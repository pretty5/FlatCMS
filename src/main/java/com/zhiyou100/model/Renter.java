package com.zhiyou100.model;

import lombok.Data;

import java.util.Date;
@Data
public class Renter {
    private int id;
    private String name;
    private  String phone;
    private  String sex;
    private  int idNum;
    private  String nativePlace;
    private Date createTime;
    private  Date lastModifiedTime;


}
