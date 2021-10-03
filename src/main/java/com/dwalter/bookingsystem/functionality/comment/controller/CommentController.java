package com.dwalter.bookingsystem.functionality.comment.controller;

import com.dwalter.bookingsystem.functionality.comment.controller.dto.CommentDto;
import com.dwalter.bookingsystem.functionality.comment.controller.dto.CreateCommentDto;
import com.dwalter.bookingsystem.functionality.comment.exceptions.CommentNotFoundByIdException;
import com.dwalter.bookingsystem.functionality.comment.mapper.CommentMapper;
import com.dwalter.bookingsystem.functionality.comment.model.Comment;
import com.dwalter.bookingsystem.functionality.comment.service.CommentDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentDbService commentDbService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> get() {
        log.info("Get comments!");
        List<CommentDto> comments = CommentMapper.mapToCommentsDto(commentDbService.getAll());
        return new ResponseEntity<>(comments, OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<CommentDto>> get(@RequestParam(defaultValue = "0", required = false) final int page, @RequestParam(defaultValue = "10", required = false) final int size) {
        log.info("Get paginated comments!");
        List<CommentDto> comments = CommentMapper.mapToCommentsDto(commentDbService.getAll(page, size));
        return new ResponseEntity<>(comments, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> get(@PathVariable final Long id) {
        log.info("get comments by id: " + id);
        CommentDto comment = CommentMapper.mapToCommentDto((commentDbService.getById(id))
                .orElseThrow(() -> new CommentNotFoundByIdException(id)));
        return new ResponseEntity<>(comment, OK);
    }


    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> create(@RequestBody final CreateCommentDto createCommentDto) {
        Comment comment = commentDbService.create(CommentMapper.mapFromCreateToComment(createCommentDto));
        return new ResponseEntity<>(comment, CREATED);
    }

    @PutMapping
    public ResponseEntity<CommentDto> update(@RequestBody final CommentDto commentDto) {
        CommentDto updatedComment = commentDbService.update(commentDto);
        return new ResponseEntity<>(updatedComment, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Long id) {
        commentDbService.delete(id);
        return new ResponseEntity<>("Comment deleted Succesfully!", OK);
    }

}
