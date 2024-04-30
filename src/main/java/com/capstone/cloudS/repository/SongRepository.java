package com.capstone.cloudS.repository;

import com.capstone.cloudS.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,String> {
    List<Song> findByTitle(String title);
}
