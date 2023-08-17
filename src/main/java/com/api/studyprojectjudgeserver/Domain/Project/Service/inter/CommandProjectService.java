package com.api.studyprojectjudgeserver.Domain.Project.Service.inter;

import com.api.studyprojectjudgeserver.Domain.Project.Dto.CreateJobResponseDto;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.ProjectDto;

public interface CommandProjectService {
    /**
     * 프로젝트를 생성하는 메서드 입니다.
     * @param createJobRequestDto
     * @return CreateJobResponseDto
     * @throws Exception
     * @author 황시준
     * @since  1.0
     */
    CreateJobResponseDto createProject(ProjectDto createJobRequestDto) throws Exception;
}
