package com.example.newdraft.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 王青
 * @version V1.0
 * @Project: newdraft
 * @Package com.example.newdraft.model.vo
 * @date 2020/1/17 21:20 星期五
 */
@ApiModel(description = "这是返回的对象数据")
public class UserMessage {
    @ApiModelProperty(value = "状态码")
    private String code;
    @ApiModelProperty(value = "返回携带的信息")
    private String msg;
    @ApiModelProperty(value = "返回JSON字符串信息")
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
