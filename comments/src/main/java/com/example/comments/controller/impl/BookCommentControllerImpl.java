package com.example.comments.controller.impl;

import com.example.comments.controller.BookCommentController;
import com.example.comments.service.BookCommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BookCommentControllerImpl implements BookCommentController {

    @Resource
    public BookCommentService bookCommentService;


}
