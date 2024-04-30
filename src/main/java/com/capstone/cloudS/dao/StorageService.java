package com.capstone.cloudS.dao;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public interface StorageService {

    String store(MultipartFile file, String id) throws FileNotFoundException;

    String load(String filename);
    Resource loadAsResource(String filename) throws MalformedURLException;
}
