package com.site.blog.my.core.controller.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SimpleBlogListVO implements Serializable {

    private Long blogId;

    private String blogTitle;
}
