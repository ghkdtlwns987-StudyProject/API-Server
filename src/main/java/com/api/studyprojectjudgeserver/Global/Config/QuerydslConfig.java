package com.api.studyprojectjudgeserver.Global.Config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * queryDsl 을 사용하기 위해 JPAQueryFactory 를 Bean 으로 등록하는 Configuration.
 *
 * @author 황시준
 * @since  1.0
 */
@Configuration
public class QuerydslConfig {
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em){
        return new JPAQueryFactory(em);
    }
}
