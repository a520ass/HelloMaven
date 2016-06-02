package com.hf.spring.springdata.service;


import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hf.spring.springdata.dynamic.TargetDataSource;
import com.hf.spring.springdata.entity.FileInfo;
import com.hf.spring.springdata.repository.FileInfoRepository;

@Service
@Transactional
public class FileInfoService {
	
	@Autowired
	private FileInfoRepository dao;
	
	@TargetDataSource(name = "dataSource2")
	@Transactional
	public FileInfo saveEntity(FileInfo entity){
		if (StringUtils.isBlank(entity.getId())){
			entity.setId(UUID.randomUUID().toString());
		}
        return dao.save(entity);
	}
}
