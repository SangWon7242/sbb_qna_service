package com.exam.sbb.answer;


import com.exam.sbb.question.Question;
import com.exam.sbb.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;
  private LocalDateTime modifyDate;

  @ManyToOne
  private Question question;

  @ManyToOne
  private SiteUser author;

  @ManyToMany
  Set<SiteUser> voter;
}
