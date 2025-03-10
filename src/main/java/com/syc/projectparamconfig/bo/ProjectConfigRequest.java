package com.syc.projectparamconfig.bo;

import lombok.Data;

/**
 * 请求
 * */
@Data
public class ProjectConfigRequest {
    private String systemId;
    private String name;
    private String value;
    private Integer needCheck;
}
