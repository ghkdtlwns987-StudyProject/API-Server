package com.api.studyprojectjudgeserver.Project.Controller;

import com.api.studyprojectjudgeserver.Domain.Member.Dto.SignupRequestDto;
import com.api.studyprojectjudgeserver.Domain.Member.Dto.SignupResponseDto;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.Roles;
import com.api.studyprojectjudgeserver.Domain.Member.Service.inter.CommandMemberService;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.ProjectDto;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ServiceStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Collections;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
public class CommandProjectControllerTest {
    private String EMAIL = "test@naver.com";
    private String USERID = "UUID";
    private String NAME = "test";
    private String NICKNAME = "test-nickname";
    private String PWD = "test-Password";
    private String PHONE = "010-1234-1234";
    private String SERVICENAME = "testname";
    private String GITHUBURL = "https://ghkdtlwns987.git";
    private int SERVICEPORT = 9999;
    private String BUILDPACKAGE = "GRADLE";
    private ServiceStatus STATUS = ServiceStatus.CREATING;
    private String DEPLOYURL = "none";
    private String DESCRIPTION = "test 하기 위한 용도입니다.";
    private String COMMAND = "test 하기 위한 command 입니다.";
    private String BRANCH = "/main";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CommandMemberService memberAuthenticationService;

    private MemberEntity member;
    private ProjectDto projectDto;
    private SignupResponseDto signupResponseDto;
    private ProjectEntity project;
    @BeforeEach
    void setUp() throws Exception {
        long id = 1L;

        projectDto = ProjectDto.builder()
                .email(EMAIL)
                .serviceName(SERVICENAME)
                .githubUrl(GITHUBURL)
                .branch(BRANCH)
                .buildPackage(BUILDPACKAGE)
                .servicePort(SERVICEPORT)
                .description(DESCRIPTION)
                .command(COMMAND)
                .memberEntity(member)
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
    }

    @DisplayName("프로젝트 생성 테스트")
    @Test
    @Order(1)
    public void createProject() throws Exception {
        SignupRequestDto signupRequestDto = new SignupRequestDto(EMAIL, NAME, NICKNAME, PWD, PHONE);
        signupResponseDto = memberAuthenticationService.signup(signupRequestDto);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("email", EMAIL);
        requestBody.put("serviceName", SERVICENAME);
        requestBody.put("githubUrl", GITHUBURL);
        requestBody.put("branch", BRANCH);
        requestBody.put("buildPackage", BUILDPACKAGE);
        requestBody.put("servicePort", SERVICEPORT);
        requestBody.put("description", DESCRIPTION);
        requestBody.put("command", COMMAND);


        mockMvc.perform(
                        MockMvcRequestBuilders.post("/v1/api/project/job")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody.toString())
                )
                .andExpect(status().isOk())
                .andDo(
                        document("project-create",
                                Preprocessors.preprocessRequest(prettyPrint()),
                                Preprocessors.preprocessResponse(prettyPrint()),
                                responseFields(
                                        fieldWithPath("status").description("상태 코드"),
                                        fieldWithPath("code").description("응답 코드"),
                                        fieldWithPath("message").description("응답 메시지"),
                                        subsectionWithPath("data").description("응답 데이터"),
                                        subsectionWithPath("_links").description("하이퍼미디어 링크 정보")
                                ),
                                requestFields(
                                        fieldWithPath("email").description("이메일"),
                                        fieldWithPath("serviceName").description("서비스 명"),
                                        fieldWithPath("githubUrl").description("Git 주소"),
                                        fieldWithPath("branch").description("Branch"),
                                        fieldWithPath("buildPackage").description("빌드 패키지"),
                                        fieldWithPath("servicePort").description("서비스 포트"),
                                        fieldWithPath("description").description("프로젝트 설명"),
                                        fieldWithPath("command").description("프로젝트 빌드 명령어")
                                )
                        )
                );
    }
}
