package com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Config.impl;

import com.api.studyprojectjudgeserver.Domain.Jenkins.Command.Config.inter.GenerateConfigXml;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

/**
 * Xml 파일을 생성할 때 사용하는 클래스입니다.
 * 해당 클래스에 여러가지 버전을 추가해 넣어줍니다.
 *
 * @author 황시준
 * @since  1.0
 */
@Configuration
@RequiredArgsConstructor
public class GenerateConfigConfigXmlImpl implements GenerateConfigXml {
    private final XmlScriptTemplate xmlTemplate;
    public String gradleXml(ProjectEntity project) throws Exception {
        FlowDefinition flowDefinition = new FlowDefinition();
        flowDefinition.setPlugin("workflow-job@1316.vd2290d3341a_f");
        flowDefinition.setDescription("test 프로젝트");
        flowDefinition.setKeepDependencies(false);

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

        FlowDefinition.Properties properties = new FlowDefinition.Properties();

        FlowDefinition.GitLabConnectionProperty gitLabConnectionProperty = new FlowDefinition.GitLabConnectionProperty();
        gitLabConnectionProperty.setPlugin("gitlab-plugin@1.7.15");
        gitLabConnectionProperty.setGitLabConnection(new FlowDefinition.GitLabConnection());
        gitLabConnectionProperty.setJobCredentialId("");
        gitLabConnectionProperty.setUseAlternativeCredential(false);
        properties.setGitLabConnectionProperty(gitLabConnectionProperty);

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
        definition.setScript(xmlTemplate.gradleXmlTemplate(project));
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

        StringWriter writer = new StringWriter();
        marshaller.marshal(flowDefinition, writer);
        String xmlString = writer.toString();
        //System.out.println(xmlString);
        return xmlString;
    }

    @Override
    public String mavenXml(ProjectEntity project) throws Exception {
        return null;
    }
}
