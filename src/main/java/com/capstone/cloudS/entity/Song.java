package com.capstone.cloudS.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="song")
@ApiModel(value = "Class representing songs")
public class Song {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy = "uuid")
    @ApiModelProperty(notes="같은 id의 노래 불가",required = true,position = 0)
    private String id;

    @Column(name="title",length=100,nullable = false)
    @ApiModelProperty(notes ="노래제목",required = true,position = 1)
    private String title;

    @Column(name="content",length = 1000,nullable = false)
    @ApiModelProperty(notes="노래가사",required = true,position = 2)
    private String content;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name="singer",nullable = false)
    @ApiModelProperty(notes="노래를부른가수",required = true,position = 3)
    private Singer singer;

    @ManyToOne
    @JoinColumn(name="album",nullable = true)
    @ApiModelProperty(notes="이노래의앨범",required = false,position = 4)
    private Album album;

}
