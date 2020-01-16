package com.example.newdraft.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: newdraft
 * @Package com.example.newdraft.model.pojo
 * @Description: ${todo}
 * @author 吴成卓
 * @date 2020/1/16 星期四 13:44
 * @version V1.0 
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MidProgramFirm {
    /**
    * 商家项目中间表 主键
    */
    private Integer mpfId;

    /**
    * 项目主键
    */
    private Integer projectId;

    /**
    * 关联上商家表 主键
    */
    private Integer firmId;
}