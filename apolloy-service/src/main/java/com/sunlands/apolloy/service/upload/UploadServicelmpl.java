package com.sunlands.apolloy.service.upload;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.sunlands.apolloy.baseResultJson.ResultJsonManager;
import com.sunlands.apolloy.dto.ResDTO;
import com.sunlands.apolloy.exception.ApolloyException;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/12/2
 * Time: 10:46
 */
@Service
public class UploadServicelmpl implements UploadService {

	private static final Logger logger = LoggerFactory.getLogger(UploadService.class);
	//业务秘钥key
	@Value("${file.system.image.secret.key}")
	private String imageSecretKey;
	//业务访问key
	@Value("${file.system.image.access.key}")
	private String imageAccessKey;
	//接口版本
	@Value("${file.system.version}")
	private String version;
	//验证鉴权
	private String authorization = "";
	@Value("${file.system.image.key}")
	private String imageKey;
	@Value("${file.system.image.suffix}")
	private String imageSuffix;
	//token有效期 默认1小时
	@Value("${file.system.timeout}")
	private Integer timeout;
	@Value("${file.system.getToken.api}")
	private String sfsGetTokenAPI;
	@Value("${file.system.upload.api}")
	private String sfsUploadAPI;
	@Value("${file.system.visitFile.api}")
	private String sfsVisitFileAPI;

	private static final String DATA = "data";
	private static final String UPLOADURL = "uploadUrl";
	private static final String HEADER = "header";
	private static final String SECRETKEY = "secretKey";
	private static final String ACCESSKEY = "accessKey";
	private static final String TIMEOUT = "timeout";
	private static final String AUTHORIZATION = "authorization";
	private static final String VERSION = "version";
	private static final String ACCESSTOKEN = "accessToken";
	private static final String KEY = "key";
	//文件路径以及文件名
	private UUID uuid = UUID.randomUUID();

	@Override
	public ResDTO uploadImage(MultipartFile file) {

		try {
			//上传参数通过调用上传签名接口获得
			JSONObject uploadJson = uploadAuth();
			long time = System.currentTimeMillis();

			RequestBody uploadBody = RequestBody.create(null, file.getBytes());
			//注意：上传必须是put请求
			Request.Builder requestBuilder = new Request.Builder()
					.url(uploadJson.getJSONObject(DATA).getString(UPLOADURL))
					.put(uploadBody);
			uploadJson.getJSONObject(DATA).getJSONObject(HEADER).forEach((k, v) -> requestBuilder.addHeader(k, v.toString()));
			OkHttpClient client = new OkHttpClient();
			Response execute = client.newCall(requestBuilder.build()).execute();
			logger.info("上传图片 time {}", System.currentTimeMillis() - time);

			if (execute == null) {
				logger.error("上传图片失败,返回值为空 {}", execute);
				throw new ApolloyException("上传图片失败,返回值为空");
			}
			if (execute.code() == 200) {
				logger.info("上传成功！");
				String url = sfsVisitFileAPI + imageKey + uuid + imageSuffix;
				return ResultJsonManager.getResultJson().returnDataSuccessJson(url);
			} else {
				//如果出现错误，请将错误信息记录日志，并提供给文件服务管理员处理
				String errorXml = Objects.requireNonNull(execute.body()).string();
				logger.error("上传失败！error:{}", errorXml);
				throw new ApolloyException("上传失败!");
			}
		} catch (Exception e) {
			logger.error("上传图片失败", e);
			throw new ApolloyException("上传图片失败");
		}
	}

	//上传文件服务器获取token
	public String getImageToken() {
		//todo 做缓存机制
		String imageToken = "";
		if (!StringUtils.isEmpty(imageToken)) {
			logger.info("getImageToken from redis success {}", imageToken);
			return imageToken;
		}
		String token = "";
		try {

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(ACCESSKEY, imageAccessKey);
			jsonObject.put(SECRETKEY, imageSecretKey);
			//token有效时长，默认1小时
			jsonObject.put(TIMEOUT, timeout);

			logger.info("getImageToken from url params {}", jsonObject);
			long startTime = System.currentTimeMillis();

			OkHttpClient client = new OkHttpClient();
			MediaType type = MediaType.parse("application/json; charset=utf-8");
			RequestBody body = RequestBody.create(type, jsonObject.toJSONString());
			Request request = new Request.Builder()
					.addHeader(VERSION, version)
					.url(sfsGetTokenAPI)
					.post(body)
					.build();

			Call call = client.newCall(request);
			Response execute = call.execute();

			logger.info("getImageToken from url time {}", System.currentTimeMillis() - startTime);

			JSONObject object = tokenCheck(execute);
			logger.info("getImageToken from url result {}", object);

			token = object.getJSONObject(DATA).get(ACCESSTOKEN).toString();
		} catch (Exception e) {
			logger.error("getImageToken from url failed {}", e);
			throw new ApolloyException("getImageToken from url failed");
		}
		return token;
	}

	//解析上传图片返回值
	public JSONObject urlUploadCheck(Response execute) {
		if ((execute == null || execute.body() == null)) {
			throw new ApolloyException("获取上传图片url失败,uploadAuth()返回值为空");
		}
		try {
			String uploadParam = execute.body().string();
			JSONObject uploadJson = JSONObject.parseObject(uploadParam);
			if (uploadJson.getJSONObject(DATA) == null) {
				throw new ApolloyException("获取上传图片url失败,返回值data为空");
			}
			if (uploadJson.getJSONObject(DATA).getString(UPLOADURL) == null
					|| "".equals(uploadJson.getJSONObject(DATA).getString(UPLOADURL))) {
				throw new ApolloyException("获取上传图片url失败,url值为空");
			}
			if (uploadJson.getJSONObject(DATA).getJSONObject(HEADER) == null) {
				throw new ApolloyException("获取上传图片url失败,header为空值为空");
			}
			return uploadJson;
		} catch (Exception e) {
			logger.error("解析body出错 {}", e);
			throw new ApolloyException("解析body出错");
		}
	}

	//解析上传图片返回值
	public JSONObject tokenCheck(Response execute) {
		if ((execute == null || execute.body() == null)) {
			throw new ApolloyException("获取token失败,getImageToken()返回值为空");
		}
		try {
			String uploadParam = execute.body().string();
			JSONObject uploadJson = JSONObject.parseObject(uploadParam);
			if (uploadJson.getJSONObject(DATA) == null) {
				throw new ApolloyException("获取token失败,返回值data为空");
			}
			if (uploadJson.getJSONObject(DATA).get(ACCESSTOKEN) == null
					|| "".equals(uploadJson.getJSONObject(DATA).getString(ACCESSTOKEN))) {
				throw new ApolloyException("获取token失败,token值为空");
			}
			return uploadJson;
		} catch (Exception e) {
			logger.error("解析body出错 {}", e);
			throw new ApolloyException("解析body出错");
		}
	}

	public JSONObject uploadAuth() throws IOException {
		//获取上传图片url, uuid
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(KEY, imageKey + uuid + imageSuffix);
		logger.info("获取上传图片url参数 param{}", jsonObject);
		long startTime = System.currentTimeMillis();
		OkHttpClient client = new OkHttpClient();
		MediaType type = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(type, jsonObject.toJSONString());
		Request request = new Request.Builder()
				.addHeader(VERSION, version)
				.addHeader(AUTHORIZATION, getImageToken())
				.url(sfsUploadAPI)
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response execute = call.execute();
		logger.info("获取上传图片url time {}", System.currentTimeMillis() - startTime);
		//校验返回值
		JSONObject uploadJson = urlUploadCheck(execute);
		logger.info("获取上传图片url结果 result{}", uploadJson);
		return uploadJson;
	}

}
