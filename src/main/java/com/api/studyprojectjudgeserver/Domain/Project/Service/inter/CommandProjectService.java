package com.api.studyprojectjudgeserver.Domain.Project.Service.inter;

import com.api.studyprojectjudgeserver.Domain.Project.Dto.JobResponseDto;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.ProjectDto;

public interface CommandProjectService {
    /**
     * 프로젝트를 생성하는 메서드 입니다.
     * @param createJobRequestDto
     * @return ProjectDto
     * @throws Exception
     * @author 황시준
     * @since  1.0
     */
    //ProjectDto createProject(ProjectDto createJobRequestDto) throws Exception;
    JobResponseDto createProject(ProjectDto createJobRequestDto) throws Exception;

    /**
     * 사용자가 생성한 프로젝트의 모든 상태를 확인하는 메서드 입니다.
     *
     * @param projectDto
     * @return ProjectDto
     * @throws Exception
     */
    ProjectDto getAllProjectStatus(String loginId, ProjectDto projectDto) throws Exception;

    /**
     * 사용자가 생성한 프로젝트중 하나의 프로젝트를 검색하는 메서드 입니다.
     * @param serviceName
     * @param projectDto
     * @return ProjectDto
     * @throws Exception
     */
    ProjectDto getProjectStatus(String serviceName, ProjectDto projectDto) throws Exception;

    /**
     * 프로젝트를 실행시키는 메서드 입니다.
     * @param projectDto
     * @return
     * @throws Exception
     */
    ProjectDto runProject(String loginId, ProjectDto projectDto) throws Exception;
}
