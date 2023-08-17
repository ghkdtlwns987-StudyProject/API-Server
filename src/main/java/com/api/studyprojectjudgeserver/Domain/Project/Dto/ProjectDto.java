package com.api.studyprojectjudgeserver.Domain.Project.Dto;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ServiceStatus;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import lombok.*;

/**
 * 프로젝트 정보를 저장할 때 쓰이는 Dto 입니다.
 * 해당 Dto 는 Entity 에 저장합니다.
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectDto {
    private Long id;
    private String email;
    private String serviceName;
    private String githubUrl;
    private int servicePort;
    private String buildPackage;
    private ServiceStatus status;
    private String deployUrl;
    private MemberEntity memberEntity;

    public static ProjectDto fromEntity(ProjectEntity project){
        return new ProjectDto(
                project.getId(),
                project.getEmail(),
                project.getServiceName(),
                project.getGithubUrl(),
                project.getServicePort(),
                project.getBuildPackage(),
                project.getStatus(),
                project.getDeployUrl(),
                project.getMemberEntity()
        );
    }

    public ProjectEntity toEntity(){
        return ProjectEntity.builder()
                .id(id)
                .email(email)
                .serviceName(serviceName)
                .githubUrl(githubUrl)
                .servicePort(servicePort)
                .buildPackage(buildPackage)
                .status(status)
                .deployUrl(deployUrl)
                .memberEntity(memberEntity)
                .build();
    }

    public ProjectEntity toEntity(MemberEntity memberEntity){
        return ProjectEntity.builder()
                .email(getEmail())
                .serviceName(getServiceName())
                .githubUrl(getGithubUrl())
                .buildPackage(getBuildPackage())
                .servicePort(getServicePort())
                .status(ServiceStatus.CREATING)
                .deployUrl("Project Creating")
                .memberEntity(memberEntity)
                .build();
    }}
