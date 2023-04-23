package com.exam.sbb.question;

import com.exam.sbb.DataNotFoundException;
import com.exam.sbb.answer.AnswerForm;
import com.exam.sbb.user.SiteUser;
import com.exam.sbb.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Enumeration;
import java.util.Iterator;

@RequestMapping("/question")
@Controller
@RequiredArgsConstructor // 생성자 주입
// 컨트롤러는 Repository가 있는지 몰라야 한다.
// 서비스는 브라우저라는 것이 이 세상에 존재하는지 몰라야 한다.
// 세션은 어디서 다뤄야 할까요?
  // 컨트롤러
  // 서비스
// 리포지터리는 서비스가 이 세상에 있는지 몰라야 한다.
// 서비스는 컨트롤러를 몰라야 한다.
// DB는 리포지터리를 몰라야 한다.
// SPRING DATA JPA는 MySQL을 몰라야 한다.
 // SPRING DATA JPA(리포지터리) -> JPA -> 하이버네이트 -> JDBC -> MySQL Driver -> MySQL
public class QuestionController {

  // @Autowired 필드 주입
  private final QuestionService questionService;
  private final UserService userService;


  @GetMapping("/list")
  // 이 자리에 @ResponseBody가 없으면 resources/templates/question_list.html 파일을 뷰로 삼는다.
  public String list(HttpSession session, Model model, @RequestParam(defaultValue="0") int page) {

    Object o = session.getAttribute("SPRING_SECURITY_CONTEXT");
    System.out.println(o);

    Page<Question> paging = questionService.getList(page);

    // 미리 실행된 question_list.html에서
    // questionList라는 이름으로 questionList 변수를 사용할 수 있다.
    model.addAttribute("paging", paging);
    return "question_list";
  }

  @GetMapping("/detail/{id}")
  public String detail(Model model, @PathVariable long id, AnswerForm answerForm) {
    Question question = questionService.getQuestion(id);

    model.addAttribute("question", question);

    return "question_detail";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/modify/{id}")
  public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
    Question question = this.questionService.getQuestion(id);

    if(question == null) {
      throw new DataNotFoundException("%d번 질문은 존재하지 않습니다.".formatted(id));
    }

    if(!question.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
    }

    questionForm.setSubject(question.getSubject());
    questionForm.setContent(question.getContent());
    return "question_form";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/create")
  public String questionCreate(QuestionForm questionForm) {
    return "question_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/create")
  public String questionCreate(Principal principal, Model model, @Valid QuestionForm questionForm, BindingResult bindingResult) {

    if(bindingResult.hasErrors()) {
      return "question_form";
    }

    SiteUser siteUser = userService.getUser(principal.getName());

    questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);

    return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
  }
}