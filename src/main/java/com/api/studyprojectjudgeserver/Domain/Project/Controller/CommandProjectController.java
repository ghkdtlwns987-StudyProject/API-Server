package com.api.studyprojectjudgeserver.Domain.Project.Controller;

import com.api.studyprojectjudgeserver.Domain.Project.Dto.CreateJobResponseDto;
import com.api.studyprojectjudgeserver.Domain.Member.Controller.CommandMemberController;
import com.api.studyprojectjudgeserver.Domain.Member.Dto.ResultResponse;
import com.api.studyprojectjudgeserver.Domain.Project.Dto.ProjectDto;
import com.api.studyprojectjudgeserver.Domain.Project.Service.inter.CommandProjectService;
import com.api.studyprojectjudgeserver.Global.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

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
    //@PreAuthorize("hasRole('ROLE_USER')")
    public EntityModel<ResultResponse> createProject(@RequestBody ProjectDto createJobRequestDto) throws Exception {
        CreateJobResponseDto createJobResponseDto = commandProjectService.createProject(createJobRequestDto);
        ResultResponse resultResponse = ResultResponse.of(ResultCode.CREATE_JOB_REQUEST_SUCCESS, createJobResponseDto);

        EntityModel<ResultResponse> entityModel = EntityModel.of(resultResponse);
        entityModel.add(linkTo(CommandMemberController.class).withSelfRel());

        return entityModel;
    }
}
