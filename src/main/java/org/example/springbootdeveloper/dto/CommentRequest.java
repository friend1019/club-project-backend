package org.example.springbootdeveloper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private Long clubId;
    private String author;
    private String content;
    private String password; // 비밀번호 추가
}
