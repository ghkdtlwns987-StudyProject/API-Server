package com.api.studyprojectjudgeserver.Global.Error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 에러 코드 모음입니다.
 * @author : 황시준
 * @since : 1.0
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    INTERNAL_SERVER_ERROR(500, "C001", "internal server error"),
    INVALID_INPUT_VALUE(400, "C002", "invalid input type"),
    METHOD_NOT_ALLOWED(405, "C003", "method not allowed"),
    INVALID_TYPE_VALUE(400, "C004", "invalid type value"),
    BAD_CREDENTIALS(400, "C005", "bad credentials"),

    // Member
    MEMBER_NOT_EXIST(404, "M001", "member not exist"),
    USER_EMAIL_ALREADY_EXISTS(400, "M002", "user email already exists"),
    NO_AUTHORITY(403, "M003", "no authority"),
    NEED_LOGIN(401, "M004", "need login"),
    AUTHENTICATION_NOT_FOUND(401, "M005", "Security Context에 인증 정보가 없습니다."),
    MEMBER_ALREADY_LOGOUT(400, "M006", "member already logout"),

    // Auth
    REFRESH_TOKEN_INVALID(400, "A001", "refresh token invalid"),
    AUTHORIZATION_HEADER_INVALID(400, "A002", "invalid Authorization Header"),

    // Jenkins
    PROJECT_NOT_EXIST(404, "J001", "project not exist"),
    INVALID_PORT_NUMBER(404, "J001", "invalid port number(port number must bigger then 0"),
    SERVICE_NAME_ALREADY_EXISTS(3, "J002", "service name is already exists"),
    JENKINS_CREATE_REQUEST_ERROR(404, "J004", "[POST] Jenkins Job Failed"),
    JENKINS_GET_JOB_REQUEST_ERROR(404, "J004", "[GET] Jenkins Job Failed"),
    JENKINS_BUILD_REQUEST_ERROR(404, "J005", "[POST] Jenkins Build Request Failed"),
    JENKINS_BUILD_RESULT_REQUEST_ERROR(404, "J006", "[GET] Jenkins Build Result Request Failed"),
    JENKINS_LAST_BUILD_REQUEST_ERROR(404, "J007", "[GET] Jenkins Last Build Request Failed"),
    JENKINS_CONFIG_XML_GENERATE_ERROR(500, "J008", "Config.xml File Generate Error");

    private int status;
    private final String code;
    private final String message;
}