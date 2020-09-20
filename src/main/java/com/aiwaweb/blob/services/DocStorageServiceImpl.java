package com.aiwaweb.blob.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.aiwaweb.blob.model.Doc;
import com.aiwaweb.blob.repos.DocRepository;

@Service
public class DocStorageServiceImpl implements DocStorageService {

	private final DocRepository docRepository;

	@Autowired
	public DocStorageServiceImpl(DocRepository docRepository) {
		super();
		this.docRepository = docRepository;
	}

	@Override
	public Doc saveFileDocument(MultipartFile file) {
		String docName = file.getOriginalFilename();
		try {
			Doc doc = new Doc(
					docName,
					file.getContentType(),
					file.getBytes());
			return docRepository.save(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Doc getDocumentById(Integer docID) {
		return docRepository.findById(docID).get();
	}

	@Override
	public List<Doc> getallDocuments() {
		return docRepository.findAll();
	}

}
