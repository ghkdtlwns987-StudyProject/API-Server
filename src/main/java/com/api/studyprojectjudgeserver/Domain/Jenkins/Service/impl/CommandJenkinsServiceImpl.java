package com.api.studyprojectjudgeserver.Domain.Jenkins.Service.impl;

import com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Config.impl.GenerateConfigConfigXmlImpl;
import com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Operation.JenkinsRest;
import com.api.studyprojectjudgeserver.Domain.Jenkins.Factory.ConfigFactory;
import com.api.studyprojectjudgeserver.Domain.Jenkins.Service.inter.CommandJenkinsService;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.JobResponseDto;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.ProjectDto;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ServiceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommandJenkinsServiceImpl implements CommandJenkinsService {
    private final JenkinsRest jenkinsRest;
    private final ConfigFactory configFactory;
    private final GenerateConfigConfigXmlImpl generateConfigXmlImpl;
    @Override
    @Transactional
    public JobResponseDto createItem(ProjectEntity project) throws Exception {
        if(project.getBuildPackage().equals("GRADLE")) {
            configFactory.GradleConfigXml(project);
        }
        if(project.getBuildPackage().equals("MAVEN")){
            configFactory.MavenConfigXml(project);
        }

        jenkinsRest.createJob(project);
        project.updateServiceStatus(ServiceStatus.READY);
        return JobResponseDto.fromEntity(project);
    }

    @Override
    public ProjectDto deleteItem(ProjectEntity project) {
        return null;
    }

    @Override
    public ProjectDto editItem(ProjectEntity project) {
        return null;
    }

    @Override
    public ProjectDto runItem(ProjectEntity project) {
        ResponseEntity<Void> response = jenkinsRest.runJob(project);
        if(response.getStatusCode().is2xxSuccessful()){
            project.updateServiceStatus(ServiceStatus.BUILDING);
        }else {
            project.updateServiceStatus(ServiceStatus.BUILD_FAILED);
        }
        return ProjectDto.fromEntity(project);
    }

    @Override
    public ProjectDto getItemStatus(ProjectEntity project) {
        ResponseEntity<Void> response = jenkinsRest.getLastBuild(project);
        if(response.getStatusCode().is2xxSuccessful()){
            project.updateServiceStatus(ServiceStatus.SUCCEED);
        }else{
            project.updateServiceStatus(ServiceStatus.FAILED);
        }

        return ProjectDto.fromEntity(project);
    }
}
