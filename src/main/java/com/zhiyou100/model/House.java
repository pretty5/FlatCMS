package com.zhiyou100.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/*
*@ClassName:House
 @Description:TODO
 @Author:
 @Date:2018/8/28 10:06 
 @Version:v1.0
*/
@Data
public class House {
    private int id;
    private String area;
    private String community;
    private int unitNum	;
    private int floor;
    private String roomNum	;
    private double space;
    private String direction;
    private String fitment;;
    private int isDoubleAir;
    private int limit;
    private String facility;
    //跟钱相关的字段要用Decimal
    private BigDecimal price;
    private String status;
    private String image;
    private String  address;
    private Date createTime	;
    private Date lastModifiedTime;
    private String remark;

}
