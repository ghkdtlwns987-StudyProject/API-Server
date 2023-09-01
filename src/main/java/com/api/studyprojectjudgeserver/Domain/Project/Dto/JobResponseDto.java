package com.api.studyprojectjudgeserver.Domain.Project.Dto;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ServiceStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class JobResponseDto {
    private String email;
    private int servicePort;
    private String githubUrl;
    private String serviceName;
    private String resultUrl;
    private String message;
    private String buildPackage;
    private String description;
    private String command;
    private String branch;
    private ServiceStatus serviceStatus;
    private LocalDateTime deployAt;

    public static JobResponseDto fromEntity(ProjectEntity projectEntity){
        JobResponseDtoBuilder builder =
                JobResponseDto
                        .builder()
                        .email(projectEntity.getEmail())
                        .servicePort(projectEntity.getServicePort())
                        .githubUrl(projectEntity.getGithubUrl())
                        .serviceName(projectEntity.getServiceName())
                        .servicePort(projectEntity.getServicePort())
                        .message("Request Success")
                        .serviceStatus(projectEntity.getStatus())
                        .resultUrl(projectEntity.getDeployUrl())
                        .buildPackage(projectEntity.getBuildPackage())
                        .description(projectEntity.getDescription())
                        .command(projectEntity.getCommand())
                        .branch(projectEntity.getBranch())
                        .deployAt(LocalDateTime.now());
        return builder.build();
    }
}
