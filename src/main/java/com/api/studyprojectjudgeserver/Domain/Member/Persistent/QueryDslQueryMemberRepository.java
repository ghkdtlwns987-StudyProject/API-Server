package com.api.studyprojectjudgeserver.Domain.Member.Persistent;

import com.api.studyprojectjudgeserver.Domain.Member.Entity.MemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Entity.QMemberEntity;
import com.api.studyprojectjudgeserver.Domain.Member.Repository.QueryMemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QueryDslQueryMemberRepository implements QueryMemberRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<MemberEntity> findById(Long id){
        QMemberEntity member = QMemberEntity.memberEntity;
        return Optional.ofNullable(jpaQueryFactory.selectFrom(member)
                .where(member.Id.eq(id))
                .fetchFirst());

    }

    @Override
    public Optional<MemberEntity> findMemberByEmail(String email) {
        QMemberEntity member = QMemberEntity.memberEntity;
        return Optional.ofNullable(jpaQueryFactory.selectFrom(member)
                .where(member.email.eq(email))
                .fetchFirst());
    }

    @Override
    public boolean existsMemberByEmail(String email) {
        QMemberEntity member = QMemberEntity.memberEntity;
        return Optional.ofNullable(jpaQueryFactory.selectFrom(member)
                .where(member.email.eq(email))
                .fetchFirst()).isPresent();
    }
}
