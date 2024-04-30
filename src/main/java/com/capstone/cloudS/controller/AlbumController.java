package com.capstone.cloudS.controller;


import com.capstone.cloudS.dao.AlbumDAO;
import com.capstone.cloudS.dao.StorageService;
import com.capstone.cloudS.dto.AlbumDTO;
import com.capstone.cloudS.dto.AlbumFormDTO;
import com.capstone.cloudS.dto.CommonResponse;
import com.capstone.cloudS.entity.Album;
import com.capstone.cloudS.enums.Genre;
import com.capstone.cloudS.utils.ObjectMapperUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.Resource;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;


@RestController
@RequestMapping("albums")
@Api(tags = "Albums")
public class AlbumController {

    @Autowired
    private StorageService storageService;

    @Autowired
    AlbumDAO albumDAO;

    @Value("${user.home}${app.upload.dir:${user.home}}")
    public String uploadDir;

    @GetMapping("")
    @ApiOperation(value = "앨범목록 리턴")
    public ResponseEntity<CommonResponse<List<Album>>> findAll(
            @RequestParam(required = false,name = "genre")Genre genre,
            @RequestParam(required = false, name = "name")String name){

        if(name !=null || genre != null){
            List<Album> albums = albumDAO.findByNameOrGenre(name, genre);
            return new ResponseEntity<>(new CommonResponse<List<Album>>(albums),
                    HttpStatus.OK);
        }else{
            List<Album> albums = albumDAO.findAll();
            return new ResponseEntity<>(new CommonResponse<List<Album>>(albums),
                    HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "앨범id로 찾기")
    public ResponseEntity<CommonResponse<Album>> findByID(@PathVariable String id) throws NotFoundException{

        Album album = albumDAO.findById(id);
        return new ResponseEntity<>(new CommonResponse<Album>(album), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "새로운 앨범 만들기")
    public ResponseEntity<CommonResponse<Album>> create(@RequestBody AlbumFormDTO form) throws NotFoundException{
        Album createdAlbum = albumDAO.create(form);
        return new ResponseEntity<>(new CommonResponse<Album>("201","Created",createdAlbum),
                HttpStatus.CREATED);
    }

    @PutMapping("")
    @ApiOperation(value = "앨범 업데이트")
    public ResponseEntity<CommonResponse<Album>> update(@RequestBody AlbumDTO album) throws NotFoundException{
        Album updatedAlbum = albumDAO.update(ObjectMapperUtils.map(album,Album.class));
        return new ResponseEntity<>(new CommonResponse<Album>(updatedAlbum),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "id로 앨범 삭제")
    public ResponseEntity<CommonResponse<Album>> delete(@PathVariable String id) throws NotFoundException{
        albumDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<CommonResponse<Album>> handleFileUpload(@RequestParam("file")MultipartFile file,
                                                                  @PathVariable String id) throws FileNotFoundException, NotFoundException{

        String path = storageService.store(file, id);
        Album updatedAlbum = albumDAO.updateImages(id, path);
        return new ResponseEntity<>(new CommonResponse<Album>(updatedAlbum), HttpStatus.OK);

    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable String id) throws MalformedURLException, NotFoundException{

        Album album = albumDAO.findById(id);

        Resource resource = storageService.loadAsResource(album.getImages());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }



}
