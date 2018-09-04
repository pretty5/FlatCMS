package com.zhiyou100.dao;

import com.zhiyou100.model.House;
import org.junit.Test;

import java.sql.SQLException;



public class HouseDaoTest {

    @Test
    public void findById() throws SQLException {
        House house = new HouseDao().findById(1);
        System.out.println(house);
    }

    @Test
    public void insert() throws SQLException, IllegalAccessException {
        HouseDao houseDao = new HouseDao();
        House house = houseDao.findById(1);
       house.setId(2);
        houseDao.insert(house);
    }

    /*@Test
    public void update(){
        HouseDao houseDao = new HouseDao();
        House house = new House();
        house.setId(1);
        house.setArea("金水区");
        try {
            houseDao.update(house);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(house);
    }*/
}