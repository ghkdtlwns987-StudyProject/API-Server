package com.api.studyprojectjudgeserver.Domain.Project.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Packages {
    MAVEN(0, "MAVEN"),
    GRADLE(1, "GRADLE");

    private final int id;
    private final String key;
}
