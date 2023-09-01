package com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Config.inter;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;

public interface GenerateConfigXml {
    String gradleXml(ProjectEntity project) throws Exception;
    String mavenXml(ProjectEntity project) throws Exception;
}
