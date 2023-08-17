package com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Service.inter;

import com.api.studyprojectjudgeserver.Domain.Jenkins.Dto.JenkinsDto;

public interface CommandJenkinsService {
    /**
     * Jenkins 에 Item 을 생성하는데 쓰이는 메서드 입니다.
     * @param dto
     * @return JenkinsDto
     */
    JenkinsDto createItem(JenkinsDto dto);

    /**
     * Jenkins 에 생성한 Item 을 지울 때 쓰이는 메서드 입니다.
     * @param dto
     * @return
     */
    JenkinsDto deleteItem(JenkinsDto dto);

    /**
     * Jenkins 에 생성한 Item 을 수정할 때 쓰이는 메서드 입니다.
     */
    JenkinsDto editItem(JenkinsDto dto);
}
