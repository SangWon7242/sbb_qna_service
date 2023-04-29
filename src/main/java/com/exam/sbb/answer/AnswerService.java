package com.exam.sbb.answer;

import com.exam.sbb.DataNotFoundException;
import com.exam.sbb.question.Question;
import com.exam.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
  private final AnswerRepository answerRepository;

  public void create(Question question, String content, SiteUser author) {
    Answer answer = new Answer();
    answer.setContent(content);
    answer.setCreateDate(LocalDateTime.now());
    answer.setAuthor(author);
    question.addAnswer(answer);

    answerRepository.save(answer);
  }

  public Answer getAnswer(Long id) {
    return answerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("answer not found"));
  }
}
