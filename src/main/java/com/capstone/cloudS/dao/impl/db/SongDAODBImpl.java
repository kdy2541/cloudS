package com.capstone.cloudS.dao.impl.db;

import com.capstone.cloudS.dao.AlbumDAO;
import com.capstone.cloudS.dao.SingerDAO;
import com.capstone.cloudS.dao.SongDAO;
import com.capstone.cloudS.dto.SongFormDTO;
import com.capstone.cloudS.entity.Album;
import com.capstone.cloudS.entity.Singer;
import com.capstone.cloudS.entity.Song;
import com.capstone.cloudS.repository.SongRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SongDAODBImpl implements SongDAO {

    @Autowired
    SongRepository songRepository;
    @Autowired
    SingerDAO singerDAO;
    @Autowired
    AlbumDAO albumDAO;


    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Song findById(String id) throws NotFoundException {
        return songRepository.findById(id).orElseThrow(() -> new NotFoundException("음악을 찾을 수 없습니다"));
    }


    @Override
    public Song create(SongFormDTO form) throws NotFoundException {
        Song song = new Song();
        song.setTitle(form.getTitle());
        song.setContent(form.getContent());

        Singer singer = singerDAO.findById(form.getSinger());
        Album album = albumDAO.findById(form.getAlbum());

        song.setSinger(singer);
        song.setAlbum(album);

        return songRepository.save(song);
    }

    @Override
    public Song update(Song form) throws NotFoundException {
        Song song = this.findById(form.getId());

        song.setId(form.getId());
        song.setTitle(form.getTitle());
        song.setContent(form.getContent());

        return songRepository.save(song);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        Song song = this.findById(id);
        songRepository.delete(song);
    }

    @Override
    public List<Song> findByTitle(String title) {
        return songRepository.findByTitle(title);
    }
}
