package com.dwalter.bookingsystem.functionality.comment.repository;

import com.dwalter.bookingsystem.functionality.comment.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.roomId = :id")
    List<Comment> findAllByRoomId(Long id);
}
