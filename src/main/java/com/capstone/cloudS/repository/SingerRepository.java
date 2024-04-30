package com.capstone.cloudS.repository;

import com.capstone.cloudS.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerRepository extends JpaRepository<Singer, String> {
    Singer findByIdAndAlbumsId(String singerId, String albumId);
}
