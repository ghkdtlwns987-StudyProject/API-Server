package com.api.studyprojectjudgeserver.Domain.Member.Repository;

import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;

import java.lang.reflect.Member;
import java.util.Optional;

/**
 * 회원 등록 및 수정 관련 Repository
 * @author 황시준
 * @since  1.0
 */
public interface CommandMemberRepository {
    /**
     * 회원에 대한 데이터를 등록합니다.
     * @param member
     * @return MemberEntity
     * @author 황시준
     * @since  1.0
     */
    MemberEntity save(MemberEntity member);

    /**
     * 회원을 삭제하는 기능입니다.
     * @param member
     * @return MemberEntity
     * @author 황시준
     * @since  1.0
     */
    MemberEntity delete(MemberEntity member);
}
