package com.api.studyprojectjudgeserver.jenkins.Adapter;

import com.api.studyprojectjudgeserver.Domain.Jenkins.Adapter.JenkinsAdapter;
import com.api.studyprojectjudgeserver.Domain.Jenkins.Dto.JenkinsDto;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.Roles;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.ProjectDto;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ServiceStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class JenkinsAdapterTest {
    private final String EMAIL = "test@naver.com";
    private final String USERID = "UUID";
    private final String NAME = "test";
    private final String NICKNAME = "test-nickname";
    private final String PWD = "test-Password";
    private final String PHONE = "010-1234-1234";
    private final String SERVICENAME = "testServiceName";
    private final String GITHUBURL = "https://ghkdtlwns987.git";
    private int SERVICEPORT = 9999;
    private String BUILDPACKAGE = "Gradle";
    private ServiceStatus STATUS = ServiceStatus.CREATING;
    private String DEPLOYURL = "none";
    private String DESCRIPTION = "test 하기 위한 용도입니다.";
    private String COMMAND = "test 하기 위한 command 입니다.";
    private String OUTPUTFILE = "./my_config.xml";
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private JenkinsAdapter jenkinsAdapter;

    private MemberEntity member;
    private ProjectEntity project;
    private JenkinsDto jenkinsDto;

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
                .servicePort(SERVICEPORT)
                .buildPackage(BUILDPACKAGE)
                .status(STATUS)
                .deployUrl(DEPLOYURL)
                .memberEntity(member)
                .build();


        jenkinsDto = JenkinsDto.of(project, DESCRIPTION, COMMAND, OUTPUTFILE);
    }

    @DisplayName("Item 생성 테스트")
    @Test
    public void createItem() {
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(responseEntity);
        ResponseEntity<Void> result = jenkinsAdapter.createItem(jenkinsDto, project);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}