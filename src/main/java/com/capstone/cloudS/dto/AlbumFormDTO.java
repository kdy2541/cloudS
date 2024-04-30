package com.capstone.cloudS.dto;


import com.capstone.cloudS.enums.Genre;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel(value = "새로운 앨범 만들기를 위한albumDTO")
public class AlbumFormDTO {

    @Size(max=100,message = "앨범의 이름은 100자까지입니다.")
    @ApiModelProperty(notes ="앨범의 이름입니다",required = true,position = 0)
    private String name;

    @Size(min = 4,max = 4,message = "발매년도는 4자여야합니다")
    @ApiModelProperty(notes ="발매년도",required = true,position = 1,example = "2000-01-01")
    private Date releaseDate;

    @Size(message = "앨범의 장르")
    @ApiModelProperty(notes="앨범 장르",required = true,position = 2)
    private Genre genre;

    @Size(message = "조건에 맞는 가수 id를 넣으시오")
    @ApiModelProperty(notes = "앨범의 가수id", required = true, position = 3)
    private String singerId;

    public AlbumFormDTO(){
        super();
    }
}
