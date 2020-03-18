package com.fyd.cygl.controller;

import com.fyd.cygl.entity.Staffs;
import com.fyd.cygl.service.StaffsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StaffsController {
    @Autowired
    StaffsService staffsService;
    @ResponseBody
    @PostMapping(value="/staffs",produces = "application/json;charset=UTF-8" )
    public Map<String, Object> searchStaffs(//page：开始页面，可以通过这个来改变页编号
                                            @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                            //limit：每页数据量
                                            @RequestParam(name = "limit",required = false, defaultValue = "10")Integer limit ){
        System.out.println("page="+page+"  limit="+limit);
        PageHelper.startPage(page, limit);
        List<Staffs> staffs = staffsService.searchAll();
        PageInfo<Staffs> pageInfo = new PageInfo<>(staffs);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("data",staffs);
        System.out.println(result);
        return result;
    }
    //按员工编号查询(可模糊查询)
    @ResponseBody
    @PostMapping(value="/staffsBySid",produces = "application/json;charset=UTF-8" )
    public Map<String,Object> searchStaffsBySid(//page：开始页面，可以通过这个来改变页编号
                                                 @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                                 //limit：每页数据量
                                                 @RequestParam(name = "limit",required = false, defaultValue = "10")Integer limit,
                                                 @RequestParam(name = "searchContent",required = false)Integer searchContent){

        System.out.println("员工编号：page="+page+"  limit="+limit+" searchContent="+searchContent);
        PageHelper.startPage(page, limit);
        List<Staffs> staffs = staffsService.searchBySid(searchContent);
        PageInfo<Staffs> pageInfo = new PageInfo<>(staffs);
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("data",staffs);
        System.out.println(result);
        return result;
    }

    //按名字查询
    @ResponseBody
    @PostMapping(value="/staffsByName",produces = "application/json;charset=UTF-8" )
    public Map<String,Object> searchStaffsByName(//page：开始页面，可以通过这个来改变页编号
                                                @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                                //limit：每页数据量
                                                @RequestParam(name = "limit",required = false, defaultValue = "10")Integer limit,
                                                @RequestParam(name = "searchContent",required = false)String searchContent){
        System.out.println("名字：page="+page+"  limit="+limit+" searchContent="+searchContent);
        PageHelper.startPage(page, limit);
        List<Staffs> staffs = staffsService.searchByName("%"+searchContent+"%");
        PageInfo<Staffs> pageInfo = new PageInfo<>(staffs);

        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("data",staffs);
        System.out.println(result);
        return result;
    }
    //删除当前行
    @PostMapping(value="/delestaffline")
    public int deleteBytid(@RequestParam(name = "sid",required=false) Integer sid){
        System.out.println("sid="+sid);
        int i = staffsService.deleteBysid(sid);
        return i;

    }
    //编辑当前行
    @ResponseBody
    @PostMapping(value = "/editstaffBysid")
    public int updateBysid(@RequestParam(name = "sid",required = false) Integer sid,@RequestParam(name = "name",required = false) String name,@RequestParam(name = "gender",required = false) String gender,@RequestParam(name = "year",required = false) Integer year,@RequestParam(name = "salary",required = false) Double salary){
        System.out.println("sid="+sid+"  name="+name+"  gender="+gender+"  year="+year+"  salary="+salary);
        Staffs staffs = new Staffs();
        staffs.setSid(sid);
        staffs.setName(name);
        staffs.setGender(gender);
        staffs.setYear(year);
        staffs.setSalary(salary);
        int i = staffsService.updateBysid(staffs);
        return i;
    }
    //添加
    @ResponseBody
    @PostMapping(value = "/addstaff")
    public int addStaff(@RequestParam(name = "name",required = false) String name,@RequestParam(name = "gender",required = false) String gender,@RequestParam(name = "year",required = false) Integer year,@RequestParam(name = "salary",required = false) Double salary ){
        Staffs staffs = new Staffs();
        staffs.setName(name);
        staffs.setGender(gender);
        staffs.setYear(year);
        staffs.setSalary(salary);
        int i = staffsService.add(staffs);
        return i;
    }
}
