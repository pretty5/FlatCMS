package com.zhiyou100.dao;

import com.zhiyou100.model.Renter;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class RenterDaoTest {

   /* @Test
    public void insert() throws SQLException, IllegalAccessException {
        RenterDao renterDao = new RenterDao();
        Renter renter = new Renter();
        renter.setName("德玛");
        renter.setPhone(123456);
        renter.setSex("男");
        renter.setIdNum(1234567585);
        renter.setNativePlace("河南");
        renter.setCreateTime(new java.util.Date());
        renter.setLastModifiedTime(new java.util.Date());
        renterDao.insert(renter);
        System.out.println(renter);
        *//*Renter byId = renterDao.findById(17);
        byId.setPhone(147);
        System.out.println(renterDao.update(byId));*//*
    }*/

    @Test
    public void update() throws SQLException, IllegalAccessException {
        RenterDao renterDao = new RenterDao();
        Renter byId = renterDao.findById(20);
        byId.setName("寒冰");
        renterDao.update(byId);
    }

}