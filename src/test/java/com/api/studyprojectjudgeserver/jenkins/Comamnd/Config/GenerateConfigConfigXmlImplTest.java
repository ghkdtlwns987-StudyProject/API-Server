package com.api.studyprojectjudgeserver.jenkins.Comamnd.Config;

import com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Config.impl.FlowDefinition;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.Roles;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ServiceStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class GenerateConfigConfigXmlImplTest {
    private String EMAIL = "test@naver.com";
    private String USERID = "UUID";
    private String NAME = "test";
    private String NICKNAME = "test-nickname";
    private String PWD = "test-Password";
    private String PHONE = "010-1234-1234";
    private String SERVICENAME = "testServiceName";
    private String GITHUBURL = "https://ghkdtlwns987.git";
    private int SERVICEPORT = 9999;
    private String BUILDPACKAGE = "Gradle";
    private ServiceStatus STATUS = ServiceStatus.CREATING;
    private String DEPLOYURL = "none";
    private String DESCRIPTION = "test 하기 위한 용도입니다.";
    private String COMMAND = "test 하기 위한 command 입니다.";
    private String BRANCH = "/main";

    private MemberEntity member;
    private ProjectEntity project;

    @BeforeEach
    void setUp() {
        long id = 1L;

        member = MemberEntity.builder()
                .Id(id)
                .userId(USERID)
                .email(EMAIL)
                .pwd(PWD)
                .nickname(NICKNAME)
                .name(NAME)
                .phone(PHONE)
                .roles(Collections.singletonList(Roles.USER.getId()))
                .build();

        project = ProjectEntity.builder()
                .id(id)
                .email(EMAIL)
                .serviceName(SERVICENAME)
                .githubUrl(GITHUBURL)
                .branch(BRANCH)
                .servicePort(SERVICEPORT)
                .buildPackage(BUILDPACKAGE)
                .status(STATUS)
                .deployUrl(DEPLOYURL)
                .description(DESCRIPTION)
                .command(COMMAND)
                .memberEntity(member)
                .build();
    }

    @Test
    @DisplayName("Config.xml 데이터를 생성하는 테스트입니다.")
    public void testFlowDefinitionToXml() {
        try {
            // Create a new FlowDefinition instance
            FlowDefinition flowDefinition = new FlowDefinition();
            flowDefinition.setPlugin("workflow-job@1316.vd2290d3341a_f");
            flowDefinition.setDescription("test 프로젝트");
            flowDefinition.setKeepDependencies(false);

            // Set actions
            FlowDefinition.Actions actions = new FlowDefinition.Actions();
            FlowDefinition.DeclarativeJobAction declarativeJobAction = new FlowDefinition.DeclarativeJobAction();
            declarativeJobAction.setPlugin("pipeline-model-definition@2.2125.vddb_a_44a_d605e");
            actions.setDeclarativeJobAction(declarativeJobAction);
            flowDefinition.setActions(actions);

            FlowDefinition.DeclarativeJobPropertyTrackerAction propertyTrackerAction = new FlowDefinition.DeclarativeJobPropertyTrackerAction();
            propertyTrackerAction.setPlugin("pipeline-model-definition@2.2125.vddb_a_44a_d605e");
            propertyTrackerAction.setPlugin("pipeline-model-definition@2.2125.vddb_a_44a_d605e");
            propertyTrackerAction.setJobProperties("");
            propertyTrackerAction.setTriggers("");
            propertyTrackerAction.setParameters("");
            propertyTrackerAction.setOptions("");

            actions.setDeclarativeJobPropertyTrackerAction(propertyTrackerAction);

            // Set properties
            FlowDefinition.Properties properties = new FlowDefinition.Properties();

            // Set GitLabConnectionProperty
            FlowDefinition.GitLabConnectionProperty gitLabConnectionProperty = new FlowDefinition.GitLabConnectionProperty();
            gitLabConnectionProperty.setPlugin("gitlab-plugin@1.7.15");
            gitLabConnectionProperty.setGitLabConnection(new FlowDefinition.GitLabConnection());
            gitLabConnectionProperty.setJobCredentialId("");
            gitLabConnectionProperty.setUseAlternativeCredential(false);
            properties.setGitLabConnectionProperty(gitLabConnectionProperty);

            // Set PipelineTriggersJobProperty
            FlowDefinition.PipelineTriggersJobProperty pipelineTriggersJobProperty = new FlowDefinition.PipelineTriggersJobProperty();
            FlowDefinition.GitHubPushTrigger gitHubPushTrigger = new FlowDefinition.GitHubPushTrigger();
            gitHubPushTrigger.setPlugin("github@1.37.0");
            gitHubPushTrigger.setSpec("");
            FlowDefinition.Triggers triggers = new FlowDefinition.Triggers();
            triggers.setGitHubPushTrigger(gitHubPushTrigger);
            pipelineTriggersJobProperty.setTriggers(triggers);
            properties.setPipelineTriggersJobProperty(pipelineTriggersJobProperty);

            flowDefinition.setProperties(properties);

            FlowDefinition.Definition definition = new FlowDefinition.Definition();
            definition.setClazz("org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition");
            definition.setPlugin("workflow-cps@3653.v07ea_433c90b_4");
            definition.setScript(gradleXmlTest(project));
            definition.setSandbox(true); // 수정된 부분
            flowDefinition.setDefinition(definition);

            FlowDefinition.Triggers triggers2 = new FlowDefinition.Triggers();
            flowDefinition.setTriggers(triggers2);

            flowDefinition.setDisabled(false);

            JAXBContext context = JAXBContext.newInstance(FlowDefinition.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(flowDefinition, outputStream);

            String xmlString = outputStream.toString(StandardCharsets.UTF_8);
            System.out.println(xmlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String gradleXmlTest(ProjectEntity project){
        return "pipeline {\n" +
                "    agent any\n" +
                "    \n" +
                "    stages {\n" +
                "        stage('git clone') {\n" +
                "            steps {\n" +
                "                script {\n" +
                "                    git url: '" + project.getGithubUrl() + "',\n" +
                "                    branch: 'main'\n " +
                "               }\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        stage('Test') {\n" +
                "            steps {\n" +
                "                echo 'Testing..'\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        stage('Build Project') {\n" +
                "            steps {\n" +
                "                script {\n" +
                "                    sh \"chmod +x gradlew\"\n" +
                "                    sh \"./gradlew clean build\"\n" +
                "                    sh \"mkdir -p dist\"\n" +
                "                    sh \"cp ./build/libs/*.jar ./dist/\"\n" +
                "                    sh \"tar -zcvf deploy.tar.gz ./dist\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        stage(\"Push ECR\") {\n" +
                "            steps {\n" +
                "                script {\n" +
                "                    sh \"aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 600359243171.dkr.ecr.ap-northeast-2.amazonaws.com\"\n\n" +
                "                    def repositoryName = \"" + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId() + "\"\n" +
                "                    def region = \"ap-northeast-2\"\n" +
                "                    def repositoryExists = sh(script: \"aws ecr describe-repositories --repository-names $repositoryName --region $region\", returnStatus: true)" + "\n" +
                "                    if (repositoryExists == 0) {\n" +
                "                        echo \"Repository $repositoryName already exists.\"\n" +
                "                    } else {\n" +
                "                        echo \"Creating repository $repositoryName\"\n" +
                "                        sh \"aws ecr create-repository --repository-name $repositoryName --image-scanning-configuration scanOnPush=true --region $region\"\n" +
                "                    }\n" +
                "                    sh \"docker build -t " + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId() + " .\"\n" +
                "                    sh \"docker tag " + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId() + ":latest" + " " + "600359243171.dkr.ecr.ap-northeast-2.amazonaws.com/" + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId() + ":latest\"\n" +
                "                    sh \"docker push 600359243171.dkr.ecr.ap-northeast-2.amazonaws.com/" + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId() + ":latest\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        stage(\"Deploy\") {" +
                "            steps {\n" +
                "                script {\n" +
                "                    sshPublisher (\n" +
                "                        publishers: [\n" +
                "                            sshPublisherDesc(\n" +
                "                                configName: 'master',\n" +
                "                                transfers: [\n" +
                "                                sshTransfer(\n" +
                "                                   cleanRemote: false, \n" +
                "                                   excludes: '',\n" +
                "                                   execCommand: '''\n" +
                "                                        sh /941/secret.sh " + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId() + "\n" +
                "                                        sh /941/yaml_generator.sh " + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId() + " " + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId() + "-app " + "/941/" + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId() + ".yaml " + "600359243171.dkr.ecr.ap-northeast-2.amazonaws.com/" + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId() + ":latest " + "30234" + " " + project.getServicePort() + " VAR1=value1 VAR2=value2\n" +
                "                                        kubectl apply -f /941/" + project.getMemberEntity().getUserId() + "-" + project.getServiceName() + "-" + project.getId() + ".yaml\n" +
                "                                        ''',\n" +
                "                                        execTimeout: 120000, \n" +
                "                                        flatten: false, \n" +
                "                                        makeEmptyDirs: false, \n" +
                "                                        noDefaultExcludes: false, \n" +
                "                                        patternSeparator: '[, ]+', \n" +
                "                                        remoteDirectory: '', \n" +
                "                                        remoteDirectorySDF: false, \n" +
                "                                        removePrefix: '', \n" +
                "                                        sourceFiles: ''\n" +
                "                                    )\n" +
                "                                ],\n" +
                "                                usePromotionTimestamp: true, \n" +
                "                                useWorkspaceInPromotion: false, \n" +
                "                                verbose: false\n" +
                "                            )\n" +
                "                        ]\n" +
                "                    )\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
    }
}
