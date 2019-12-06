package com.sunlands.apolloy.controller.app;

import com.sunlands.apolloy.dto.ReqAppDTO;
import com.sunlands.apolloy.dto.ReqAppQueryDTO;
import com.sunlands.apolloy.dto.ResDTO;
import com.sunlands.apolloy.service.app.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 11:32
 */
@RestController
@RequestMapping("/app")
public class AppController {

	@Autowired
	private AppService appService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResDTO add(@RequestBody ReqAppDTO reqAppDTO) {
		return appService.add(reqAppDTO);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResDTO update(@RequestBody ReqAppDTO reqAppDTO) {
		return appService.update(reqAppDTO);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResDTO delete(@RequestBody ReqAppDTO reqAppDTO) {
		return appService.delete(reqAppDTO.getAppCode());
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public ResDTO query(@RequestBody ReqAppQueryDTO reqAppQueryDTO) {
		return appService.query(reqAppQueryDTO);
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ResDTO get(@RequestBody ReqAppDTO reqAppDTO) {
		return appService.get(reqAppDTO.getAppCode());
	}

	@RequestMapping(value = "/getAllCategory", method = RequestMethod.POST)
	public ResDTO getAllCategory(@RequestParam Integer categoryType) {
		return appService.getAllCategory(categoryType);
	}
}
