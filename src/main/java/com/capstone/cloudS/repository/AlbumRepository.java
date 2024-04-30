package com.capstone.cloudS.repository;

import com.capstone.cloudS.entity.Album;
import com.capstone.cloudS.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, String> {
    List<Album> findByNameOrGenre(String titme, Genre genre);
}
