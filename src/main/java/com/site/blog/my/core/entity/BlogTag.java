package com.site.blog.my.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Setter
@Getter
public class BlogTag {
    private Integer tagId;

    private String tagName;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

}