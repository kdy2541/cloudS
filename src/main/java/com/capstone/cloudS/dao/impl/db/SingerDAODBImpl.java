package com.capstone.cloudS.dao.impl.db;


import com.capstone.cloudS.dao.SingerDAO;
import com.capstone.cloudS.entity.Singer;
import com.capstone.cloudS.repository.SingerRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerDAODBImpl implements SingerDAO {

    @Autowired
    SingerRepository singerRepository;
    @Override
    public List<Singer> findAll() {
        return singerRepository.findAll();
    }

    @Override
    public Singer findById(String id) throws NotFoundException {
        return singerRepository.findById(id).orElseThrow(()->new NotFoundException("가수를 찾을 수 없습니다"));
    }


    @Override
    public Singer create(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public Singer update(Singer form) throws NotFoundException {
        Singer singer = this.findById(form.getId());

        singer.setId(form.getId());
        singer.setName(form.getName());
        singer.setBirthDate(form.getBirthDate());
        singer.setGender(form.getGender());

        return singerRepository.save(singer);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        Singer singer = this.findById(id);
        singerRepository.delete(singer);
    }

    @Override
    public Singer findByIdAndAlbumId(String singerId, String albumId) {
        return singerRepository.findByIdAndAlbumsId(singerId,albumId);
    }
}
