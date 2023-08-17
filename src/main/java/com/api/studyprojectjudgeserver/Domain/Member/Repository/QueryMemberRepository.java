package com.api.studyprojectjudgeserver.Domain.Member.Repository;

import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Member 테이블에 있는 데이터를 조회하는 Repository 입니다.
 * @author 황시준
 * @since  1.0
 */
public interface QueryMemberRepository
{
    /**
     * primary key를 통해 회원을 조회 합니다.
     *
     * @param id primary key
     * @return 조회된 회원
     * @author 황시준
     * @since 1.0
     */
    Optional<MemberEntity> findById(Long id);

    /**
     * email로 회원을 조회합니다.
     * @param email
     * @return MemberEntity
     * @author 황시준
     * @since  1.0
     */
    Optional<MemberEntity> findMemberByEmail(String email);

    /**
     * 가입된 이메일의 중복 여부를 판단합니다.
     * @param email
     * @return
     */
    boolean existsMemberByEmail(String email);
}
