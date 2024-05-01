package com.capstone.cloudS.controller;


import com.capstone.cloudS.dao.SingerDAO;
import com.capstone.cloudS.dto.CommonResponse;
import com.capstone.cloudS.dto.SingerDTO;
import com.capstone.cloudS.entity.Singer;
import com.capstone.cloudS.utils.ObjectMapperUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/singers")
@Api(tags = "Singers")
public class SingerController {

    @Autowired
    SingerDAO singerDAO;

    @GetMapping("")
    @ApiOperation(value = "가수 목록 리턴")
    public ResponseEntity<CommonResponse<List<SingerDTO>>> getAll(){

        List<Singer> singers = singerDAO.findAll();
        return new ResponseEntity<>(
                new CommonResponse<List<SingerDTO>>(ObjectMapperUtils.mapAll(singers, SingerDTO.class)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "id로 가수 리턴")
    public ResponseEntity<CommonResponse<SingerDTO>> findById(@PathVariable String id) throws NotFoundException{
        Singer singer = singerDAO.findById(id);

        return new ResponseEntity<>( new CommonResponse<SingerDTO>(ObjectMapperUtils.map(singer, SingerDTO.class)), HttpStatus.OK);
    }

    @GetMapping("/{id}/albums")
    @ApiOperation(value = "가수가 가지고있는 앨범 리턴")
    public ResponseEntity<CommonResponse<Singer>> findAlbumBySingerId(@PathVariable String id) throws NotFoundException{
        Singer singer = singerDAO.findById(id);

        return new ResponseEntity<>( new CommonResponse<Singer>(ObjectMapperUtils.map(singer,Singer.class)), HttpStatus.OK);
    }

    @GetMapping("/{singerId}/albums/{albumId}")
    @ApiOperation(value = "한개의 앨범 맅너")
    public ResponseEntity<CommonResponse<Singer>> findBySingerAlbumId(@PathVariable String singerId,@PathVariable String albumId){
        Singer singer = singerDAO.findByIdAndAlbumId(singerId, albumId);
        return new ResponseEntity<>(new CommonResponse<Singer>(ObjectMapperUtils.map(singer,Singer.class)),HttpStatus.OK);

    }
    @PostMapping("")
    @ApiOperation(value = "새로운 가수 등록")
    public ResponseEntity<CommonResponse<SingerDTO>> create(@RequestBody SingerDTO singer){
        Singer newSinger = singerDAO.create(ObjectMapperUtils.map(singer, Singer.class));
        return new ResponseEntity<>( new CommonResponse<SingerDTO>("201","Created",ObjectMapperUtils.map(newSinger, SingerDTO.class)),HttpStatus.CREATED);
    }

    @PutMapping("")
    @ApiOperation(value = "가수 업데이트")
    public ResponseEntity<CommonResponse<SingerDTO>> update(@RequestBody SingerDTO singer) throws NotFoundException{
        Singer updatedSinger = singerDAO.update(ObjectMapperUtils.map(singer,Singer.class));
        return new ResponseEntity<>( new CommonResponse<SingerDTO>(ObjectMapperUtils.map(updatedSinger,SingerDTO.class)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "id로 가수 삭제")
    public ResponseEntity<CommonResponse<SingerDTO>> delete(@PathVariable String id) throws NotFoundException{
        singerDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
