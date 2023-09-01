package com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Config.impl;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import org.springframework.context.annotation.Configuration;

/**
 * Config.xml 파일에 들어갈 명령어에 대한 Template 입니다.
 * @author 황시준
 * @since  1.0
 */
@Configuration
public class XmlScriptTemplate {
    public String gradleXmlTemplate(ProjectEntity project){
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
