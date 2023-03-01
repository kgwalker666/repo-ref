package com.site.blog.my.core.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogTagCount {
    private Integer tagId;

    private String tagName;

    private Integer tagCount;
}
