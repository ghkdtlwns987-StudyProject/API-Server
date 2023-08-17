package com.api.studyprojectjudgeserver.Domain.Project.Repository;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;

import java.util.Optional;

public interface QueryProjectRepository {
    Optional<ProjectEntity> findById(Long id);
    boolean existsServiceByServiceName(String serviceName);
}
