package com.maitaidan.pojo;

/**
 * Created by Crytis on 2016/3/13.
 * Just test.
 */
public class GeneralJSONResult<T> {
    private boolean ret;
    private String errMsg;
    private T data;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public GeneralJSONResult(boolean ret, String errMsg, T data) {
        this.ret = ret;
        this.errMsg = errMsg;
        this.data = data;

    }
}
