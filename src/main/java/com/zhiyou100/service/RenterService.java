package com.zhiyou100.service;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.dao.RenterDao;
import com.zhiyou100.model.Renter;

import java.sql.SQLException;
import java.util.List;

/*
*@ClassName:RenterService
 @Description:TODO
 @Author:
 @Date:2018/8/28 11:11 
 @Version:v1.0
*/
@Bean
public class RenterService {
    @Require
    RenterDao renterDao;

    public Renter findById(int id) throws SQLException {
        Renter renter = renterDao.findById(id);
        return renter;
    }

    public List<Renter> findByPage(int pageNum) throws SQLException {
        return renterDao.findByPage(pageNum);
    }


    public int insert(Renter renter) throws SQLException, IllegalAccessException {
        return renterDao.insert(renter);
    }

    public int update(Renter renter) throws SQLException, IllegalAccessException {
        return renterDao.update(renter);
    }

    public int delete(Integer id) throws SQLException {
        return renterDao.delete(id);
    }
}
