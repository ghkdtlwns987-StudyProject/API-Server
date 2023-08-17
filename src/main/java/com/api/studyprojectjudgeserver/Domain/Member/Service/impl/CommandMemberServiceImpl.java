package com.api.studyprojectjudgeserver.Domain.Member.Service.impl;

import com.api.studyprojectjudgeserver.Domain.Member.Dto.*;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Repository.CommandMemberRepository;
import com.api.studyprojectjudgeserver.Domain.Member.Service.inter.CommandMemberService;
import com.api.studyprojectjudgeserver.Global.Error.Exception.Auth.MemberEmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandMemberServiceImpl implements CommandMemberService {
    private final CommandMemberRepository commandMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final QueryMemberServiceImpl queryMemberService;

    @Transactional
    @Override
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) throws Exception{
        if(queryMemberService.memberExistsByEmail(signupRequestDto.getEmail())){
            throw new MemberEmailAlreadyExistsException();
        }
        String encrpytedPassword = passwordEncoder.encode(signupRequestDto.getPwd());
        signupRequestDto.setPwd(encrpytedPassword);
        MemberEntity memberEntity = signupRequestDto.toEntity();
        commandMemberRepository.save(memberEntity);
        return SignupResponseDto.fromEntity(memberEntity);
    }

    @Transactional
    @Override
    public MemberDto update(String loginId, UpdateMemberDto dto) throws Exception{
        MemberEntity memberEntity= queryMemberService.findMemberByEmail(loginId);
        memberEntity.update(passwordEncoder.encode(dto.getPwd()), dto.getNickname(), dto.getPhone());

        return MemberDto.fromEntity(memberEntity);
    }

    /**
     * 추후 업데이트 할 기능입니다.
     * 해당 기능은 회원 탈퇴 기능입니다.
     * @param loginId
     * @return MemberDto
     * @throws Exception
     */
    @Override
    public MemberDto withdraw(String loginId) throws Exception {
        return null;
    }
}
