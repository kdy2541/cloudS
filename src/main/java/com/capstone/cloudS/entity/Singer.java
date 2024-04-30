package com.capstone.cloudS.entity;


import com.capstone.cloudS.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="singers")
@ApiModel(value = "Class representing singer.")
public class Singer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy = "uuid")
    @ApiModelProperty(notes="같은 id의 가수 불가",required = true,position = 0)
    private String id;

    @Column(name="name", length = 15, nullable = false)
    @ApiModelProperty(notes="가수명",required = true,position = 1)
    private String name;

    @Column(name = "birth_date",nullable = false)
    @ApiModelProperty(notes="생년월일",dataType = "Enum",required = true,position = 2)
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender",length = 8,nullable = false)
    @ApiModelProperty(notes="성별",dataType = "Enum",required = true,position = 3)
    private Gender gender;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true,mappedBy = "singer")
    @ApiModelProperty(notes = "가수의 보유 앪범", position = 4)
    private List<Album> albums;

    public Singer(){
        super();
    }

}
