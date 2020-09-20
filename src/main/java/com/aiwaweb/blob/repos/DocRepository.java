package com.aiwaweb.blob.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiwaweb.blob.model.Doc;

public interface DocRepository extends JpaRepository<Doc, Integer>{

}
