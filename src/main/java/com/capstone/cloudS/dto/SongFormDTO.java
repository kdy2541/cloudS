package com.capstone.cloudS.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value ="노래생성 요청 클래스")
public class SongFormDTO {
    @ApiModelProperty(notes="노래 제목입니다.",position = 0)
    private String title;

    @ApiModelProperty(notes="노래가사입니다",position = 1)
    private String content;

    @ApiModelProperty(notes="이 노래의 가수입니다",position = 2)
    private String singer;

    @Null
    @ApiModelProperty(notes="노래의 앨범 id",position = 3)
    private String album;

    public SongFormDTO(){
        super();
    }
}
