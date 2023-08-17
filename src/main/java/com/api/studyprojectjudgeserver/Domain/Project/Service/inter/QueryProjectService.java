package com.api.studyprojectjudgeserver.Domain.Project.Service.inter;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;

import java.util.Optional;

public interface QueryProjectService {
    Optional<ProjectEntity> findById(Long id);

    boolean existsServiceName(String serviceName);
}
