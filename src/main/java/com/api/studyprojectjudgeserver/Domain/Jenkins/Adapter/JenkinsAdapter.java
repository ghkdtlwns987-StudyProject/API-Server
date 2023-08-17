package com.api.studyprojectjudgeserver.Domain.Jenkins.Adapter;

import com.api.studyprojectjudgeserver.Domain.Jenkins.Dto.JenkinsDto;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Global.Config.JenkinsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Jenkins 서버와 통신할 때 쓰이는 어뎁터입니다.
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JenkinsAdapter {
    private final JenkinsConfig jenkinsConfig;
    private final RestTemplate restTemplate;

    /**
     * jenkins 서버에 Item을 생성할 때 사용되는 메서드 입니다.
     * Item을 생성하는 메커니즘은 "<사용자 서비스 이름> + <프로젝트 정보를 저장한 시간> 입니다.
     * 다음은 Jenkins 서버에 Item 을 생성할 때 쓰는 URI 입니다.
     * 현재는 localhost 에서 코드를 작성하기 때문에 파일 경로를 ./ 로 주었지만 서버에 배포한다면 경로를 수정하거나 Django나 Flask로 서버를 열어야 합니다.
     * curl -i -X POST --user "k8s_jenkins165:1152d8fc9a35e758e9c5048d33dcd6789c" --data-binary "@./my_config.xml" -H "Content-Type: text/xml" "http://117.16.17.165:30000/createItem?name=testJenkins"
     * @param jenkinsDto
     * @param projectEntity
     * @return
     * @author 황시준
     * @since  1.0
     */
    public ResponseEntity<Void> createItem(JenkinsDto jenkinsDto, ProjectEntity projectEntity){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        headers.setBasicAuth(jenkinsConfig.getJenkinsUser(), jenkinsConfig.getJenkinsToken());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("name", projectEntity.getServiceName()
                + "_"
                + projectEntity.getLastModifiedTime());
        body.add("file",
                new FileSystemResource(
                        jenkinsConfig.getPathVal()) + jenkinsDto.getOutputfile()
        );

        URI uri = UriComponentsBuilder
                .fromUriString(jenkinsConfig.getJenkinsUrl())
                .path("/createItem")
                .queryParam("name",
                        projectEntity.getServiceName()
                                    + "_"
                                    + projectEntity.getCreatedTime())
                .build()
                .toUri();

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                Void.class
        );

        return responseEntity;
    }
}
