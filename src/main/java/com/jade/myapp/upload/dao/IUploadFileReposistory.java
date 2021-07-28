package com.jade.myapp.upload.dao;

import java.util.HashMap;
import java.util.List;

import com.jade.myapp.upload.model.FileVO;

public interface IUploadFileReposistory {

	int getMaxFileId();
	void uploadFile(FileVO file);
	FileVO getFile(int fileId);
	
	List<FileVO> getFileList(String directoryName);
	List<FileVO> getAllFileList();
	List<FileVO> getImageList(String directoryName);
	
	String getDirectoryName(int fileId);
	void updateDirectoryName(HashMap<String, Object> map);
	
	void deleteFile(int fileId);
	void updateFile(FileVO file);
}
