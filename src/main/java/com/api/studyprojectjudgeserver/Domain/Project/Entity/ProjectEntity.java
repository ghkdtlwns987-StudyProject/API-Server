package com.api.studyprojectjudgeserver.Domain.Project.Entity;

import com.api.studyprojectjudgeserver.Domain.Member.Entity.BaseTimeEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Global.Error.Exception.Jenkins.InvalidPortRagneException;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@Table(name = "project")
public class ProjectEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Pattern(regexp = "^[a-z0-9]*$", message = "Only lowercase letters or numbers are allowed")
    @Column(name = "serviceName", nullable = false)
    private String serviceName;

    @Column(name = "githubUrl", nullable = false)
    private String githubUrl;

    @Column(name = "branch", nullable = false)
    private String branch;

    @Column(name = "servicePort", nullable = false)
    private int servicePort;

    @Column(name = "package", nullable = false)
    private String buildPackage;

    @Enumerated(EnumType.STRING)
    @Column(name = "serviceStatus", nullable = false)
    private ServiceStatus status;

    @Column(name = "deployUrl", nullable = true)
    private String deployUrl;

    @Column(name = "description", nullable = true)
    private String description;         // 프로젝트 설명(관리자 확인용)

    @Column(name = "command", nullable = false)
    private String command;             // 프로젝트를 빌드하는데 쓰이는 명령어

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberEntity;

    /**
     * "serviceName_123452135213"
     * @return
     */

    public void update(String serviceName, String githubUrl, int servicePort, ServiceStatus status, String buildPackage){
        this.serviceName = serviceName;
        this.githubUrl = githubUrl;
        this.servicePort = servicePort;
        this.status = status;
        this.buildPackage = buildPackage;
    }

    public void updatePort(int servicePort){
        if(servicePort < 0){
            throw new InvalidPortRagneException();
        }
        this.servicePort = servicePort;
    }

    public void updateServiceStatus(ServiceStatus status){
        this.status = status;
    }
}
