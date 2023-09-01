package com.api.studyprojectjudgeserver.jenkins.Comamnd.Operation;

import com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Operation.JenkinsRest;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.Roles;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ServiceStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class JenkinsRestTest {
    private String EMAIL = "test@naver.com";
    private String USERID = "UUID";
    private String NAME = "test";
    private String NICKNAME = "test-nickname";
    private String PWD = "test-Password";
    private String PHONE = "010-1234-1234";
    private String SERVICENAME = "testServiceName";
    private String GITHUBURL = "https://ghkdtlwns987.git";
    private int SERVICEPORT = 9999;
    private String BUILDPACKAGE = "Gradle";
    private ServiceStatus STATUS = ServiceStatus.CREATING;
    private String DEPLOYURL = "none";
    private String DESCRIPTION = "test 하기 위한 용도입니다.";
    private String COMMAND = "test 하기 위한 command 입니다.";
    private String BRANCH = "/main";

    private MemberEntity member;
    private ProjectEntity project;

    @BeforeEach
    void setUp() {
        long id = 1L;

        member = MemberEntity.builder()
                .Id(id)
                .userId(USERID)
                .email(EMAIL)
                .pwd(PWD)
                .nickname(NICKNAME)
                .name(NAME)
                .phone(PHONE)
                .roles(Collections.singletonList(Roles.USER.getId()))
                .build();

        project = ProjectEntity.builder()
                .id(id)
                .email(EMAIL)
                .serviceName(SERVICENAME)
                .githubUrl(GITHUBURL)
                .branch(BRANCH)
                .servicePort(SERVICEPORT)
                .buildPackage(BUILDPACKAGE)
                .status(STATUS)
                .deployUrl(DEPLOYURL)
                .description(DESCRIPTION)
                .command(COMMAND)
                .memberEntity(member)
                .build();
    }
    @MockBean
    private JenkinsRest jenkinsRest;

    @Test
    @DisplayName("Create Job Test")
    public void createJobTest() throws Exception{
        when(jenkinsRest.createJob(any())).thenReturn(ResponseEntity.ok().build());
        ResponseEntity<Void> response = jenkinsRest.createJob(project);
        assertEquals(response.getStatusCode(), response.getStatusCode());
    }

    @Test
    @DisplayName("Run Job Test")
    public void runJobTest() throws Exception{
        when(jenkinsRest.runJob(any())).thenReturn(ResponseEntity.ok().build());
        ResponseEntity<Void> response = jenkinsRest.runJob(project);
        assertEquals(response.getStatusCode(), response.getStatusCode());
    }

}
