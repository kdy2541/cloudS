package com.capstone.cloudS.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class CommonResponse<T> {
    @ApiModelProperty(value="responseStatusCode",position = 0)
    private String status = "200";

    @ApiModelProperty(value="ResponseMessage",position = 1)
    private String message="ok";

    @ApiModelProperty(value = "ResponseDate",position = 2)
    private T data;

    public CommonResponse(){
        super();
    }

    public CommonResponse(T data){
        this.data = data;
    }

    public CommonResponse(String status, String message){
        super();
        this.status = status;
        this.message = message;
    }

    public CommonResponse(String status, String message, T data){
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
