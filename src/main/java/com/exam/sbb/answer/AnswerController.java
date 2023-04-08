package com.exam.sbb.answer;

import com.exam.sbb.question.Question;
import com.exam.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {
  private final QuestionService questionService;

  @PostMapping("/create/{id}")
  public String createAnswer(Model model, @PathVariable("id") Integer id, String content) {
    Question question = this.questionService.getQuestion(id);
    // 답변 등록 시작
    // 답변 등록 끝

    return "redirect:/question/detail/%d".formatted(id);
  }
}