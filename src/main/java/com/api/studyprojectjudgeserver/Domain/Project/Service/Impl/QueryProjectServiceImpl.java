package com.api.studyprojectjudgeserver.Domain.Project.Service.Impl;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Repository.QueryProjectRepository;
import com.api.studyprojectjudgeserver.Domain.Project.Service.inter.QueryProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QueryProjectServiceImpl implements QueryProjectService {
    private final QueryProjectRepository queryProjectRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<ProjectEntity> findById(Long id) {
        return queryProjectRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsServiceName(String serviceName) {
        return queryProjectRepository.existsServiceByServiceName(serviceName);
    }

}
