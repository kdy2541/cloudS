package com.capstone.cloudS.entity;

import com.capstone.cloudS.enums.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.annotations.Update;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "albums")
@ApiModel(value = "Class representing albums")
public class Album {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID", strategy = "uuid")
    @ApiModelProperty(notes = "같은 id의 앨범 불가",required = true, position = 0)
    private String id;

    @Column(length = 100, nullable = false)
    @ApiModelProperty(notes="앨범이름", required = true, position = 1)
    private String name;

    @Column(nullable = false)
    @ApiModelProperty(notes="앨범등록일", required = true, position = 2)
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    @ApiModelProperty("Genre of albums.")
    private Genre genre;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(length = 150, nullable = true)
    @ApiModelProperty(notes = "이미지Path",required = true, position = 3)
    private String images;

    @ManyToOne
    @JoinColumn(name="singer",nullable = true)
    @ApiModelProperty(notes="가수",required = false,position = 4)
    private Singer singer;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy="album")
    @ApiModelProperty(notes = "앨범의 음악 목록")
    private List<Song> songs;
}
