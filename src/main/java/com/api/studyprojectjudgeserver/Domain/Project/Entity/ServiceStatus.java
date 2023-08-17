package com.api.studyprojectjudgeserver.Domain.Project.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceStatus {
    CREATING(0, "CREATE"),
    BUILDING(1, "BUILDING"),
    SUCCEED(2, "SUCCEED"),
    FAILED(3 , "FAILED");

    private final int id;
    private final String name;
}
//    CREATING, SUCCEED, FAILED
