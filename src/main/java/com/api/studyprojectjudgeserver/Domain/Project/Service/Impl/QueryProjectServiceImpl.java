package com.api.studyprojectjudgeserver.Domain.Project.Service.Impl;

import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import com.api.studyprojectjudgeserver.Domain.Project.Repository.QueryProjectRepository;
import com.api.studyprojectjudgeserver.Domain.Project.Service.inter.QueryProjectService;
import com.api.studyprojectjudgeserver.Global.Error.ClientException;
import com.api.studyprojectjudgeserver.Global.Error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryProjectServiceImpl implements QueryProjectService {
    private final QueryProjectRepository queryProjectRepository;

    @Transactional(readOnly = true)
    @Override
    public ProjectEntity findProjectById(Long id) {
        return queryProjectRepository.findById(id)
                .orElseThrow(() -> new ClientException(
                        ErrorCode.PROJECT_NOT_EXIST,
                        "Project Id : " + id
                ));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsServiceName(String serviceName) {
        return queryProjectRepository.existsServiceByServiceName(serviceName);
    }

    @Transactional(readOnly = true)
    @Override
    public ProjectEntity findProjectByServiceName(String serviceName){
        return queryProjectRepository.findByServiceName(serviceName)
                .orElseThrow(() -> new ClientException(
                        ErrorCode.PROJECT_NOT_EXIST,
                        "Service Name : " + serviceName
                ));
    }
}
