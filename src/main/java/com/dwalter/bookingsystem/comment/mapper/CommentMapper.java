package com.dwalter.bookingsystem.comment.mapper;

import com.dwalter.bookingsystem.comment.domain.Comment;
import com.dwalter.bookingsystem.comment.domain.CommentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto mapToCommentDto(Comment comment);

    Comment mapToComment(CommentDto commentDto);

    List<CommentDto> mapToCommentsDto(List<Comment> comments);
}
