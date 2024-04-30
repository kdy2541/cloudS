package com.capstone.cloudS.dao;

import java.util.List;
import com.capstone.cloudS.entity.Singer;
import org.apache.ibatis.javassist.NotFoundException;

public interface SingerDAO {
    List<Singer> findAll();
    Singer findById(String id) throws NotFoundException;
    Singer create(Singer singer);
    Singer update(Singer singer) throws NotFoundException;
    void delete(String id) throws NotFoundException;
    Singer findByIdAndAlbumId(String singerId, String albumId);
}
