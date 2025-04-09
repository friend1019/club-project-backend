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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"clubId"})})  // clubId만 unique하게 설정
public class ClubLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // 고유 id
    private Long clubId;    // 동아리 id
}
