package com.api.studyprojectjudgeserver.Domain.Member.Repository;

import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import org.springframework.data.repository.Repository;

/**
 * 회원 테이블에 JPA로 접근 가능한 인터페이스 입니다.
 * @author 황시준
 * @since  1.0
 */
public interface JpaMemberRepository extends Repository<MemberEntity, Long>, CommandMemberRepository {

}

