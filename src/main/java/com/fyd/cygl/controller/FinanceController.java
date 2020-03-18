package com.fyd.cygl.controller;

import com.fyd.cygl.entity.OrderDetail;
import com.fyd.cygl.entity.Orders;
import com.fyd.cygl.service.OrderDetailService;
import com.fyd.cygl.service.OrdersService;
import com.fyd.cygl.util.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class FinanceController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderDetailService orderDetailService;

    @ResponseBody
    @PostMapping(value="/historyorder",produces = "application/json;charset=UTF-8" )
    public Map<String, Object> searchHistoryOrder(//page：开始页面，可以通过这个来改变页编号
                                            @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                            //limit：每页数据量
                                            @RequestParam(name = "limit",required = false, defaultValue = "10")Integer limit){
        System.out.println("page="+page+"  limit="+limit);
        PageHelper.startPage(page, limit);
        List<Orders> orders = ordersService.selectByStatus(1);
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);
        Map<String,Object> result = new HashMap<>();
        for (int i=0;i<orders.size();i++){
            orders.get(i).turnToTimestr();
        }
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("data",orders);
        System.out.println(result);
        return result;
    }
    @ResponseBody
    @PostMapping(value="/historyorderdetail",produces = "application/json;charset=UTF-8" )
    public Map<String, Object> searchHistoryOrderDetail(//page：开始页面，可以通过这个来改变页编号
                                                  @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                                  //limit：每页数据量
                                                  @RequestParam(name = "limit",required = false, defaultValue = "10")Integer limit,
                                                        @RequestParam(name = "oid",required = false)String oid ){
        System.out.println("page="+page+"  limit="+limit+" oid="+oid);
        PageHelper.startPage(page, limit);
        List<OrderDetail> orderDetails = orderDetailService.selectByOid(oid);
        PageInfo<OrderDetail> pageInfo = new PageInfo<>(orderDetails);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("data",orderDetails);
        System.out.println(result);
        return result;
    }
    @ResponseBody
    @PostMapping(value="/statisticscalender")
    public Map<String, Object> statistics(@RequestParam(name = "datevalue",required = false)String datevalue ){
        Date date = DateUtils.stringToDate(datevalue, "yyyy-MM-dd");//将字符串时间转化成Date对象
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");//将Date对象转成年-月
        String month = sdf.format(date);
        List<Orders> orders = ordersService.selectByYearAndMonth(month);//通过年月查询出指定月份的数据
        SimpleDateFormat sdf2=new SimpleDateFormat("dd");
        int daysOfMonth = DateUtils.getDaysOfMonth(date);//指定月天数

        //每天的营业额数据
        double [] data = new double[daysOfMonth];
        //初始化数组
        for (double da :data){da=0;}
        for (int i=1;i<=orders.size();i++){
            //只统计已完成的订单
            if (orders.get(i-1).getStatus()==1){
                //哪一天
                String day = sdf2.format(orders.get(i - 1).getTime());
                //将对应天的加到对应数组位置上
                data[Integer.valueOf(day)-1]+=orders.get(i-1).getRealprice();
            }
        }
        //每天完成的订单数
        int [] data2=new int[daysOfMonth];
        //每天未完成的订单数
        int [] data3=new int[daysOfMonth];
        for (double da2 :data2){da2=0;}
        for (int i=1;i<=orders.size();i++){
            //只统计已完成的订单
            if (orders.get(i-1).getStatus()==1) {
                //哪一天
                String day = sdf2.format(orders.get(i - 1).getTime());
                //将对应天的加到对应数组位置上
                data2[Integer.valueOf(day)-1]+=1;
            }else if (orders.get(i-1).getStatus()==0){
                //哪一天
                String day = sdf2.format(orders.get(i - 1).getTime());
                //将对应天的加到对应数组位置上
                data3[Integer.valueOf(day)-1]+=1;
            }
        }
//        month+"-"+
        //X坐标数据
        String []x = new String[daysOfMonth];
        for (int i=0;i<daysOfMonth;i++){
            x[i]=""+(i+1);
        }

        Map<String,Object> result = new HashMap<>();
        result.put("orders",orders);
        result.put("x",x);
        result.put("data",data);
        result.put("data2",data2);
        result.put("data3",data3);
        return result;
    }
}
