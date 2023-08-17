package com.api.studyprojectjudgeserver.Domain.Project.Repository;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;

public interface CommandProjectRepository {
    /**
     * 프로젝트에 대한 정보를 등록합니다.
     * @param projectEntity
     * @return ProjectEntity
     * @author 황시준
     * @since  1.0
     */
    ProjectEntity save(ProjectEntity projectEntity);
}
