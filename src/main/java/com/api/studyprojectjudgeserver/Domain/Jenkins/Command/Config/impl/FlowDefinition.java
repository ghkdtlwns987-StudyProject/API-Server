package com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Config.impl;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * Jenkins 에 전달할 xml 데이터를 생성할 때 쓰이는 template 입니다.
 * @author 황시준
 * @since  1.0
 */
@XmlRootElement(name = "flow-definition")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class FlowDefinition {

    @XmlAttribute
    private String plugin;

    @XmlElement(name = "actions")
    private Actions actions;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "keepDependencies")
    private boolean keepDependencies;

    @XmlElement(name = "properties")
    private Properties properties;

    @XmlElement(name = "definition")
    private Definition definition;

    @XmlElement(name = "triggers")
    private Triggers triggers;

    @XmlElement(name = "disabled")
    private boolean disabled;

    @XmlElement(name = "com.dabsquared.gitlabjenkins.connection.GitLabConnectionProperty")
    private GitLabConnectionProperty gitLabConnectionProperty;

    @XmlElement(name = "org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty")
    private PipelineTriggersJobProperty pipelineTriggersJobProperty;

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class GitLabConnectionProperty {
        @XmlAttribute
        private String plugin;

        @XmlElement(name = "gitLabConnection")
        private GitLabConnection gitLabConnection;

        @XmlElement(name = "jobCredentialId")
        private String jobCredentialId;

        @XmlElement(name = "useAlternativeCredential")
        private boolean useAlternativeCredential;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class GitLabConnection {
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class PipelineTriggersJobProperty {
        @XmlElement(name = "triggers")
        private Triggers triggers;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class Actions {
        @XmlElement(name = "org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobAction")
        private DeclarativeJobAction declarativeJobAction;

        @XmlElement(name = "org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobPropertyTrackerAction")
        private DeclarativeJobPropertyTrackerAction declarativeJobPropertyTrackerAction;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class DeclarativeJobAction {
        @XmlAttribute
        private String plugin;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class DeclarativeJobPropertyTrackerAction {
        @XmlAttribute
        private String plugin;
        private String jobProperties;
        private String triggers;
        private String parameters;
        private String options;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class Properties {
        @XmlElement(name = "com.dabsquared.gitlabjenkins.connection.GitLabConnectionProperty")
        private GitLabConnectionProperty gitLabConnectionProperty;

        @XmlElement(name = "org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty")
        private PipelineTriggersJobProperty pipelineTriggersJobProperty;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class Definition {
        @XmlAttribute(name = "class")
        private String clazz;

        @XmlAttribute
        private String plugin;

        @XmlElement(name = "script")
        private String script;

        @XmlElement(name = "sandbox")
        private boolean sandbox;
    }



    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class GitHubPushTrigger {
        @XmlAttribute
        private String plugin;

        @XmlElement(name = "spec")
        private String spec;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class Triggers {
        @XmlElement(name = "com.cloudbees.jenkins.GitHubPushTrigger")
        private GitHubPushTrigger gitHubPushTrigger;
    }
}
