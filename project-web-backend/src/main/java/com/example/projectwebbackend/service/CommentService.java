package com.example.projectwebbackend.service;

import com.example.projectwebbackend.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComment();

    List<Comment> getCommentByAdminId(Long id);
}
