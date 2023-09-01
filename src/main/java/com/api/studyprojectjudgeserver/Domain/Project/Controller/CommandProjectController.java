package com.api.studyprojectjudgeserver.Domain.Project.Controller;

import com.api.studyprojectjudgeserver.Domain.Project.Dto.JobResponseDto;
import com.api.studyprojectjudgeserver.Domain.Member.Controller.CommandMemberController;
import com.api.studyprojectjudgeserver.Domain.Member.Dto.ResultResponse;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.ProjectDto;
import com.api.studyprojectjudgeserver.Domain.Project.Service.inter.CommandProjectService;
import com.api.studyprojectjudgeserver.Global.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Jenkins에 실행할 작업을 정의한 Controller입니다.
 * @author : 황시준
 * @since  : 1.0
 */
@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class CommandProjectController {
    private final CommandProjectService commandProjectService;
    @PostMapping("/project/job")
    public EntityModel<ResultResponse> createProject(@RequestBody @Valid ProjectDto createJobRequestDto) throws Exception {
        JobResponseDto jobResponseDto = commandProjectService.createProject(createJobRequestDto);
        //ProjectDto projectDto = commandProjectService.createProject(createJobRequestDto);
        ResultResponse resultResponse = ResultResponse.of(ResultCode.CREATE_JOB_REQUEST_SUCCESS, jobResponseDto);

        EntityModel<ResultResponse> entityModel = EntityModel.of(resultResponse);
        entityModel.add(linkTo(CommandMemberController.class).withSelfRel());

        return entityModel;
    }

    @PostMapping("/project/run/{loginId}")
    public EntityModel<ResultResponse> runProject(@RequestParam String loginId, @RequestBody ProjectDto projectDto) throws Exception {
        ProjectDto jobResponseDto = commandProjectService.runProject(loginId, projectDto);
        ResultResponse resultResponse = ResultResponse.of(ResultCode.RUN_JOB_REQUEST_SUCCESS, jobResponseDto);

        EntityModel<ResultResponse> entityModel = EntityModel.of(resultResponse);
        entityModel.add(linkTo(CommandMemberController.class).withSelfRel());

        return entityModel;
    }

    @GetMapping("/project/job/{loginId}")
    public EntityModel<ResultResponse> getAllProjectStatus(@RequestParam String loginId, @RequestBody ProjectDto projectDto) throws Exception{
        ProjectDto jobResponseDto = commandProjectService.getAllProjectStatus(loginId, projectDto);
        ResultResponse resultResponse = ResultResponse.of(ResultCode.GET_JOB_STATUS_REQUEST_SUCCESS, jobResponseDto);

        EntityModel<ResultResponse> entityModel = EntityModel.of(resultResponse);
        entityModel.add(linkTo(CommandMemberController.class).withSelfRel());

        return entityModel;
    }

    @GetMapping("/project/job/{serviceName}")
    public EntityModel<ResultResponse> getProjectStatus(@RequestParam String serviceName, @RequestBody ProjectDto projectDto) throws Exception{
        ProjectDto jobResponseDto = commandProjectService.getProjectStatus(serviceName, projectDto);
        ResultResponse resultResponse = ResultResponse.of(ResultCode.GET_JOB_STATUS_REQUEST_SUCCESS, jobResponseDto);

        EntityModel<ResultResponse> entityModel = EntityModel.of(resultResponse);
        entityModel.add(linkTo(CommandMemberController.class).withSelfRel());

        return entityModel;
    }
}
