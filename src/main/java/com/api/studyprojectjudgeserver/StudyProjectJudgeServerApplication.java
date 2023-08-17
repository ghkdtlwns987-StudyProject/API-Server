package com.api.studyprojectjudgeserver;

import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
public class StudyProjectJudgeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyProjectJudgeServerApplication.class, args);
    }

}
