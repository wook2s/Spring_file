package com.jade.myapp.upload.controller;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jade.myapp.upload.model.FileVO;
import com.jade.myapp.upload.service.UploadFileService;

@Controller
public class UploadFileController {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);

	@Autowired
	UploadFileService uploadFileService;

	@RequestMapping(value = "/upload")
	public String home() {
		return "upload/index";
	}

	@RequestMapping(value = "/upload/new")
	public String uploadFile(Model model) {
		model.addAttribute("dir", "/");
		return "upload/form";
	}

	@RequestMapping(value = "/upload/new", method = RequestMethod.POST)
	public String uploadFile(@RequestParam(value = "dir", required = false, defaultValue = "/") String dir,
			@RequestParam MultipartFile file, RedirectAttributes reattrs) {
		logger.info(file.getName() + "uploadFile() start");
		try {
			if (file != null && !file.isEmpty()) {
				logger.info(file.getName() + "creating file start");
				FileVO fileVO = new FileVO();
				fileVO.setFileName(file.getOriginalFilename());
				fileVO.setDirectoryName(dir);
				fileVO.setFileSize(file.getSize());
				fileVO.setFileContentType(file.getContentType());
				logger.info(file.getContentType());
				fileVO.setFileData(file.getBytes());
				logger.info(fileVO.toString() + "created, insert start");
				uploadFileService.uploadFile(fileVO);
				logger.info(fileVO.toString() + "insert ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reattrs.addAttribute("message", e);
			logger.info("insert fail");
		}
		return "redirect:/upload/list";
	}

	@RequestMapping(value = "/upload/list")
	public String getFileList(Model model,
			@RequestParam(value = "dir", required = false, defaultValue = "/") String dir) {
		if (dir.equals("/")) {
			logger.info("dir = /");
			model.addAttribute("fileList", uploadFileService.getAllFileList());
			logger.info(uploadFileService.getAllFileList().toString());
		} else {
			logger.info("dir != /");
			model.addAttribute("fileList", uploadFileService.getFileList("/" + dir));
		}
		return "upload/list";
	}

	@RequestMapping(value = "/upload/gallery")
	public String getImageList(Model model,
			@RequestParam(value = "dir", required = false, defaultValue = "/images") String dir) {
		model.addAttribute("fileList", uploadFileService.getImageList(dir));
		return "upload/gallery";
	}

	@RequestMapping(value="/img/{fileId}")
	public ResponseEntity<byte[]> getImageFile(@PathVariable int fileId){
		FileVO file = uploadFileService.getFile(fileId);
		final HttpHeaders headers = new HttpHeaders();
		
		if(file != null) {
			logger.info("getFile" + file.toString());
			String[] mtypes = file.getFileContentType().split("/");
			headers.setContentType(new MediaType(mtypes[0],mtypes[1]));
			headers.setContentLength(file.getFileSize());
			headers.setContentDispositionFormData("attachment", file.getFileName() , Charset.forName("UTF-8"));
			return new ResponseEntity<byte[]>(file.getFileData(),headers,HttpStatus.OK);
		}else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/pds/{fileId}")
	ResponseEntity<byte[]> getBinaryFile(@PathVariable int fileId) {
		FileVO file = uploadFileService.getFile(fileId);

		if (file != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentLength(file.getFileSize());

 			String[] mediatypes = file.getFileContentType().split("/");
			MediaType mtype = new MediaType(mediatypes[0], mediatypes[1]);
			headers.setContentType(mtype);
			headers.setContentDispositionFormData("attachment", file.getFileName(), Charset.forName("UTF-8"));
			return new ResponseEntity<byte[]>(file.getFileData(), headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/upload/delete/{fileId}")
	public String deleteFile(@PathVariable int fileId) {
		logger.info("delete ()");
		uploadFileService.deleteFile(fileId);
		return"redirect:/upload/list";
	}
	
	@RequestMapping(value = "/upload/updateDir")
	public String updateDirectory(@RequestParam int fileIds[], @RequestParam String newDir){
		uploadFileService.updateDirectory(fileIds, newDir);
		return"redirect:/upload/list";
	}

}
