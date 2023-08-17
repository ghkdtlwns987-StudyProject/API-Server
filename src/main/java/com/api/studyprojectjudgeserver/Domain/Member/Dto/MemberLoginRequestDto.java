package com.api.studyprojectjudgeserver.Domain.Member.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Auth 서버에서 login을 위해 필요한 member 정보를 담은 클래스입니다.
 * @author : 황시준
 * @since  : 1.0
 */
@Getter
@AllArgsConstructor
public class MemberLoginRequestDto {
    private Long id;
    private String name;
    private String nickname;
    private String loginId;
    private String email;
    private String password;
    private List<String> roles;
}
