package com.example.projectwebbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Binh_luan")
public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long commentid;

    @Column(name = "Noi dung binh luan")
    private String content;

    @ManyToOne

    @JoinColumn(name = "Ma nguoi dung")
    private User user;

    @ManyToOne

    @JoinColumn(name = "Ma nha xe ")
    private Admin admin;

    @Column(name = "Thoi diem binh luan")
    private Date createdAt;

    public Long getCommentid() {
        return commentid;
    }

    public void setCommentid(Long commentid) {
        this.commentid = commentid;
    }

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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Comment(Long commentid, String content, User user, Admin admin, Date createdAt) {
        this.commentid = commentid;
        this.content = content;
        this.user = user;
        this.admin = admin;
        this.createdAt = createdAt;
    }

    public Comment() {
    }
}
