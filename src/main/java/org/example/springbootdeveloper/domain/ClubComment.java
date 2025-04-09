package org.example.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//같은 동아리에서 동일한 ip로 1번만 댓글 가능
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"clubId", "ipAddress"})})
public class ClubComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //고유 id
    private Long clubId;        //동아리 id
    private String ipAddress;   //ip주소
    private String author;      //작성자
    private String content;     //댓글 내용
    private String password;    //비밀번호
}
