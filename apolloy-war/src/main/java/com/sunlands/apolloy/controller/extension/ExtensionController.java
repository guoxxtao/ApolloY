package com.sunlands.apolloy.controller.extension;

import com.sunlands.apolloy.dto.ReqExtensionDTO;
import com.sunlands.apolloy.dto.ReqExtensionQueryDTO;
import com.sunlands.apolloy.dto.ResDTO;
import com.sunlands.apolloy.service.extension.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 11:32
 */
@RestController
@RequestMapping("/ext")
public class ExtensionController {

	@Autowired
	private ExtensionService extensionService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResDTO add(@RequestBody ReqExtensionDTO reqDTO) {
		return extensionService.add(reqDTO);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResDTO update(@RequestBody ReqExtensionDTO reqDTO) {
		return extensionService.update(reqDTO);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResDTO delete(@RequestBody ReqExtensionDTO reqDTO) {
		return extensionService.delete(reqDTO.getId());
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public ResDTO query(@RequestBody ReqExtensionQueryDTO reqAppDTO) {
		return extensionService.query(reqAppDTO);
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ResDTO get(@RequestBody ReqExtensionDTO reqDTO) {
		return extensionService.get(reqDTO.getId());
	}
}
