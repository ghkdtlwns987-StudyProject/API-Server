package com.api.studyprojectjudgeserver.Domain.Jenkins.Dto;

import com.api.studyprojectjudgeserver.Domain.Project.Dto.ProjectDto;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import lombok.*;

/**
 * 해당 Dto 는 Jenkins와 통신할 때 쓰이는 Dto 입니다.
 * 해당 Dto 는 ProjectDto를 가져와 데이터를 추가합니다.
 * 해당 Dto 는 Entity에 저장하지 않는다는 특징을 지닙니다.
 * @author 황시준
 * @since  1.0
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JenkinsDto {
    private String description;         // 프로젝트 설명(관리자 확인용)
    private String command;             // 프로젝트를 빌드하는데 쓰이는 명령어
    private String outputfile;          // createItem에 필요한 config.xml 파일 명 지정

    public static JenkinsDto of(ProjectEntity project, String description, String command, String outputfile){
        return new JenkinsDto(description, command, outputfile);
    }
}
