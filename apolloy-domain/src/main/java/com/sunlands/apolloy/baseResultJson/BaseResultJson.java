package com.sunlands.apolloy.baseResultJson;


import com.sunlands.apolloy.dto.ResDTO;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:26
 */
public class BaseResultJson {


    private ResDTO resDTO = new ResDTO();

    public ResDTO returnSuccessJson(String... successMessage) {
        if (successMessage.length <= 0) {
            resDTO.setMessage("成功");
        } else {
            resDTO.setMessage(successMessage[0]);
        }
        resDTO.setData("");
        resDTO.setFlag(1);
        return resDTO;
    }

    public ResDTO returnDataSuccessJson(Object data, String... successMessage) {
        if (successMessage.length <= 0) {
            resDTO.setMessage("成功");
        } else {
            resDTO.setMessage(successMessage[0]);
        }
        resDTO.setData(data);
        resDTO.setFlag(1);
        return resDTO;
    }

    public ResDTO returnErrorJson(String... errorMessage) {
        if (null == errorMessage) {
            resDTO.setMessage("失败");
        } else {
            resDTO.setMessage(errorMessage[0]);
        }
        resDTO.setData("");
        resDTO.setFlag(0);
        return resDTO;
    }


}
