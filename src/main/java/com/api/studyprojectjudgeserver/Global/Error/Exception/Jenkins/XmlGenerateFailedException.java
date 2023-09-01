package com.api.studyprojectjudgeserver.Global.Error.Exception.Jenkins;

import com.api.studyprojectjudgeserver.Global.Error.ErrorCode;
import com.api.studyprojectjudgeserver.Global.Error.Exception.BuisinessException;

public class XmlGenerateFailedException extends BuisinessException {
    public XmlGenerateFailedException(){
        super(ErrorCode.SERVICE_NAME_ALREADY_EXISTS);
    }
}
