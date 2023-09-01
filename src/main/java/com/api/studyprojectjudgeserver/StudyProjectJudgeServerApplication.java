package com.api.studyprojectjudgeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudyProjectJudgeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyProjectJudgeServerApplication.class, args);
    }

}
