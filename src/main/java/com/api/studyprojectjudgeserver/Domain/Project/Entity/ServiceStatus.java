package com.api.studyprojectjudgeserver.Domain.Project.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceStatus {
    CREATING(0, "CREATED"),
    READY(1, "READY"),
    BUILDING(2, "BUILDING"),
    SUCCEED(3, "SUCCEED"),
    FAILED(4 , "FAILED"),
    ERROR(5, "ERROR"),
    BUILD_FAILED(6, "BUILD_FAILED");
    private final int id;
    private final String name;
}
//    CREATING, SUCCEED, FAILED
