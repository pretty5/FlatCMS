package com.zhiyou100.dao;

import com.zhiyou100.model.Lease;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class LeaseDaoTest {

    @Test
    public void findById() throws SQLException {
        LeaseDao leaseDao = new LeaseDao();
        Lease lease = leaseDao.findById(1);
        System.out.println(lease);
       assertTrue(lease!=null);

    }

    @Test
    public void findByPage() throws SQLException {
        LeaseDao leaseDao = new LeaseDao();
        List<Lease> lease = leaseDao.findByPage(1);
        System.out.println(lease);
        assertTrue(lease!=null);
    }

    @Test
    public void insert() throws SQLException, IllegalAccessException {
        LeaseDao leaseDao = new LeaseDao();
        Lease lease = leaseDao.findById(1);
        lease.setId(2);
        leaseDao.insert(lease);
        System.out.println(lease);
        assertTrue(lease!=null);
    }

    @Test
    public void update() throws SQLException, IllegalAccessException {
        LeaseDao leaseDao = new LeaseDao();
        Lease lease = leaseDao.findById(2);
       lease.setHouseId(1);
       lease.setRenterId(1);
        leaseDao.update(lease);
        System.out.println(lease);
        assertTrue(lease!=null);
    }

    @Test
    public void delete() throws SQLException {
        LeaseDao leaseDao = new LeaseDao();
        leaseDao.delete(2);
    }
}