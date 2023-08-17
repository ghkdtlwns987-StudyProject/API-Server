package com.api.studyprojectjudgeserver.Domain.Member.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignupResponseDto {
    private String userId;
    private String email;
    private String nickname;
    private String name;
    private LocalDateTime createAt;
    private List<String> roles;
    public static SignupResponseDto fromEntity(MemberEntity member){
        SignupResponseDto builder =
                SignupResponseDto
                        .builder()
                        .userId(member.getUserId())
                        .email(member.getEmail())
                        .nickname(member.getNickname())
                        .name(member.getName())
                        .createAt(member.getCreatedTime())
                        .roles(member.getRoles())
                .build();
        return builder;
    }
}
