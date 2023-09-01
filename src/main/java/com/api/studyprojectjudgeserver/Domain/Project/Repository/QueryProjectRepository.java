package com.api.studyprojectjudgeserver.Domain.Project.Repository;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;

import java.util.Optional;

/**
 * Project 테이블에 있는 데이터를 조회하는 Repository 입니다.
 * @author 황시준
 * @since  1.0
 */
public interface QueryProjectRepository {
    /**
     * Primary Key 를 통해 프로젝트을 조회합니다.
     *
     * @param id primary key
     * @return   조회된 프로젝트
     * @author 황시준
     * @since  1.0
     */
    Optional<ProjectEntity> findById(Long id);

    /**
     * serviceName 으로 프로젝트를 조회합니다.
     * @param serviceName
     * @return 조회된 프로젝트
     * @author 황시준
     * @since  1.0
     */
    Optional<ProjectEntity> findByServiceName(String serviceName);

    /**
     * serviceName으로 현재 등록한 서비스 이름이 있는지 확인합니다.
     * @param serviceName
     * @return 프로젝트 존재 유무
     * @author 황시준
     * @since  1.0
     */
    boolean existsServiceByServiceName(String serviceName);
}
