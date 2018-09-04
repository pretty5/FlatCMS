package com.zhiyou100.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Lease {
    private  int id;
    private  int renterId;
    private  int houseId;
    private BigDecimal rental;
    private  int leaseTerm;
    private  int contractTerm;
    private Date createTime;
    private  Date lastModifiedTime;
}
