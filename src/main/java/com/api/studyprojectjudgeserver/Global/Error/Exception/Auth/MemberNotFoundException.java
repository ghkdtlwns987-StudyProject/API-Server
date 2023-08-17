package com.api.studyprojectjudgeserver.Global.Error.Exception.Auth;


import com.api.studyprojectjudgeserver.Global.Error.ErrorCode;
import com.api.studyprojectjudgeserver.Global.Error.Exception.BuisinessException;

/**
 * Member를 찾을 수 없을 때 발생하는 예외입니다.
 */
public class MemberNotFoundException extends BuisinessException {
    public MemberNotFoundException(){
        super(ErrorCode.MEMBER_NOT_EXIST);
    }
}
