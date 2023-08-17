package com.api.studyprojectjudgeserver.Domain.Project.Entity;

import com.api.studyprojectjudgeserver.Domain.Member.Entity.BaseTimeEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Global.Error.Exception.Jenkins.InvalidPortRagneException;
import lombok.*;

import javax.persistence.*;

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

    @Column(name = "serviceName", nullable = false)
    private String serviceName;

    @Column(name = "githubUrl", nullable = false, length = 50)
    private String githubUrl;

    @Column(name = "servicePort", nullable = false)
    private int servicePort;

    @Column(name = "package", nullable = false)
    private String buildPackage;

    @Enumerated(EnumType.STRING)
    @Column(name = "serviceStatus", nullable = false)
    private ServiceStatus status;

    @Column(name = "deployUrl", nullable = true)
    private String deployUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberEntity;

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
        //this.status.getId() = status;
        //setStatus(status);
        this.setStatus(status);
    }
}
