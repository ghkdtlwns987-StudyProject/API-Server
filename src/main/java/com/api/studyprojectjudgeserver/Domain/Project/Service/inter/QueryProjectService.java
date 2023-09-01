package com.api.studyprojectjudgeserver.Domain.Project.Service.inter;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;

public interface QueryProjectService {
    /**
     * Promary key로 프로젝트를 검색하는 기능입니다.
     * @param id
     * @return
     */
    ProjectEntity findProjectById(Long id);

    /**
     * serviceName으로 프로젝트를 검색하는 기능입니다.
     * @param serviceName
     * @return
     */
    ProjectEntity findProjectByServiceName(String serviceName);
    boolean existsServiceName(String serviceName);
}
