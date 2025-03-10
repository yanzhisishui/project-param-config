package com.syc.projectparamconfig.client;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.syc.projectparamconfig.service.ProjectConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ProjectConfigClient {
    @Autowired
    private ProjectConfigService projectConfigService;

    // 创建一个缓存实例
    public static final Cache<String, String> PROJECT_CONFIG_CACHE = Caffeine.newBuilder()
            .expireAfterWrite(600, TimeUnit.MINUTES) // 写入后 600 分钟过期
            .maximumSize(100000) // 最大缓存项数
            .build();

    public static final String SEPARATOR = ":";


    /**
     * 获取开关配置
     * */
    public Boolean getSwitch(String systemId,String name,Boolean defaultValue) {
        String value = getValue(systemId, name, String.valueOf(defaultValue));
        return "1".equals(value) || "true".equals(value);
    }

    /**
     * 获取开关配置
     * */
    public Boolean getSwitch(String systemId,String name) {
        String value = getValue(systemId, name,null);
        return "1".equals(value) || "true".equals(value);
    }


    /**
     * 获取项目参数配置
     * */
    public String getValue(String systemId, String name, String defaultValue) {
        return PROJECT_CONFIG_CACHE.get(systemId + SEPARATOR + name, k -> projectConfigService.getValue(systemId, name, defaultValue));
    }

    /**
     * 获取项目参数配置
     * */
    public String getValue(String systemId, String name) {
        return PROJECT_CONFIG_CACHE.get(systemId + SEPARATOR + name, k -> projectConfigService.getValue(systemId, name, null));
    }


    /**
     * 获取集合配置
     * */
    public <T> List<T> getList(String systemId, String name, List<T> defaultValue) {
        String value = getValue(systemId, name, JSON.toJSONString(defaultValue));
        return JSON.parseObject(value, new TypeReference<>() {
        });
    }

    /**
     * 获取集合配置
     * */
    public <T> List<T> getList(String systemId, String name) {
        String value = getValue(systemId, name, null);
        return JSON.parseObject(value, new TypeReference<>() {
        });
    }

    /**
     * 获取 Map 配置
     * */
    public <K,V> Map<K, V> getMap(String systemId, String name, Map<K, V> defaultValue) {
        String value = getValue(systemId, name, JSON.toJSONString(defaultValue));
        return JSON.parseObject(value, new TypeReference<>() {
        });
    }
    /**
     * 获取 Map 配置
     * */
    public <K,V> Map<K, V> getMap(String systemId, String name) {
        String value = getValue(systemId, name, null);
        return JSON.parseObject(value, new TypeReference<>() {
        });
    }
}
