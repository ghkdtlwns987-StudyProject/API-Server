package com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Operation;

import com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Config.inter.GenerateConfigXml;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Global.Config.JenkinsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;


/**
 * Jenkins 서버와 통신할 때 쓰이는 명령입니다.
 * @author 황시준
 * @since  1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JenkinsRest {
    private final JenkinsConfig jenkinsConfig;
    private final RestTemplate restTemplate;
    private final GenerateConfigXml generateConfigXml;

    /**
     * Jenkins 에서 CSRF 공격을 차단하기 위해 Crumb 값을 헤더에 포함해야 합니다.
     * crumb 값을 반환하는 메서드 입니다.
     * @return crumb
     */
    private String getCrumbMessage(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(jenkinsConfig.getJenkinsUser(), jenkinsConfig.getJenkinsToken());

        URI uri = UriComponentsBuilder
                .fromUriString(jenkinsConfig.getJenkinsUrl())
                .path("/crumbIssuer/api/json")
                .build()
                .toUri();

        ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<Map<String, String>>() {}
        );

        String crumb = response.getBody().get("crumb");
        return crumb;
    }


    /**
     * jenkins 서버에 Item을 생성할 때 사용되는 메서드 입니다.
     * Item 을 생성하는 메커니즘은 "<UUID(사용자 이름)>-<프로젝트 서비스 이름>-<프로젝트 Id> 입니다.
     * 예시입니다. => 42d6ae34-a6f3-4a9f-97f0-206030656b8b-testService-1
     *
     * xml <서비스 이름>-<Id> 입니다.
     * 예시입니다. => testService-1
     * 다음은 Jenkins 서버에 Item 을 생성할 때 쓰는 URI 입니다.
     * @param project
     * @return
     * @author 황시준
     * @since  1.0
     */
    public ResponseEntity<Void> createJob(ProjectEntity project) throws Exception {
        //String crumb = getCrumbMessage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        headers.setBasicAuth(jenkinsConfig.getJenkinsUser(), jenkinsConfig.getJenkinsToken());
        //headers.set("Jenkins-Crumb", crumb);
;
        String xmlContent = new String(generateConfigXml.gradleXml(project));
        URI uri = UriComponentsBuilder
                .fromUriString(jenkinsConfig.getJenkinsUrl())
                .path("/job/testFolder/createItem")
                .queryParam("name", project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId())
                .build()
                .toUri();

        HttpEntity<String> requestEntity = new HttpEntity<>(xmlContent, headers);
        log.info("requestEntity : " + requestEntity);
        ResponseEntity<Void> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                Void.class
        );

        log.info("Time : " + LocalDateTime.now());
        log.info("Member : " + project.getMemberEntity().getEmail());
        log.info("xmlContent: " + xmlContent);
        //log.info("Jenkins-Crumb : " + crumb);
        log.info("uri : " + uri);
        log.info("requestEntity : " + requestEntity);

        return response;
    }

    /**
     * 등록한 프로젝트가 존재하는지 확인하는 메서드 입니다.
     * @param project
     * @return
     * @throws Exception
     * curl -X GET "http://117.16.17.165:30000/job/AuthServer/api/json"  --user k8s_jenkins165:111771fcf88cc2f8afaa25ff24b51a313c -v
     */
    public ResponseEntity<String> getJob(ProjectEntity project) throws Exception{
        //String crumb = getCrumbMessage();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(jenkinsConfig.getJenkinsUser(), jenkinsConfig.getJenkinsToken());
        //headers.set("Jenkins-Crumb", crumb);
        URI uri = UriComponentsBuilder
                .fromUriString(jenkinsConfig.getJenkinsUrl())
                .path("/job/testFolder/job/" + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId())
                .build()
                .toUri();

        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                String.class
        );
        return response;
    }

    /**
     * 등록한 프로젝트를 실행하는 메서드 입니다.
     * @param project
     * @return
     * curl --user USER:API_TOKEN -X POST https://localhost:8080/job/test/build
     */
    public ResponseEntity<Void> runJob(ProjectEntity project){
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(jenkinsConfig.getJenkinsUser(), jenkinsConfig.getJenkinsToken());
        URI uri = UriComponentsBuilder
                .fromUriString(jenkinsConfig.getJenkinsUrl())
                .path("/job/testFolder/job/" +
                        project.getMemberEntity().getUserId() + "-"
                        + project.getServiceName() + "-"
                        + project.getId()
                        + "/build"
                )
                .build()
                .toUri();
        ResponseEntity<Void> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(headers),
                Void.class
        );

        return response;
    }

    /**
     * 마지막 빌드를 확인하는 메서드 입니다.
     * @param project
     * @return
     * curl --user USER:API_TOKEN -X POST https://localhost:8080/job/test/lastStableBuild/api/json
     */
    public ResponseEntity<Void> getLastBuild(ProjectEntity project){
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(jenkinsConfig.getJenkinsUser(), jenkinsConfig.getJenkinsToken());
        URI uri = UriComponentsBuilder
                .fromUriString(jenkinsConfig.getJenkinsUrl())
                .path("/job/testFolder/job/"
                        + project.getMemberEntity().getUserId() + "-"
                        + project.getServiceName() + "-"
                        + project.getId()
                        + "/lastStableBuild/api/json"
                )
                .build()
                .toUri();

        ResponseEntity<Void> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                Void.class
        );
        return response;
    }
}
