package com.zhiyou100.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.RequestMapping;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.model.House;
import com.zhiyou100.service.HouseService;
import com.zhiyou100.util.BeanUtil;
import com.zhiyou100.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/*
*@ClassName:HouseController
 @Description:TODO
 @Author:
 @Date:2018/8/28 11:14 
 @Version:v1.0
*/
@Bean
@RequestMapping("/house")
public class HouseController {

    private final static Logger log=LoggerFactory.getLogger(HouseController.class);
    @Require
    HouseService houseService;
    @RequestMapping("/list.do")
    public void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = req.getParameter("page");
        int pageNum=1;
        if (!StringUtils.isBlank(page)){
            pageNum=Integer.parseInt(page);
        }
        try {
            List<House> houseList = houseService.findByPage(pageNum);
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();
            String json = gson.toJson(houseList);
            ResponseUtil.responseSuccess(resp,json,200);
        } catch (Exception e) {
           // e.printStackTrace();
            ResponseUtil.responseFailure(resp,"server error",100);
        }
    }
    @RequestMapping("/get.do")
    public void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String StringId = req.getParameter("id");
        if (StringUtils.isBlank(StringId)){
            ResponseUtil.responseFailure(resp,"id is not set",100);
            return;
        }
        Integer id = Integer.valueOf(StringId);
        try {
            House house = houseService.findById(id);
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();
            ResponseUtil.responseSuccess(resp,gson.toJson(house),200);

        } catch (SQLException e) {
            //e.printStackTrace();
            log.error("error:",e);
            ResponseUtil.responseFailure(resp,"server error",100);
        }


    }
    @RequestMapping("/delete.do")
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String StringId = req.getParameter("id");
        if (StringUtils.isBlank(StringId)){
            ResponseUtil.responseFailure(resp,"id is not set",100);
            return;
        }
        Integer id = Integer.valueOf(StringId);
        try {
            int rows = houseService.delete(id);
            if (rows==1){
                ResponseUtil.responseSuccess(resp,"delete success",200);
            }else{
                ResponseUtil.responseFailure(resp,"delete failure",100);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            log.error("error:",e);
            ResponseUtil.responseFailure(resp,"server error",100);
        }


    }
    @RequestMapping("/insert.do")
    public void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            House house = BeanUtil.parseFromRequest(req, House.class);
            house.setLastModifiedTime(new Date());
            int rows=houseService.insert(house);
            if (rows==1){
                ResponseUtil.responseSuccess(resp,"insert success",200);
            }else{
                ResponseUtil.responseFailure(resp,"insert error",100);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("error:",e);
            ResponseUtil.responseFailure(resp,"server error",100);
        }
    }
    @RequestMapping("/update.do")
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //

            House house = BeanUtil.parseFromRequest(req, House.class);
            house.setCreateTime(new Date());
            house.setLastModifiedTime(new Date());
            int rows=houseService.update(house);
            System.out.println(house);
            if (rows==1){
                ResponseUtil.responseSuccess(resp,"更新成功",200);
            }else{
                ResponseUtil.responseFailure(resp,"更新失败",100);
            }

        } catch (Exception e) {
            //e.printStackTrace();
            log.error("error:",e);
            ResponseUtil.responseFailure(resp,"server error",100);
        }
    }


}
