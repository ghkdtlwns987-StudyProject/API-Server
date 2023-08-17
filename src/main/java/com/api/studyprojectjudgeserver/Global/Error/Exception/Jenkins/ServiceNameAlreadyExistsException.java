package com.api.studyprojectjudgeserver.Global.Error.Exception.Jenkins;

import com.api.studyprojectjudgeserver.Global.Error.ErrorCode;
import com.api.studyprojectjudgeserver.Global.Error.Exception.BuisinessException;

public class ServiceNameAlreadyExistsException extends BuisinessException {
    public ServiceNameAlreadyExistsException(){
        super(ErrorCode.SERVICE_NAME_ALREADY_EXISTS);
    }

}
