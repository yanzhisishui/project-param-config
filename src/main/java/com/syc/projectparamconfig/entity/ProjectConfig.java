package com.syc.projectparamconfig.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 项目参数配置
 */
@Data
@TableName("project_config")
public class ProjectConfig {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 服务id
     */
    private String systemId;

    /**
     * 配置参数名称
     */
    private String name;

    /**
     * 值
     */
    private String value;

    /**
     * 描述
     */
    private String description;
    /**
     * 是否需要审核
     */
    private Integer needCheck;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @TableLogic
    private Integer deleted;
}
