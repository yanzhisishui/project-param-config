package com.syc.projectparamconfig.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.syc.projectparamconfig.bo.ProjectConfigRequest;
import com.syc.projectparamconfig.client.ProjectConfigClient;
import com.syc.projectparamconfig.entity.ProjectConfig;
import com.syc.projectparamconfig.mapper.ProjectConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ProjectConfigService {

    @Autowired
    private ProjectConfigMapper projectConfigMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(ProjectConfigRequest request) {
        ProjectConfig projectConfig = projectConfigMapper.selectOne(Wrappers.<ProjectConfig>lambdaQuery()
                .eq(ProjectConfig::getSystemId, request.getSystemId())
                .eq(ProjectConfig::getName, request.getName()).last(" for update"));
        if (projectConfig != null) {
            projectConfig.setUpdateTime(LocalDateTime.now());
            projectConfig.setValue(request.getValue());
            projectConfig.setNeedCheck(request.getNeedCheck());
            projectConfigMapper.updateById(projectConfig);
        } else {
            projectConfig = new ProjectConfig();
            projectConfig.setName(request.getName());
            projectConfig.setCreateTime(LocalDateTime.now());
            projectConfig.setValue(request.getValue());
            projectConfig.setSystemId(request.getSystemId());
            projectConfig.setUpdateTime(LocalDateTime.now());
            projectConfig.setNeedCheck(request.getNeedCheck());
            projectConfigMapper.insert(projectConfig);
        }
        //更新缓存
        ProjectConfigClient.PROJECT_CONFIG_CACHE.invalidate(request.getSystemId() + ProjectConfigClient.SEPARATOR + request.getName());
    }


    @Transactional(rollbackFor = Exception.class)
    public String getValue(String systemId, String name, String defaultValue) {
        log.info("ProjectConfigService#getValue systemId-{},name-{}", systemId, name);
        ProjectConfig projectConfig = projectConfigMapper.selectOne(Wrappers.<ProjectConfig>lambdaQuery().eq(ProjectConfig::getSystemId, systemId).eq(ProjectConfig::getName, name).last(" for update"));
        if (projectConfig != null) {
            return projectConfig.getValue();
        }
        projectConfig = new ProjectConfig();
        projectConfig.setSystemId(systemId);
        projectConfig.setName(name);
        projectConfig.setValue(defaultValue);
        projectConfig.setCreateTime(LocalDateTime.now());
        projectConfig.setUpdateTime(LocalDateTime.now());
        projectConfig.setNeedCheck(0);
        projectConfigMapper.insert(projectConfig);
        return projectConfig.getValue();
    }
}
