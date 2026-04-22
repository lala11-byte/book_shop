package com.example.comments.mapper;

import com.example.comments.pojo.BookComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BookCommentMapper {
   List<BookComment> selectComment(String isbn);
}
