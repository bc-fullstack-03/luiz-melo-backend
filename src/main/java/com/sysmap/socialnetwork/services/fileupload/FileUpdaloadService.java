package com.sysmap.socialnetwork.services.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUpdaloadService implements IFileUploadService{
	
	@Autowired
	private AwsService awsService;
	
	public String upload(MultipartFile file, String fileName) throws Exception {
		var fileUri = "";
		
		try {
			fileUri = awsService.upload(file, fileName);
		}catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		
		return fileUri;
	}
}
