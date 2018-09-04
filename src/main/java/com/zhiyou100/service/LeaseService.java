package com.zhiyou100.service;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.dao.LeaseDao;
import com.zhiyou100.model.Lease;

import java.sql.SQLException;
import java.util.List;

/*
*@ClassName:LeaseService
 @Description:TODO
 @Author:
 @Date:2018/8/28 11:11 
 @Version:v1.0
*/
@Bean
public class LeaseService {
    @Require
    LeaseDao leaseDao;

    public Lease findById(int id) throws SQLException {
        Lease lease = leaseDao.findById(id);
        return lease;
    }

    public List<Lease> findByPage(int pageNum) throws SQLException {
        return leaseDao.findByPage(pageNum);
    }


    public int insert(Lease lease) throws SQLException, IllegalAccessException {
        return leaseDao.insert(lease);
    }

    public int update(Lease lease) throws SQLException, IllegalAccessException {
        return leaseDao.update(lease);
    }

    public int delete(Integer id) throws SQLException {
        return leaseDao.delete(id);
    }
}
