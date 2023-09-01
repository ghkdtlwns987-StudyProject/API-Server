package com.api.studyprojectjudgeserver.Global.Config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JenkinsConfig {
    @Value("${jenkins.url}")
    public String jenkinsUrl;

    @Value("${jenkins.user}")
    public String jenkinsUser;

    @Value("${jenkins.token}")
    public String jenkinsToken;

    @Value("${file.path}")
    public String pathVal;

    @Value("${JENKINS_CREDENTIAL}")
    public String jenkinsCredential;
}
