package com.api.studyprojectjudgeserver.member.controller;

import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.Roles;
import com.api.studyprojectjudgeserver.Domain.Member.Service.inter.CommandMemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.h2.engine.Role;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
class CommandMemberControllerTest {
    private final String EMAIL = "test@naver.com";
    private final String USERID = "UUID";
    private final String NAME = "test";
    private final String NICKNAME = "test-nickname";
    private final String PWD = "test-Password";
    private final String PHONE = "010-1234-1234";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CommandMemberService memberAuthenticationService;

    private MemberEntity member;
    @BeforeEach
    void setUp(){
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
    }

    @DisplayName("회원가입 테스트")
    @Order(1)
    @Test
    public void signUpTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("email", EMAIL);
        requestBody.put("name", NAME);
        requestBody.put("nickname", NICKNAME);
        requestBody.put("pwd", PWD);
        requestBody.put("phone", PHONE);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/members/v1/api")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody.toString())
                )
                .andExpect(status().isOk())
                .andDo(
                        document("member-signup",
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
                                        fieldWithPath("name").description("이름"),
                                        fieldWithPath("nickname").description("닉네임"),
                                        fieldWithPath("pwd").description("비밀번호"),
                                        fieldWithPath("phone").description("전화번호")
                                )
                        )
                );

    }


    @DisplayName("회원가입 중복 테스트")
    @Order(2)
    @Test
    public void duplicationSignupTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("email", "test@naver.com");
        requestBody.put("name", "test");
        requestBody.put("nickname", "test-nickname");
        requestBody.put("pwd", "testPassword1234");
        requestBody.put("phone", "010-1234-1234");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/members/v1/api")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody.toString())
                )
                .andExpect(status().isInternalServerError())
                .andDo(
                        document("member-duplicate-signup",
                                Preprocessors.preprocessRequest(prettyPrint()),
                                Preprocessors.preprocessResponse(prettyPrint()),
                                responseFields(
                                        fieldWithPath("success").description("성공 여부"),
                                        fieldWithPath("status").description("상태 코드"),
                                        fieldWithPath("data").description("데이터"),
                                        fieldWithPath("errorMessage").description("에러 메시지")
                                )
                        )
                );
    }

    @DisplayName("유저 검색 테스트")
    @Order(3)
    @Test
    public void getMemberInfoTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestBody = objectMapper.createObjectNode();
        mockMvc.perform(
                        get("/members/v1/api/test@naver.com")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody.toString())
                )
                .andExpect(status().isOk())
                .andDo(
                        document("member-getMemberInfo",
                                Preprocessors.preprocessRequest(prettyPrint()),
                                Preprocessors.preprocessResponse(prettyPrint()),
                                responseFields(
                                        fieldWithPath("status").description("상태 코드"),
                                        fieldWithPath("code").description("응답 코드"),
                                        fieldWithPath("message").description("응답 메시지"),
                                        fieldWithPath("data.id").description("회원 ID"),
                                        fieldWithPath("data.userId").description("사용자 ID"),
                                        fieldWithPath("data.email").description("이메일"),
                                        fieldWithPath("data.password").description("비밀번호"),
                                        fieldWithPath("data.nickname").description("닉네임"),
                                        fieldWithPath("data.name").description("이름"),
                                        fieldWithPath("data.phone").description("전화번호"),
                                        fieldWithPath("data.roles").description("역할"),
                                        fieldWithPath("data.registered").description("생성 시간"),
                                        fieldWithPath("_links.self.href").description("요청 URL")
                                )
                        )
                );
    }


    @DisplayName("회원 수정 테스트")
    @Order(4)
    @Test
    public void updateTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("nickname", "test-nickname-update");
        requestBody.put("pwd", "test-Password");
        requestBody.put("phone", "010-1234-1234");

        mockMvc.perform(
                        put("/members/v1/api/test@naver.com")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody.toString())
                )
                .andExpect(status().isOk())
                .andDo(
                        document("member-update",
                                Preprocessors.preprocessRequest(prettyPrint()),
                                Preprocessors.preprocessResponse(prettyPrint()),
                                responseFields(
                                        fieldWithPath("status").description("상태 코드"),
                                        fieldWithPath("code").description("응답 코드"),
                                        fieldWithPath("message").description("응답 메시지"),
                                        fieldWithPath("data.id").description("회원 ID"),
                                        fieldWithPath("data.userId").description("사용자 ID"),
                                        fieldWithPath("data.email").description("이메일"),
                                        fieldWithPath("data.password").description("비밀번호"),
                                        fieldWithPath("data.nickname").description("닉네임"),
                                        fieldWithPath("data.name").description("이름"),
                                        fieldWithPath("data.phone").description("전화번호"),
                                        fieldWithPath("data.roles").description("역할"),
                                        fieldWithPath("data.registered").description("생성 시간"),
                                        fieldWithPath("_links.self.href").description("요청 URL")
                                ),
                                requestFields(
                                        fieldWithPath("nickname").description("업데이트할 nickname"),
                                        fieldWithPath("pwd").description("업데이트할 비밀번호"),
                                        fieldWithPath("phone").description("업데이트할 전화번호")
                                )
                        )
                );
    }
}
