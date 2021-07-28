package com.jade.myapp.upload.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jade.myapp.upload.dao.IUploadFileReposistory;
import com.jade.myapp.upload.model.FileVO;

@Service
public class UploadFileService implements IUploadFileService{

	@Autowired
	IUploadFileReposistory uploadFileReposistory;
	
	@Override
	public void uploadFile(FileVO file) {
		file.setFileId(uploadFileReposistory.getMaxFileId()+1);
		uploadFileReposistory.uploadFile(file);
	}

	@Override
	public FileVO getFile(int fileId) {
		return uploadFileReposistory.getFile(fileId);
	}

	@Override
	public List<FileVO> getFileList(String dir) {
		return uploadFileReposistory.getFileList(dir);
	}

	@Override
	public List<FileVO> getAllFileList() {
		return uploadFileReposistory.getAllFileList();
	}

	@Override
	public List<FileVO> getImageList(String dir) {
		return uploadFileReposistory.getImageList(dir);
	}

	@Override
	public String getDirectoryName(int fileId) {
		return uploadFileReposistory.getDirectoryName(fileId);
	}

	@Override
	public void updateDirectory(int[] fileIds, String directoryName) {
		
		try {
			for(int fileId : fileIds) {
				HashMap<String , Object> map = new HashMap<String, Object>();
				map.put("directoryName", directoryName);
				map.put("fileId", fileId);
				uploadFileReposistory.updateDirectoryName(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void deleteFile(int fileId) {
		uploadFileReposistory.deleteFile(fileId);
	}

	@Override
	public void updateFile(FileVO file) {
		uploadFileReposistory.updateFile(file);
	}

}
