package com.example.projectwebbackend.repository;

import com.example.projectwebbackend.entity.Comment;
import com.example.projectwebbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByUser(User user);

    List<Comment> findAllByAdmin_Adminid(Long id); }
