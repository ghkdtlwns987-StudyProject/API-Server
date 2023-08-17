package com.api.studyprojectjudgeserver.Domain.Member.Service.inter;

import com.api.studyprojectjudgeserver.Domain.Member.Dto.*;

public interface CommandMemberService {
    /**
     * 회원가입 기능입니다.
     * @param signupRequestDto
     * @return SignupResponseDto
     * @author : 황시준
     * @since  : 1.0
     */
    SignupResponseDto signup(SignupRequestDto signupRequestDto) throws Exception;

    /**
     * 회원 수정 기능입니다
     * @param loginId
     * @param dto
     * @return MemberDto
     * @author : 황시준
     * @since  : 1.0
     */
    MemberDto update(String loginId, UpdateMemberDto dto) throws Exception;

    /**
     * 회원 탈퇴 기능입니다.
     * @param loginId
     * @return MemberDto
     * @throws Exception
     * @author : 황시준
     * @since  : 1.0
     */
    MemberDto withdraw(String loginId) throws Exception;
}
