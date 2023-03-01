package com.site.blog.my.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
public class BlogComment {
    private Long commentId;

    private Long blogId;

    private String commentator;

    private String email;

    private String websiteUrl;

    private String commentBody;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentCreateTime;

    private String commentatorIp;

    private String replyBody;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date replyCreateTime;

    private Byte commentStatus;

    private Byte isDeleted;

    public void setCommentator(String commentator) {
        this.commentator = commentator == null ? null : commentator.trim();
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl == null ? null : websiteUrl.trim();
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody == null ? null : commentBody.trim();
    }


    public void setCommentatorIp(String commentatorIp) {
        this.commentatorIp = commentatorIp == null ? null : commentatorIp.trim();
    }

    public void setReplyBody(String replyBody) {
        this.replyBody = replyBody == null ? null : replyBody.trim();
    }

}