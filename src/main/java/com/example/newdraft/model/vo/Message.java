package com.example.newdraft.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "返回对象的信息")
public class Message {
    @ApiModelProperty(value = "状态码")
    private String code;
    @ApiModelProperty(value = "返回携带的信息")
    private String msg;
    @ApiModelProperty(value = "返回JSON字符串信息")
    private String data;
}
