package com.capstone.cloudS.dao;

import com.capstone.cloudS.dto.SongFormDTO;
import com.capstone.cloudS.entity.Song;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface SongDAO {
    List<Song> findAll();
    Song findById(String id) throws NotFoundException;
    Song create(SongFormDTO song) throws NotFoundException;
    Song update(Song song) throws NotFoundException;
    void delete(String id) throws NotFoundException;
    List<Song> findByTitle(String title);
}
