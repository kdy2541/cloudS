package com.capstone.cloudS.dto;

import com.capstone.cloudS.enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AlbumDTO {
    private String name;
    private Date releaseDate;
    private Genre genre;
    private String images;

    public AlbumDTO(){
        super();
    }
}
