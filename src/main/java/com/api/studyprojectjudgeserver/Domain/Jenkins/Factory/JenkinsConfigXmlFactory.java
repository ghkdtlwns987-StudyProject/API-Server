package com.api.studyprojectjudgeserver.Domain.Jenkins.Factory;

import com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Config.inter.GenerateConfigXml;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JenkinsConfigXmlFactory implements ConfigFactory {
    private final GenerateConfigXml generateConfigXml;
    @Override
    public void GradleConfigXml(ProjectEntity project) throws Exception{
        generateConfigXml.gradleXml(project);
    }

    @Override
    public void MavenConfigXml(ProjectEntity project) throws Exception{
        generateConfigXml.mavenXml(project);
    }

}
