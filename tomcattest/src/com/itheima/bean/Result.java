package com.itheima.bean;

/**
 * @author ：yp
 * @description : 封装正常返回结果   请求服务器,  把java对象
 * new Result(true,"查询联系人成功",list)
 * new Result(false,"查询联系人失败")
 * 转成json  响应json
 * @version: 1.0
 */
public class Result implements java.io.Serializable {
    private boolean flag;//执行结果，true为执行成功 false为执行失败
    private String message;//返回结果信息
    private Object result;//返回数据

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }


    public Result(boolean flag, String message, Object result) {
        this.flag = flag;
        this.message = message;
        this.result = result;
    }

    public Result() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
