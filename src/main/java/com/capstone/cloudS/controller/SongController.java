package com.capstone.cloudS.controller;


import com.capstone.cloudS.dao.SongDAO;
import com.capstone.cloudS.dto.CommonResponse;
import com.capstone.cloudS.dto.SongFormDTO;
import com.capstone.cloudS.entity.Song;
import com.capstone.cloudS.utils.ObjectMapperUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("songs")
@Api(tags = "Songs")
public class SongController {

    @Autowired
    SongDAO songDAO;

    @GetMapping("")
    @ApiOperation(value = "음악 리스트 리턴", code = 200)
    public ResponseEntity<CommonResponse<List<Song>>> findAll(@RequestParam(required = false) String title){
        if(title != null){
            List<Song> songs = songDAO.findByTitle(title);
            return new ResponseEntity<>(new CommonResponse<List<Song>>(songs), HttpStatus.OK);
        }else{
            List<Song> songs = songDAO.findAll();
            return new ResponseEntity<>(new CommonResponse<List<Song>>(songs), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "id로 음악리턴",code = 200)
    public ResponseEntity<CommonResponse<Song>> findById(@PathVariable String id) throws NotFoundException{
        Song song = songDAO.findById(id);
        return new ResponseEntity<>(new CommonResponse<Song>(song), HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation("새로운 음악")
    public ResponseEntity<CommonResponse<Song>> create(@RequestBody SongFormDTO form) throws NotFoundException{
        Song song = songDAO.create(form);
        return new ResponseEntity<>(new CommonResponse<Song>("201","OK",song), HttpStatus.CREATED);
    }

    @PutMapping("")
    @ApiOperation("음악 업데이트")
    public ResponseEntity<CommonResponse<Song>> update(@RequestBody SongFormDTO song) throws NotFoundException{
        Song updateSong = songDAO.update(ObjectMapperUtils.map(song,Song.class));
        return new ResponseEntity<>(new CommonResponse<Song>(updateSong), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("id로 음악 삭제")
    public ResponseEntity<CommonResponse<Song>> delete(@PathVariable String id) throws NotFoundException{
        songDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
