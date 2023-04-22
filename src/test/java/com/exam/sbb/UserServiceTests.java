package com.exam.sbb;

import com.exam.sbb.answer.AnswerRepository;
import com.exam.sbb.question.Question;
import com.exam.sbb.question.QuestionRepository;
import com.exam.sbb.user.UserRepository;
import com.exam.sbb.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTests {
  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  private AnswerRepository answerRepository;


  @BeforeEach
  void beforeEach() {
    clearData();
    createSampleData();
  }

  public static void createSampleData(UserService userService) {
    userService.create("admin", "admin@test.com", "1234");
    userService.create("user1", "user1@test.com", "1234");
  }

  private void createSampleData() {
    createSampleData(userService);
  }

  public static void clearData(UserRepository userRepository, AnswerRepository answerRepository, QuestionRepository questionRepository) {
    AnswerRepositoryTests.clearData(answerRepository, questionRepository);
    QuestionRepositoryTests.clearData(questionRepository);
    userRepository.deleteAll(); // DELETE FROM site_user;
    userRepository.truncateTable();
  }

  private void clearData() {
    clearData(userRepository, answerRepository, questionRepository);
  }

  @Test
  @DisplayName("회원가입이 가능하다.")
  public void t1() {
    userService.create("user2", "user2@email.com", "1234");
  }

}
