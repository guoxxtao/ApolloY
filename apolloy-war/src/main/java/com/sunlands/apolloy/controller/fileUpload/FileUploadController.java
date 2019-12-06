package com.sunlands.apolloy.controller.fileUpload;

import com.sunlands.apolloy.dto.ResDTO;
import com.sunlands.apolloy.service.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/12/2
 * Time: 15:50
 */
@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {

	@Autowired
	private UploadService uploadService;

	@RequestMapping(value = "/uploadImage",consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	public ResDTO uploadImage(@RequestParam MultipartFile file){
		return uploadService.uploadImage(file);
	}


}
