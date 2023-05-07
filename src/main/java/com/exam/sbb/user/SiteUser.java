package com.exam.sbb.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import javax.persistence.*;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class SiteUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;

  private String password;

  @Column(unique = true)
  private String email;

  public SiteUser(long id) {
    this.id = id;
  }

}
