package com.site.blog.my.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Setter
@Getter
public class BlogConfig {
    private String configName;

    private String configValue;

    private Date createTime;

    private Date updateTime;

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }
}