package com.zhiyou100.dao;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.model.Lease;
import com.zhiyou100.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/*
*@ClassName:LeaseDao
 @Description:TODO
 @Author:
 @Date:2018/8/28 10:14 
 @Version:v1.0
*/
@Bean
public class LeaseDao {
    public Lease findById(int id) throws SQLException {
        //获取连接
        Connection connection = DBUtil.getConnection();
        String sql="select id,renter_id renterId,house_id houseId,rental,lease_term leaseTerm,contract_term contractTerm, "+
                " create_time createTime,last_modified_time lastModifiedTime from t_lease where id=?";
        Lease lease = DBUtil.queryById(connection, sql, Lease.class, id);
        DBUtil.close();
        return lease;
    }

    public List<Lease> findByPage(int pageNum) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="select id,renter_id renterId,house_id houseId,rental,lease_term leaseTerm,contract_term contractTerm," +
                "create_time createTime,last_modified_time lastModifiedTime from t_lease limit ?,?";
        int start=20*pageNum-20;
        int end=20;
        List<Lease> leases = DBUtil.queryByConditions(connection, sql, Lease.class, start, end);
        DBUtil.close();
        return leases;
    }


    public int insert(Lease lease) throws SQLException, IllegalAccessException {
        Connection connection = DBUtil.getConnection();
        int rows = DBUtil.insert(connection, "t_lease", lease);
        DBUtil.close();
        return rows;
    }

    public int update(Lease lease) throws SQLException, IllegalAccessException {
        Connection connection = DBUtil.getConnection();
        int rows = DBUtil.updateByObject(connection, "t_lease", lease);
        DBUtil.close();
        return rows;

    }

    public int delete(Integer id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql="delete from t_lease where id=?";
        int rows = DBUtil.delete(connection, sql, id);
        DBUtil.close();
        return rows;

    }
}
