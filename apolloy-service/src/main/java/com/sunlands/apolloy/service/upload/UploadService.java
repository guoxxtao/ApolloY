package com.sunlands.apolloy.service.upload;

import com.sunlands.apolloy.dto.ResDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/12/2
 * Time: 10:45
 */
public interface UploadService {

	ResDTO uploadImage(MultipartFile file);
}
