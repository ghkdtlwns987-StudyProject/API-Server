package com.api.studyprojectjudgeserver.Domain.Jenkins.Service.inter;

import com.api.studyprojectjudgeserver.Domain.Project.Dto.JobResponseDto;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.ProjectDto;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;

import java.io.IOException;

public interface CommandJenkinsService {
    /**
     * Jenkins 에 Item 을 생성하는데 쓰이는 메서드 입니다.
     *
     * @param project
     * @return JenkinsDto
     */
    JobResponseDto createItem(ProjectEntity project) throws IOException, Exception;


    /**
     * Jenkins 에 생성한 Item 을 지울 때 쓰이는 메서드 입니다.
     * @param project
     * @return ProjectDto
     */
    ProjectDto deleteItem(ProjectEntity project);

    /**
     * Jenkins 에 생성한 Item 을 수정할 때 쓰이는 메서드 입니다.
     * @param project
     * @return ProjectDto
     */
    ProjectDto editItem(ProjectEntity project);

    /**
     * Jenkins 에 생성한 Item 을 실행할 때 쓰이는 메서드 입니다.
     * 만약 빌드가 됬다면 200을 응답받으며 상태가 Build 가 됩니다.
     * 만약 빌드에 들어가지 못했다면 400을 응답받으면 상태가 Failed 가 됩니다.
     * @param project
     * @return JenkinsDto
     */
    ProjectDto runItem(ProjectEntity project);

    /**
     * Jenkins 생성한 Item 상태를 확인하는 메서드 입니다.
     * 상태를 확인할 때 lastStableBuild(마지막 빌드 상태 확인) 요청을 통해 프로젝트 상태를 확인합니다.
     * 만약 마지막 빌드에 성공했다면 200번대 코드가 반환되고
     * 만약 마지막 빌드에 실패했다면 400번대 코드가 반환됩니다.
     * 만약 200 코드가 반환되었다면 상태가 Succeed 가 됩니다.
     * 그렇지 않으면 빌드에 실패했다는 뜻으로 상태가 Failed 가 됩니다.
     * @param project
     * @return
     */
    ProjectDto getItemStatus(ProjectEntity project);
}
