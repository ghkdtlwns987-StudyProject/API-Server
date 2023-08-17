package com.api.studyprojectjudgeserver.Domain.Project.Persistent;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Entity.QProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Repository.QueryProjectRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class QueryDslQueryProjectRepository implements QueryProjectRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ProjectEntity> findById(Long id) {
        QProjectEntity qProject = QProjectEntity.projectEntity;
        return Optional.ofNullable(queryFactory.selectFrom(qProject)
                .where(qProject.id.eq(id))
                .fetchFirst());
    }

    @Override
    public boolean existsServiceByServiceName(String serviceName) {
        QProjectEntity qProject = QProjectEntity.projectEntity;
        return Optional.ofNullable(queryFactory.selectFrom(qProject)
                .where(qProject.serviceName.eq(serviceName))
                .fetchFirst()).isPresent();
    }
}
