package com.api.studyprojectjudgeserver.Domain.Member.Dto;

import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
/**
 * 회원 엔티티를 위한 dto 클래스입니다.
 *
 * @author 황시준
 * @since 1.0
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberDto {
    private Long id;
    private String userId;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String phone;
    private List<String> roles;
    private LocalDateTime registered;

    public static MemberDto fromEntity(MemberEntity member){
        return new MemberDto(
            member.getId(),
            member.getUserId(),
            member.getEmail(),
            member.getPwd(),
            member.getNickname(),
            member.getName(),
            member.getPhone(),
            member.getRoles(),
                member.getCreatedTime()
        );
    }

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .Id(id)
                .userId(userId)
                .email(email)
                .pwd(password)
                .nickname(nickname)
                .name(name)
                .phone(phone)
                .roles(roles)
                .build();
    }

}
