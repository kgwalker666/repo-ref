package com.site.blog.my.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class BlogTagRelation {
    private Long relationId;

    private Long blogId;

    private Integer tagId;

    private Date createTime;
}