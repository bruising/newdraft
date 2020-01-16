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
public class MidWordUser {
    /**
    * 申请的详细资料关联中间表 主键
    */
    private Integer mwuId;

    /**
    * 关联users 的主键
    */
    private Integer userId;

    /**
    * 关联商家的主键
    */
    private Integer firmId;

    /**
    * 关联项目主键
    */
    private Integer programId;

    /**
    * 1：审批；2：未审批
    */
    private Integer mwuStatus;
}