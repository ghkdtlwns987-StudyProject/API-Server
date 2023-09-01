package com.api.studyprojectjudgeserver.Domain.Project.Service.Impl;


import com.api.studyprojectjudgeserver.Domain.Jenkins.Service.inter.CommandJenkinsService;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.JobResponseDto;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.ProjectDto;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ServiceStatus;
import com.api.studyprojectjudgeserver.Domain.Project.Repository.CommandProjectRepository;
import com.api.studyprojectjudgeserver.Domain.Project.Service.inter.CommandProjectService;
import com.api.studyprojectjudgeserver.Domain.Project.Service.inter.QueryProjectService;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Service.inter.QueryMemberService;
import com.api.studyprojectjudgeserver.Global.Error.Exception.Jenkins.ServiceNameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 프로젝트관련 정보를 다루는 서비스 입니다.
 *
 * @since : 1.0
 * @author : 황시준
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CommandProjectServiceImpl implements CommandProjectService {
    private final QueryMemberService queryMemberService;
    private final QueryProjectService queryProjectService;
    private final CommandProjectRepository commandProjectRepository;
    private final CommandJenkinsService commandJenkinsService;
    @Transactional
    public JobResponseDto createProject(ProjectDto createJobRequestDto) throws Exception{
        if(queryProjectService.existsServiceName(createJobRequestDto.getServiceName())){
           throw new ServiceNameAlreadyExistsException();
        }

        MemberEntity memberEntity = queryMemberService.findMemberByEmail(createJobRequestDto.getEmail());
        ProjectEntity projectEntity = createJobRequestDto.toEntity(memberEntity);
        commandProjectRepository.save(projectEntity);
        commandJenkinsService.createItem(projectEntity);
        commandJenkinsService.runItem(projectEntity);
        return JobResponseDto.fromEntity(projectEntity);
    }

    @Override
    public ProjectDto getAllProjectStatus(String loginId, ProjectDto projectDto) throws Exception {
        return null;
    }


    @Override
    public ProjectDto getProjectStatus(String serviceName, ProjectDto projectDto) throws Exception {
        ProjectEntity project = queryProjectService.findProjectByServiceName(serviceName);
        projectDto = commandJenkinsService.getItemStatus(project);
        return projectDto;
    }


    @Override
    public ProjectDto runProject(String loginId, ProjectDto projectDto) throws Exception {
        MemberEntity memberEntity = queryMemberService.findMemberByEmail(loginId);
        ProjectEntity projectEntity = projectDto.toEntity(memberEntity);
        commandJenkinsService.runItem(projectEntity);
        return ProjectDto.fromEntity(projectEntity);
    }

    @Transactional
    public ProjectDto updateStatus(Long projectId, ServiceStatus projectStatus) throws Exception{
        ProjectEntity projectEntity = queryProjectService.findProjectById(projectId);
        projectEntity.updateServiceStatus(projectStatus);
        return ProjectDto.fromEntity(projectEntity);
    }

}