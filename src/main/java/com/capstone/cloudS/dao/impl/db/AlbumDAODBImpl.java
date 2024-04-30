package com.capstone.cloudS.dao.impl.db;

import com.capstone.cloudS.dao.AlbumDAO;
import com.capstone.cloudS.dao.SingerDAO;
import com.capstone.cloudS.dto.AlbumFormDTO;
import com.capstone.cloudS.entity.Album;
import com.capstone.cloudS.entity.Singer;
import com.capstone.cloudS.enums.Genre;
import com.capstone.cloudS.repository.AlbumRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumDAODBImpl implements AlbumDAO {
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    SingerDAO singerDAO;

    @Override
    public List<Album> findAll(){
        return albumRepository.findAll();
    }

    @Override
    public Album findById(String id) throws NotFoundException {
        return albumRepository.findById(id).orElseThrow(()->new NotFoundException("앨범을 찾을 수 없습니다."));
    }

    @Override
    public Album create(AlbumFormDTO form) throws NotFoundException {
        Album album = new Album();
        Singer singer = singerDAO.findById(form.getSingerId());

        album.setName(form.getName());
        album.setReleaseDate(form.getReleaseDate());
        album.setGenre(form.getGenre());
        album.setSinger(singer);

        return albumRepository.save(album);
    }

    @Override
    public Album update(Album album) throws NotFoundException {
        Album newAlbum = this.findById(album.getId());

        newAlbum.setId(album.getId());
        newAlbum.setName(album.getName());
        newAlbum.setReleaseDate(album.getReleaseDate());
        newAlbum.setGenre(album.getGenre());

        return albumRepository.save(newAlbum);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        Album album = this.findById(id);

        albumRepository.delete(album);
    }

    @Override
    public List<Album> findByNameOrGenre(String name, Genre genre) {
        return albumRepository.findByNameOrGenre(name, genre);
    }

    @Override
    public Album updateImages(String id, String path) throws NotFoundException {
        Album newAlbum = this.findById(id);

        newAlbum.setId(id);
        newAlbum.setImages(path);

        return albumRepository.save(newAlbum);
    }
}
