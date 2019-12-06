package com.sunlands.apolloy.controller.extensionPoint;

import com.sunlands.apolloy.dto.ReqExtensionPointDTO;
import com.sunlands.apolloy.dto.ReqExtensionPointQueryDTO;
import com.sunlands.apolloy.dto.ResDTO;
import com.sunlands.apolloy.service.extensionPoint.ExtensionPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 11:32
 */
@RestController
@RequestMapping("/extPoint")
public class ExtensionPointController {

	@Autowired
	private ExtensionPointService extensionPointService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResDTO add(@RequestBody ReqExtensionPointDTO reqDTO) {
		return extensionPointService.add(reqDTO);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResDTO update(@RequestBody ReqExtensionPointDTO reqDTO) {
		return extensionPointService.update(reqDTO);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResDTO delete(@RequestBody ReqExtensionPointDTO reqDTO) {
		return extensionPointService.delete(reqDTO.getId());
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public ResDTO query(@RequestBody ReqExtensionPointQueryDTO reqDTO) {
		return extensionPointService.query(reqDTO);
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ResDTO get(@RequestBody ReqExtensionPointDTO reqDTO) {
		return extensionPointService.get(reqDTO.getId());
	}

	@RequestMapping(value = "/queryByApp", method = RequestMethod.POST)
	@CrossOrigin("*")
	public ResDTO queryByApp(@RequestBody ReqExtensionPointQueryDTO reqDTO) {
		return extensionPointService.queryByApp(reqDTO);
	}

}
