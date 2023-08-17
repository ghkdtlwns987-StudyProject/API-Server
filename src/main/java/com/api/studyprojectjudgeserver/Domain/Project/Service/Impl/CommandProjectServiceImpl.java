package com.api.studyprojectjudgeserver.Domain.Project.Service.Impl;


import com.api.studyprojectjudgeserver.Domain.Project.Dto.CreateJobResponseDto;
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
import java.util.Optional;

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

    @Transactional
    public CreateJobResponseDto createProject(ProjectDto createJobRequestDto) throws Exception{
        if(queryProjectService.existsServiceName(createJobRequestDto.getServiceName())){
           throw new ServiceNameAlreadyExistsException();
        }

        MemberEntity memberEntity = queryMemberService.findMemberByEmail(createJobRequestDto.getEmail());
        ProjectEntity projectEntity = createJobRequestDto.toEntity(memberEntity);
        commandProjectRepository.save(projectEntity);
        return CreateJobResponseDto.fromEntity(projectEntity);
    }

    @Transactional
    public ProjectDto updateStatus(Long projectId, ServiceStatus projectStatus) throws Exception{
        Optional<ProjectEntity> projectEntity = queryProjectService.findById(projectId);
        projectEntity.orElseThrow().updateServiceStatus(projectStatus);
        return ProjectDto.fromEntity(projectEntity.orElseThrow());
    }
}