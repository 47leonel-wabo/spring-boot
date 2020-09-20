package com.aiwaweb.blob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aiwaweb.blob.model.Doc;
import com.aiwaweb.blob.services.DocStorageServiceImpl;

@Controller
public class DocController {

	private final DocStorageServiceImpl docService;

	@Autowired
	public DocController(DocStorageServiceImpl docService) {
		super();
		this.docService = docService;
	}
	
	@GetMapping("/")
	public String getDocument(Model model) {
		List<Doc> docs = docService.getallDocuments();
		model.addAttribute("docs", docs);
		return "doc";
	}
	
	@PostMapping("/uploadFiles")
	public String uploadMultipartFile(@RequestParam("files") MultipartFile[] files) {
		for (MultipartFile multipartFile : files) {
			docService.saveFileDocument(multipartFile);
		}
		return "redirect:/";
	}
	
	@GetMapping("/downloadFile/{docId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("docId") Integer docId){
		Doc doc = docService.getDocumentById(docId);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(org.springframework.http.HttpHeaders
						.CONTENT_DISPOSITION, "attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getDocData()));
	}
	
	@PostMapping("/documents/delete/{docId}")
	public String removeDocument(@PathVariable("docId") Integer id) {
		docService.deleteDocumentByID(id);
		return "redirect:/";
	}
}
