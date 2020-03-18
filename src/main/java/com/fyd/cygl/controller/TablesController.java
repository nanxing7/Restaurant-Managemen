package com.fyd.cygl.controller;

import com.fyd.cygl.entity.Tables;
import com.fyd.cygl.service.TablesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class TablesController {
    @Autowired
    private TablesService tablesService;
    //显示所有
    @ResponseBody
    @PostMapping(value="/tables",produces = "application/json;charset=UTF-8" )
    public Map<String, Object> searchTables(//page：开始页面，可以通过这个来改变页编号
                                            @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                            //limit：每页数据量
                                            @RequestParam(name = "limit",required = false, defaultValue = "10")Integer limit ){
        System.out.println("page="+page+"  limit="+limit);
        PageHelper.startPage(page, limit);
        List<Tables> tables = tablesService.searchAll();
        PageInfo<Tables> pageInfo = new PageInfo<>(tables);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("data",tables);
        System.out.println(pageInfo.getList());
        return result;
    }
    //按所属房间查询(可模糊查询)
    @ResponseBody
    @PostMapping(value="/tablesByRoom",produces = "application/json;charset=UTF-8" )
    public Map<String,Object> searchTablesByRoom(//page：开始页面，可以通过这个来改变页编号
                                                 @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                                 //limit：每页数据量
                                                 @RequestParam(name = "limit",required = false, defaultValue = "10")Integer limit,
                                                 @RequestParam(name = "searchContent",required = false)String searchContent){

        System.out.println("所属房间：page="+page+"  limit="+limit+" searchContent="+searchContent);
        PageHelper.startPage(page, limit);
        List<Tables> tables = tablesService.searchByRoom("%"+searchContent+"%");
        PageInfo<Tables> pageInfo = new PageInfo<>(tables);
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("data",tables);
        System.out.println(result);
        return result;
    }

    //按桌台号查询
    @ResponseBody
    @PostMapping(value="/tablesByTid",produces = "application/json;charset=UTF-8" )
    public Map<String,Object> searchTablesByTid(//page：开始页面，可以通过这个来改变页编号
                                                 @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                                 //limit：每页数据量
                                                 @RequestParam(name = "limit",required = false, defaultValue = "10")Integer limit,
                                                 @RequestParam(name = "searchContent",required = false)Integer searchContent){
        System.out.println("桌台号：page="+page+"  limit="+limit+" searchContent="+searchContent);
        PageHelper.startPage(page, limit);
        List<Tables> tables = tablesService.searchByTid(searchContent);
        PageInfo<Tables> pageInfo = new PageInfo<>(tables);

        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("data",tables);
        System.out.println(result);
        return result;
    }
    //删除当前行
    @PostMapping(value="/deletableline")
    public int deleteBytid(@RequestParam(name = "tid",required=false) Integer tid){
        System.out.println("tid="+tid);
        int i = tablesService.deleteBytid(tid);
        return i;

    }
    //编辑当前行
    @ResponseBody
    @PostMapping(value = "/edittableBytid")
    public int updateBytid(@RequestParam(name = "tid",required = false) Integer tid,@RequestParam(name = "seatnum",required = false) Integer seatnum,@RequestParam(name = "room",required = false) String room,@RequestParam(name = "status",required = false) Integer status){
        System.out.println("tid="+tid+"  seatnum="+seatnum+"  room="+room+"  status="+status);
        Tables tables = new Tables();
        tables.setTid(tid);
        tables.setSeatnum(seatnum);
        tables.setRoom(room);
        tables.setStatus(status);
        int i = tablesService.updateBytid(tables);
        return i;
    }
    //添加
    @ResponseBody
    @PostMapping(value = "/addtable")
    public int addTable(@RequestParam(name = "seatnum",required = false) Integer seatnum,@RequestParam(name = "room",required = false) String room){
        Tables tables = new Tables();
        tables.setSeatnum(seatnum);
        tables.setRoom(room);
        tables.setStatus(0);
        int i = tablesService.add(tables);
        return i;
    }


}
