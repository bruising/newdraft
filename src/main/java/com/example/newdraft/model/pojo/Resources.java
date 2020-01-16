package com.example.newdraft.model.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: newdraft
 * @Package com.example.newdraft.model.pojo
 * @Description: ${todo}
 * @author 吴成卓
 * @date 2020/1/16 星期四 13:45
 * @version V1.0 
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resources {
    /**
    * 资源的主键
    */
    private Integer resId;

    /**
    * 文件名
    */
    private String resName;

    /**
    * 1：图片，2：文档
    */
    private Integer resType;

    /**
    * 链接
    */
    private String resLink;

    /**
    * 创建时间
    */
    private Date createdTime;

    /**
    * 最后修改时间
    */
    private Date updatedTime;

    /**
    * 备注
    */
    private String resComment;
}