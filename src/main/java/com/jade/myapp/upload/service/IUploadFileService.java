package com.jade.myapp.upload.service;

import java.util.List;

import com.jade.myapp.upload.model.FileVO;

public interface IUploadFileService {

	void uploadFile(FileVO file);
	FileVO getFile(int fileId);
	
	List<FileVO> getFileList(String dir);
	List<FileVO> getAllFileList();
	List<FileVO> getImageList(String dir);
	
	
	String getDirectoryName(int fileId);
	void updateDirectory(int[] fileIds, String directoryName);
	
	void deleteFile(int fileId);
	void updateFile(FileVO file);
}
