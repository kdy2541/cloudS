package com.capstone.cloudS.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponseError {

    @ApiModelProperty(value = "responseStatusCode",position = 0)
    private String status;

    @ApiModelProperty(value = "responseMessage",position = 1)
    private String message;

    public CommonResponseError(){
        super();
    }
}
