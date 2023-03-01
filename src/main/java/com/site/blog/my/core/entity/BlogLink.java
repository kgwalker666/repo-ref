package com.site.blog.my.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
public class BlogLink {
    private Integer linkId;

    private Byte linkType;

    private String linkName;

    private String linkUrl;

    private String linkDescription;

    private Integer linkRank;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }


    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription == null ? null : linkDescription.trim();
    }
}