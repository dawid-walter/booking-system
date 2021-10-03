package com.dwalter.bookingsystem.functionality.comment.repository;

import com.dwalter.bookingsystem.functionality.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
