package com.zhiyou100.service;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.dao.HouseDao;
import com.zhiyou100.model.House;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/*
*@ClassName:HouseService
 @Description:TODO
 @Author:
 @Date:2018/8/28 11:11 
 @Version:v1.0
*/
@Bean
public class HouseService {
    @Require
    HouseDao houseDao;

    public House findById(int id) throws SQLException {
        House house = houseDao.findById(id);
        return house;
    }

    public List<House> findByPage(int pageNum) throws SQLException {
        return houseDao.findByPage(pageNum);
    }


    public int insert(House house) throws SQLException, IllegalAccessException {
        return houseDao.insert(house);
    }

    public int update(House house) throws SQLException, IllegalAccessException {
        return houseDao.update(house);
    }

    public int delete(Integer id) throws SQLException {
        return houseDao.delete(id);
    }
}
