package com.hf.spring.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hf.spring.springdata.entity.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, String>{

}
