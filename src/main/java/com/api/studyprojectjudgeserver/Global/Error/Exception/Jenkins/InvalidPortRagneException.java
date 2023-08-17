package com.api.studyprojectjudgeserver.Global.Error.Exception.Jenkins;

import com.api.studyprojectjudgeserver.Global.Error.ErrorCode;
import com.api.studyprojectjudgeserver.Global.Error.Exception.BuisinessException;

public class InvalidPortRagneException extends BuisinessException {
    public InvalidPortRagneException(){
        super(ErrorCode.INVALID_PORT_NUMBER);
    }
}
