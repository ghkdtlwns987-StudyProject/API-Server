package com.api.studyprojectjudgeserver.jenkins.Comamnd.Service;

import com.api.studyprojectjudgeserver.Domain.Jenkins.Service.inter.CommandJenkinsService;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.Roles;
import com.api.studyprojectjudgeserver.Domain.Member.Service.inter.QueryMemberService;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.JobResponseDto;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.ProjectDto;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ServiceStatus;
import com.api.studyprojectjudgeserver.Domain.Project.Repository.CommandProjectRepository;
import com.api.studyprojectjudgeserver.Domain.Project.Service.Impl.CommandProjectServiceImpl;
import com.api.studyprojectjudgeserver.Domain.Project.Service.inter.QueryProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CommandProjectServiceImplTest {
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
    @Mock
    private QueryMemberService queryMemberService;

    @Mock
    private QueryProjectService queryProjectService;

    @Mock
    private CommandProjectRepository commandProjectRepository;

    @Mock
    private CommandJenkinsService commandJenkinsService;

    @InjectMocks
    private CommandProjectServiceImpl commandProjectService;

    ProjectEntity project;
    MemberEntity member;
    ProjectDto projectDto;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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

        projectDto = ProjectDto.builder()
                .id(project.getId())
                .email(project.getEmail())
                .serviceName(project.getServiceName())
                .githubUrl(project.getGithubUrl())
                .branch(project.getBranch())
                .servicePort(project.getServicePort())
                .buildPackage(project.getBuildPackage())
                .status(project.getStatus())
                .deployUrl(project.getDeployUrl())
                .description(project.getDescription())
                .command(project.getCommand())
                .memberEntity(project.getMemberEntity())
                .build();

        commandProjectService = new CommandProjectServiceImpl(
                queryMemberService,
                queryProjectService,
                commandProjectRepository,
                commandJenkinsService
        );
    }

    @Test
    @DisplayName("createProject Test")
    public void createProjectTest() throws Exception {

        when(queryMemberService.findMemberByEmail(projectDto.getEmail())).thenReturn(member);
        when(queryProjectService.existsServiceName(projectDto.getServiceName())).thenReturn(false);

        commandProjectService.createProject(projectDto);

        verify(commandProjectRepository).save(any());
        verify(commandJenkinsService).createItem(any());
        verify(commandJenkinsService).runItem(any());
    }

    @Test
    @DisplayName("getProjectStatus Test")
    public void getProjectStatusTest() throws Exception{
        when(queryProjectService.findProjectByServiceName(project.getServiceName())).thenReturn(project);
        commandProjectService.getProjectStatus(SERVICENAME, projectDto);
        verify(commandJenkinsService).getItemStatus(project);
    }
}
