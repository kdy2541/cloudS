package com.capstone.cloudS.dao;

import com.capstone.cloudS.dto.AlbumFormDTO;
import com.capstone.cloudS.entity.Album;
import com.capstone.cloudS.enums.Genre;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface AlbumDAO {
    List<Album> findAll();
    Album findById(String id) throws NotFoundException;
    Album create(AlbumFormDTO album) throws NotFoundException;
    Album update(Album album) throws NotFoundException;
    void delete(String id) throws NotFoundException;
    List<Album> findByNameOrGenre(String name, Genre genre);
    Album updateImages(String id, String path) throws NotFoundException;
}
