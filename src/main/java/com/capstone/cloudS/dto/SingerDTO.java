package com.capstone.cloudS.dto;

import com.capstone.cloudS.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SingerDTO {
    private String name;
    private Date birthDate;
    private Gender gender;

    public SingerDTO(){
        super();
    }
}
