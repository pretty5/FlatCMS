package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.Renter;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/*
*@ClassName:RenterDao
 @Description:TODO
 @Author:
 @Date:2018/8/28 10:14 
 @Version:v1.0
*/
@Bean
public class RenterDao {
    public Renter findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,name,phone,sex,id_num idNum,native_place nativePlace, "+
                "   create_time createTime,last_modified_time lastModifiedTime from t_renter where id=?";
        Renter renter = DBUtil.queryById(connection, sql, Renter.class, id);
        DBUtil.close();
        return renter;
    }

    public List<Renter> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,name,phone,sex,id_num idNum,native_place nativePlace,create_time createTime,last_modified_time lastModifiedTime from t_renter limit ?,?";
        int start=20*pageNum-20;
        int end=20;
        List<Renter> renters = DBUtil.queryByConditions(connection, sql, Renter.class, start, end);
        DBUtil.close();
        return renters;
    }


    public int insert(Renter renter) throws SQLException, IllegalAccessException {
        Connection connection = DBUtil.getConnection();
        int rows = DBUtil.insert(connection, "t_renter", renter);
        DBUtil.close();
        return rows;
    }

    public int update(Renter renter) throws SQLException, IllegalAccessException {
        Connection connection = DBUtil.getConnection();
        int rows = DBUtil.updateByObject(connection, "t_renter", renter);
        DBUtil.close();
        return rows;

    }

    public int delete(Integer id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="delete from t_renter where id=?";
        int rows = DBUtil.delete(connection, sql, id);
        DBUtil.close();
        return rows;

    }
}
