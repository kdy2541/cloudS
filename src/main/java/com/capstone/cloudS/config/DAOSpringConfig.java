package com.capstone.cloudS.config;


import com.capstone.cloudS.dao.AlbumDAO;
import com.capstone.cloudS.dao.SingerDAO;
import com.capstone.cloudS.dao.SongDAO;
import com.capstone.cloudS.dao.impl.db.AlbumDAODBImpl;
import com.capstone.cloudS.dao.impl.db.SingerDAODBImpl;
import com.capstone.cloudS.dao.impl.db.SongDAODBImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOSpringConfig {

    @Bean
    public SingerDAO singerDAO(){
        return new SingerDAODBImpl();
    }

    @Bean
    public AlbumDAO albumDAO(){
        return new AlbumDAODBImpl();
    }

    @Bean
    public SongDAO songDAO(){
        return new SongDAODBImpl();
    }
}
