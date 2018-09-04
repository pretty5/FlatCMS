package com.zhiyou100.Resonse;

import lombok.Data;

/*
*@ClassName:ResponMessage
 @Description:TODO
 @Author:
 @Date:2018/8/27 11:27 
 @Version:v1.0
*/
//用来封装响应
@Data
public class ResponseMessage {
    //响应码
    private int code;
    //响应内容
    private String content;
    //响应是否成功
    private boolean isSuccess;
    //错误原因
    private String error;

}
