package com.itheima.test;

import com.alibaba.fastjson.JSON;
import com.itheima.bean.LinkMan;
import com.itheima.bean.Result;
import com.itheima.service.LinkManService;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: yp
 */
public class JsonTest {

    @Test //当做是web层doGet()方法, 查询所有的联系人
    public void fun01(){
        try {
            //1.获得请求参数
            //2.调用业务 获得List<LinkMan> list
            //List<LinkMan> list = new ArrayList<LinkMan>();
            LinkManService linkManService = new LinkManService();
            int i = 1/0;
            List<LinkMan> list = linkManService.findAll();

            //把list封装成Result
            Result result = new Result(true, "查询所有的联系人成功", list);
            String data = JSON.toJSONString(result); //把java对象转成JSON
            System.out.println(data);
            //3.响应
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(false, "查询所有的联系人失败");
            String data = JSON.toJSONString(result);
            System.out.println(data);
        }


    }


}
