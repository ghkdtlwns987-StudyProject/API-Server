package com.api.studyprojectjudgeserver.Domain.Member.Service.inter;

import com.api.studyprojectjudgeserver.Domain.Member.Dto.MemberDto;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;


public interface QueryMemberService {
    /**
     * 회원을 primary key로 조회 하기 위한 메서드 입니다.
     *
     * @param id member의 primary key
     * @return 회원 조회 결과
     * @author 황시준
     * @since 1.0
     */
    MemberEntity findMemberById(long id);

    /**
     * 이메일이 존재하는지 확인하는 메서드 입니다
     * @param email
     * @return boolean
     * @author 황시준
     * @since  1.0
     */
    boolean memberExistsByEmail(String email);

    /**
     * 이메일로 데이터를 조회하는 메서드 입니다
     * @param email
     * @return MemberEntity
     * @author 황시준
     * @since  1.0
     */

    MemberEntity findMemberByEmail(String email);

    /**
     * 이메일로 사용자에 대한
     * @param email
     * @return
     * @throws Exception
     */
    MemberDto getMemberInfo(String email) throws Exception;

}
