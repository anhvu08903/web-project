package com.example.projectwebbackend.dto;

import com.example.projectwebbackend.entity.User;
import lombok.AllArgsConstructor;

import java.util.Date;
@AllArgsConstructor
public class UserCommentationRequest {
    private String content;
    private User user;
    private Date createdAt;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
