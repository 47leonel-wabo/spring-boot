package com.aiwaweb.blob.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aiwaweb.blob.model.Doc;

public interface DocStorageService {
	
	public Doc saveFileDocument(MultipartFile file);
	public Doc getDocumentById(Integer dicID);
	public List<Doc> getallDocuments();

}
