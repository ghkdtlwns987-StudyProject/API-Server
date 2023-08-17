package com.api.studyprojectjudgeserver.Domain.Member.Entity;


import com.api.studyprojectjudgeserver.Domain.Project.Entity.ProjectEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Member Table과 매핑되는 클래스입니다.
 * @author : 황시준
 * @since : 1.0
 */
@Builder
@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String pwd;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "name")
    private String name;

    @Column(name = "Phone")
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectEntity> projects = new ArrayList<>();

    public void update(String password, String nickname, String phone){
        this.pwd = password;
        this.nickname = nickname;
        this.phone = phone;
    }
    public void updatePassword(String password){
        this.pwd = password;
    }

    public void updateNickName(String nickname){
        this.nickname = nickname;
    }
}