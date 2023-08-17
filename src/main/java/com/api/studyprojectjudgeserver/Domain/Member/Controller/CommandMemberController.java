package com.api.studyprojectjudgeserver.Domain.Member.Controller;

import com.api.studyprojectjudgeserver.Domain.Member.Dto.MemberDto;
import com.api.studyprojectjudgeserver.Domain.Member.Dto.*;
import com.api.studyprojectjudgeserver.Domain.Member.Service.inter.CommandMemberService;
import com.api.studyprojectjudgeserver.Domain.Member.Service.inter.QueryMemberService;
import com.api.studyprojectjudgeserver.Global.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Member를 인증하는 Controller입니다.
 * @author : 황시준
 * @since : 1.0
 */
@RestController
@RequestMapping("/members/v1")
@RequiredArgsConstructor
public class CommandMemberController {
    private final CommandMemberService commandMemberService;
    private final QueryMemberService queryMemberService;
    /**
     * 현재는 인증 서버에 회원가입 API가 존재합니다.
     * 이는 추후 다른 서비스를 제작해 API를 옮길 예정입니다.
     * @param signupRequestDto
     * @return
     * @throws Exception
     */
    @PostMapping("/api")
    public EntityModel<ResultResponse> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) throws Exception{
        SignupResponseDto signupResponseDto = commandMemberService.signup(signupRequestDto);
        ResultResponse resultResponse = ResultResponse.of(ResultCode.REGISTER_SUCCESS, signupResponseDto);

        EntityModel<ResultResponse> entityModel = EntityModel.of(resultResponse);
        entityModel.add(linkTo(CommandMemberController.class).withSelfRel());

        return entityModel;
    }

    @PutMapping("/api/{loginId}")
    public EntityModel<ResultResponse> update(@PathVariable String loginId, @RequestBody UpdateMemberDto updateMemberDto) throws Exception{
        MemberDto memberDto = commandMemberService.update(loginId, updateMemberDto);
        ResultResponse resultResponse = ResultResponse.of(ResultCode.USER_UPDATE_SUCCESS, memberDto);

        EntityModel<ResultResponse> entityModel = EntityModel.of(resultResponse);
        entityModel.add(linkTo(CommandMemberController.class).withSelfRel());

        return entityModel;
    }

    @DeleteMapping("/api/{loginId}")
    public EntityModel<ResultResponse> delete(@PathVariable String loginId) throws Exception{
        MemberDto memberDto = commandMemberService.withdraw(loginId);
        ResultResponse resultResponse = ResultResponse.of(ResultCode.DELETE_USER_SUCCESS, memberDto);

        EntityModel<ResultResponse> entityModel = EntityModel.of(resultResponse);
        entityModel.add(linkTo(CommandMemberController.class).withSelfRel());

        return entityModel;
    }
    @GetMapping("/api/{loginId}")
    public EntityModel<ResultResponse> getMemberInfo(@PathVariable String loginId) throws Exception{
        MemberDto memberDto = queryMemberService.getMemberInfo(loginId);
        ResultResponse resultResponse = ResultResponse.of(ResultCode.GET_MY_INFO_SUCCESS, memberDto);

        EntityModel<ResultResponse> entityModel = EntityModel.of(resultResponse);
        entityModel.add(linkTo(CommandMemberController.class).withSelfRel());

        return entityModel;
    }

}
