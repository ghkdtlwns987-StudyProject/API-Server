package com.api.studyprojectjudgeserver.Domain.Jenkins.Factory;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;

public interface ConfigFactory {
    /**
     * Gradle 을 Build 합니다.
     * @param project
     */
    void GradleConfigXml(ProjectEntity project) throws Exception;

    /**
     * Maven 을 Build 합니다.
     * @param project
     */
    void MavenConfigXml(ProjectEntity project) throws Exception;
}
