package com.api.studyprojectjudgeserver.jenkins.Comamnd.Service;

import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.Roles;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ServiceStatus;
import com.api.studyprojectjudgeserver.Domain.Project.Repository.QueryProjectRepository;
import com.api.studyprojectjudgeserver.Domain.Project.Service.Impl.QueryProjectServiceImpl;
import com.api.studyprojectjudgeserver.Global.Error.ClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QueryProjectServiceImplTest {
    private String EMAIL = "test@naver.com";
    private String USERID = "UUID";
    private String NAME = "test";
    private String NICKNAME = "test-nickname";
    private String PWD = "test-Password";
    private String PHONE = "010-1234-1234";
    private String SERVICENAME = "testserviceName";
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

    @Mock
    private QueryProjectRepository queryProjectRepository;

    @InjectMocks
    private QueryProjectServiceImpl queryProjectService;

    @Test
    @DisplayName("findProjectById Test")
    public void findProjectByIdTest() {
        when(queryProjectRepository.findById(project.getId())).thenReturn(Optional.of(project));
        ProjectEntity result = queryProjectService.findProjectById(project.getId());
        assertNotNull(result);
        assertEquals(project.getId(), result.getId());
    }

    @Test
    @DisplayName("findProjectById NotFound Test")
    public void findProjectByIdNotFoundTest() {
        when(queryProjectRepository.findById(project.getId())).thenReturn(Optional.empty());
        assertThrows(ClientException.class, () -> queryProjectService.findProjectById(project.getId()));
    }

    @Test
    @DisplayName("existsServiceName Test")
    public void existsServiceNameTest() {
        when(queryProjectRepository.existsServiceByServiceName(project.getServiceName())).thenReturn(true);
        boolean result = queryProjectService.existsServiceName(SERVICENAME);
        assertTrue(result);
    }

    @Test
    @DisplayName("findProjectByServiceName Test")
    public void findProjectByServiceNameTest() {
        when(queryProjectRepository.findByServiceName(project.getServiceName())).thenReturn(Optional.of(project));
        ProjectEntity result = queryProjectService.findProjectByServiceName(SERVICENAME);
        assertEquals(result.getServiceName(), SERVICENAME);
    }

    @Test
    @DisplayName("findProjectByServiceName NotFound Test")
    public void findProjectByServiceNameNotFoundTest() {
        when(queryProjectRepository.findByServiceName(project.getServiceName())).thenReturn(Optional.empty());
        assertThrows(ClientException.class, () -> queryProjectService.findProjectByServiceName(project.getServiceName()));
    }
}