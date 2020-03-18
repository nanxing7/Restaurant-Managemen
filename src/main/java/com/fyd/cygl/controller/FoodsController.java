package com.fyd.cygl.controller;

import com.fyd.cygl.entity.Foods;
import com.fyd.cygl.service.FoodsService;
import com.fyd.cygl.util.UploadUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FoodsController {
    @Autowired
    private FoodsService foodsService;

    //查询所有
    @PostMapping(value="/foods",produces = "application/json;charset=UTF-8" )
    public String searchFoods(//page：开始页面，可以通过这个来改变页编号
                                            @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                            //limit：每页数据量
                                            @RequestParam(name = "limit",required = false, defaultValue = "12")Integer limit,Map<String, Object> result ){
        System.out.println("page="+page+"  limit="+limit);
        PageHelper.startPage(page, limit);
        List<Foods> foods = foodsService.selectAll();
        PageInfo<Foods> pageInfo = new PageInfo<>(foods);
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("foods",pageInfo);
        System.out.println(result);
        return "/food/fmain::test";
    }
    //分类查询
    @PostMapping(value="/foodsBySort",produces = "application/json;charset=UTF-8" )
    public String searchFoodsBySort(//page：开始页面，可以通过这个来改变页编号
                              @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                              //limit：每页数据量
                              @RequestParam(name = "limit",required = false, defaultValue = "12")Integer limit,
                                    @RequestParam(name = "sort",required = false,defaultValue = "0") Integer sort,Map<String, Object> result ){
        System.out.println("page="+page+" limit="+limit+" sort="+sort);
        PageHelper.startPage(page, limit);
        String sortStr=null;
        if (sort == 1){
            sortStr="热菜";
        }else if (sort==2){
            sortStr="凉菜";
        }else if (sort==0){
            //全部
            List<Foods> foods = foodsService.selectAll();
            PageInfo<Foods> pageInfo = new PageInfo<>(foods);
            result.put("code",0);
            result.put("count",pageInfo.getTotal());
            result.put("foods",pageInfo);
            return "/food/fmain::test";
        }
        List<Foods> foods = foodsService.selectBySort(sortStr);
        PageInfo<Foods> pageInfo = new PageInfo<>(foods);
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("foods",pageInfo);
        System.out.println(result);
        return "/food/fmain::test";
    }
    //分类查询
    @PostMapping(value="/foodsByStatus",produces = "application/json;charset=UTF-8" )
    public String searchFoodsByStatus(//page：开始页面，可以通过这个来改变页编号
                                    @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                    //limit：每页数据量
                                    @RequestParam(name = "limit",required = false, defaultValue = "12")Integer limit,
                                    @RequestParam(name = "status",required = false,defaultValue = "0") Integer status,Map<String, Object> result ){
        System.out.println("page="+page+" limit="+limit+" status="+status);
        PageHelper.startPage(page, limit);
        String statusStr=null;
        if (status == 1){
            statusStr="有";
        }else if (status==2){
            statusStr="无";
        }else if (status==0){
            //全部
            List<Foods> foods = foodsService.selectAll();
            PageInfo<Foods> pageInfo = new PageInfo<>(foods);
            result.put("code",0);
            result.put("count",pageInfo.getTotal());
            result.put("foods",pageInfo);
            return "/food/fmain::test";
        }
        List<Foods> foods = foodsService.selectByStatus(statusStr);
        PageInfo<Foods> pageInfo = new PageInfo<>(foods);
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("foods",pageInfo);
        System.out.println(result);
        return "/food/fmain::test";
    }
    //按名字查询
    @PostMapping(value="/foodsBySearchLike",produces = "application/json;charset=UTF-8" )
    public String searchFoodsByName(//page：开始页面，可以通过这个来改变页编号
                                                 @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                                 //limit：每页数据量
                                                 @RequestParam(name = "limit",required = false, defaultValue = "12")Integer limit,
                                                 @RequestParam(name = "likeContent",required = false)String likeContent,Map<String,Object> result){
        System.out.println("名字：page="+page+"  limit="+limit+" likeContent="+likeContent);
        PageHelper.startPage(page, limit);
        List<Foods> foods = foodsService.selectBySearchLike("%" + likeContent + "%");
        PageInfo<Foods> pageInfo = new PageInfo<>(foods);

        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("foods",pageInfo);
        System.out.println(result);
        return "/food/fmain::test";
    }
    //上传文件
    @ResponseBody
    @PostMapping(value="/upload",produces = "application/json;charset=UTF-8" )
    public Map<String,Object> upload(@RequestParam("file") MultipartFile file){
        Map<String,Object> msg = new HashMap<>();
        if (file.isEmpty()) {
            msg.put("code",1);
            msg.put("uploadResult","上传失败，请选择文件");
            return msg;
        }


        // 拿到文件名
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        // 存放上传图片的文件夹
        File fileDir = UploadUtils.getImgDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        System.out.println(fileDir.getAbsolutePath());

        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
            System.out.println(newFile.getAbsolutePath());

            // 上传图片到 -》 “绝对路径”
            file.transferTo(newFile);
            msg.put("code",0);
            msg.put("imgsrc","/images/"+filename);
            msg.put("uploadResult","上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    //添加新菜品
    @ResponseBody
    @PostMapping(value = "/addfood")
    public int addFood(@RequestParam(name = "name1",required = false) String name1,@RequestParam(name = "price",required = false) Double price,@RequestParam(name = "sort",required = false) Integer sort,@RequestParam(name = "status",required = false) Integer status,@RequestParam(name = "result",required = false) String result,@RequestParam(name = "imgsrc",required = false) String imgsrc){
        System.out.println("name1="+name1+" price="+price+" sort="+sort+" status="+status+" result="+result+" imgsrc="+imgsrc);
        Foods foods = new Foods();
        foods.setName(name1);
        foods.setPrice(price);
        if (sort==0){
            foods.setSort("热菜");
        }else{
            foods.setSort("凉菜");
        }
        if (status==0){
            foods.setStatus("有");
        }else{
            foods.setStatus("无");
        }
        foods.setResult(result);
        foods.setImage(imgsrc);
        int add = foodsService.add(foods);
        return add;
    }
    //编辑当前行
    @ResponseBody
    @PostMapping(value = "/editfoodByfid")
    public int updateBysid(@RequestParam(name = "fid",required = false) Integer fid,@RequestParam(name = "name1",required = false) String name1,@RequestParam(name = "price",required = false) Double price,@RequestParam(name = "sort",required = false) Integer sort,@RequestParam(name = "status",required = false) Integer status,@RequestParam(name = "result",required = false) String result,@RequestParam(name = "imgsrc",required = false) String imgsrc){
        System.out.println("fid="+fid+" name1="+name1+" price="+price+" sort="+sort+" status="+status+" result="+result+" imgsrc="+imgsrc);
        Foods foods = new Foods();
        foods.setFid(fid);
        foods.setName(name1);
        foods.setPrice(price);
        if (sort==0){
            foods.setSort("热菜");
        }else{
            foods.setSort("凉菜");
        }
        if (status==0){
            foods.setStatus("有");
        }else{
            foods.setStatus("无");
        }
        foods.setResult(result);
        foods.setImage(imgsrc);
        int i = foodsService.editByfid(foods);

        return i;
    }

    //删除当前行
    @PostMapping(value="/delefood")
    public int deleteBytid(@RequestParam(name = "fid",required=false) Integer fid){
        System.out.println("fid="+fid);
        int i = foodsService.delteByfid(fid);
        return i;

    }
}
