package com.api.studyprojectjudgeserver.Domain.Member.Service.impl;

import com.api.studyprojectjudgeserver.Domain.Member.Dto.MemberDto;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Repository.QueryMemberRepository;
import com.api.studyprojectjudgeserver.Domain.Member.Service.inter.QueryMemberService;
import com.api.studyprojectjudgeserver.Global.Error.ClientException;
import com.api.studyprojectjudgeserver.Global.Error.ErrorCode;
import com.api.studyprojectjudgeserver.Global.Error.Exception.Auth.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueryMemberServiceImpl implements QueryMemberService {
    private final QueryMemberRepository queryMemberRepository;

    @Transactional(readOnly = true)
    @Override
    public MemberEntity findMemberById(long id) {
        return queryMemberRepository.findById(id)
                .orElseThrow(() -> new ClientException(
                        ErrorCode.MEMBER_NOT_EXIST,
                        "Member Id: " + id
                ));
    }

    @Transactional(readOnly = true)
    @Override
    public MemberEntity findMemberByEmail(String email){
        return queryMemberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new ClientException(
                        ErrorCode.MEMBER_NOT_EXIST,
                        "Member email: " + email
                ));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean memberExistsByEmail(String email){
        return queryMemberRepository.existsMemberByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public MemberDto getMemberInfo(String email) throws Exception{
        MemberEntity memberEntity = queryMemberRepository.findMemberByEmail(email).orElseThrow(() -> new MemberNotFoundException());
        return MemberDto.fromEntity(memberEntity);
    }
}
