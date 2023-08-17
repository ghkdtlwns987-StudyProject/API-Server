package com.api.studyprojectjudgeserver.Domain.Project.Repository;

import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import org.springframework.data.repository.Repository;

/**
 * 프로젝트 테이블에 JPA 로 접근 가능한 인터페이스 입니다.
 * @author 황시준
 * @since  1.0
 */
public interface JpaProjectRepository extends Repository<MemberEntity, Long>, CommandProjectRepository {
}
