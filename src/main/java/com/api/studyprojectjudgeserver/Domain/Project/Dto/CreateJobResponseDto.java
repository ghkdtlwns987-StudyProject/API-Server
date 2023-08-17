package com.api.studyprojectjudgeserver.Domain.Project.Dto;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateJobResponseDto {
    private String email;
    private int servicePort;
    private String githubUrl;
    private String serviceName;
    private String resultUrl;
    private String message;
    private String buildPackage;
    private LocalDateTime deployAt;

    public static CreateJobResponseDto fromEntity(ProjectEntity projectEntity){
        CreateJobResponseDtoBuilder builder =
                CreateJobResponseDto
                        .builder()
                        .email(projectEntity.getEmail())
                        .servicePort(projectEntity.getServicePort())
                        .githubUrl(projectEntity.getGithubUrl())
                        .serviceName(projectEntity.getServiceName())
                        .servicePort(projectEntity.getServicePort())
                        .message("Create Job Request Success")
                        .resultUrl(projectEntity.getDeployUrl())
                        .buildPackage(projectEntity.getBuildPackage())
                        .deployAt(LocalDateTime.now());
        return builder.build();
    }
}
