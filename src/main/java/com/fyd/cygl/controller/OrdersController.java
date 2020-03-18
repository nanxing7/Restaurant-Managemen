package com.fyd.cygl.controller;

import com.fyd.cygl.entity.*;
import com.fyd.cygl.service.*;
import com.fyd.cygl.util.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OrdersController {
    @Autowired
    TablesService tablesService;
    @Autowired
    StaffsService staffsService;
    @Autowired
    FoodsService foodsService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderDetailService orderDetailService;
    //主页显示所有桌子
    @PostMapping(value = "/ordertables",produces = "application/json;charset=UTF-8" )
    public String searchAllTable(@RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                             //limit：每页数据量
                                             @RequestParam(name = "limit",required = false, defaultValue = "12")Integer limit,Map<String,Object> result){
        PageHelper.startPage(page, limit);
        List<Tables> tables = tablesService.searchAll();
        PageInfo<Tables> pageInfo = new PageInfo<>(tables);
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("tables",pageInfo);
        return "/order/main::test";
    }
    @PostMapping(value = "/fenlei",produces = "application/json;charset=UTF-8" )
    public String fenleichaxun(@RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                               //limit：每页数据量
                               @RequestParam(name = "limit",required = false, defaultValue = "12")Integer limit,@RequestParam(name = "optionvalue",required = false)Integer optionvalue,Map<String,Object> result){
        PageHelper.startPage(page, limit);
        List<Tables> tables = new ArrayList<>();
        if (optionvalue==0){
            //全部
            tables= tablesService.searchAll();
        }else if (optionvalue==1){
            //未使用
            tables = tablesService.searchBystatus(0);
        }else if (optionvalue==2){
            //已使用
            tables = tablesService.searchBystatus(1);
        }
        PageInfo<Tables> pageInfo = new PageInfo<>(tables);
        result.put("code",0);
        result.put("count",pageInfo.getTotal());
        result.put("tables",pageInfo);
        return "/order/main::test";
    }
    //主页鼠标滑动显示对应订单信息
    @ResponseBody
    @PostMapping(value = "/mouseoverorderdetails")
    public Map<String,Object> mouseOverOrderDetails(@RequestParam(name = "tid",required = false)Integer tid){
        Orders orders = ordersService.selectByTidAndOrderStatus(tid, 0);
        List<OrderDetail> orderDetails = orderDetailService.selectByOid(orders.getOid());
        String tips = orderDetails.get(orderDetails.size() - 1).getTips();
        Map<String,Object> result = new HashMap<>();
        result.put("order",orders);
        result.put("orderdetail",orderDetails);
        result.put("tips",tips);
        return result;
    }

    //新增订单里所负责员工下拉框的数据
    @ResponseBody
    @PostMapping(value = "/orderstaffs",produces = "application/json;charset=UTF-8" )
    public Map<String,Object>  searchAllStaffs(){
        Map<String,Object> result = new HashMap<>();
        List<Staffs> staffs = staffsService.searchAll();
        result.put("code",0);
        result.put("staffsid",staffs);
        return result;
    }
    @ResponseBody
    @PostMapping(value = "/orderfoods",produces = "application/json;charset=UTF-8" )
    public Map<String,Object>  searchAllFoods(){
        Map<String,Object> result = new HashMap<>();
        List<Foods> recai = foodsService.selectBySort("热菜");
        List<Foods> liangcai = foodsService.selectBySort("凉菜");
        result.put("code",0);
        result.put("recai",recai);
        result.put("liangcai",liangcai);
        return result;
    }
    @ResponseBody
    @PostMapping(value = "/newInsertOrder")
    public int addOrder(@RequestParam(name = "oid",required = false) String oid,
                        @RequestParam(name = "tid",required = false) Integer tid,
                        @RequestParam(name = "number",required = false) Integer number,
                        @RequestParam(name = "sid",required = false) Integer sid,
                        @RequestParam(name = "name1",required = false) String name1,
                        @RequestParam(name = "phone",required = false) String phone,
                        @RequestParam(name = "tips",required = false) String tips,
                        @RequestParam(name = "recaiarr",required = false) String[] recaiarr,
                        @RequestParam(name = "liangcaiarr",required = false) String[] liangcaiarr
                        ){
        System.out.println("oid="+oid+" tid="+tid+" number="+number+" sid="+sid+" name1="+name1+" phone="+phone+" tips="+tips+" recaiarr="+recaiarr+" liangcaiarr="+liangcaiarr.toString());
        List<String> recailist = Arrays.asList(recaiarr);
        List<String> liangcailist = Arrays.asList(liangcaiarr);
        List<String> rejialianglist = new ArrayList<>();
        //合并热菜和凉菜到一个list
        //数组非空才合并，若有一个为空，则这个空的数组不添加
        if (!recailist.get(0).equals("[]")){
            rejialianglist.addAll(recailist);
        }
        if (!liangcailist.get(0).equals("[]")){
            rejialianglist.addAll(liangcailist);
        }
        Orders orders = new Orders();
        orders.setOid(oid);
        orders.setTid(tid);
        orders.setNumber(number);
        orders.setSid(sid);
        orders.setName(name1);
        orders.setPhone(phone);
        orders.setTime(DateUtils.getDate2());
        orders.setStatus(0);//0:未完成，1：已完成
        //在order表中添加一行新数据
        int orderresult = ordersService.addOrder(orders);
        if (orderresult==0){
            return 0;
        }
        //去除list集合里的特殊字符
        for (int i=1;i<=rejialianglist.size();i++){
            String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？\"]";
            //可以在中括号内加上任何想要替换的字符，实际上是一个正则表达式
            String aa="";//这里是将特殊字符换为aa字符串,""代表直接去掉
            rejialianglist.set(i-1,rejialianglist.get(i-1).replaceAll(regEx,aa));
        }
        OrderDetail orderDetail = new OrderDetail();
        for (int i=1;i<=rejialianglist.size();i++){
            orderDetail.setNumber(i);
            orderDetail.setFname(rejialianglist.get(i-1));
            orderDetail.setOid(oid);
            orderDetail.setTips(tips);
            Foods foods = foodsService.selectByName(rejialianglist.get(i - 1));
            orderDetail.setPrice(foods.getPrice());
            int orderDetailresult = orderDetailService.insertOrderDetail(orderDetail);
            if (orderDetailresult==0){
                return 0;
            }
        }
        //更新桌子使用状态
        List<Tables> tables = tablesService.searchByTid(tid);
        tables.get(0).setStatus(1);
        tablesService.updateBytid(tables.get(0));
        return 1;
    }
    //通过桌台号tid和未完成的订单状态status:0查询其他信息填充到修改表单里。
    @ResponseBody
    @PostMapping(value = "/updateselectOrderByoid2")
    public Map<String,Object> searchOrderandOrderDetailByTid(@RequestParam(name = "tid",required = false) Integer tid){
        System.out.println("tid="+tid);
        Map<String,Object> result = new HashMap<>();
        Orders orders = ordersService.selectByTidAndOrderStatus(tid,0);
        result.put("orders",orders);
        List<OrderDetail> orderDetails = orderDetailService.selectByOid(orders.getOid());
        result.put("orderdetail",orderDetails);
        result.put("tip",orderDetails.get(orderDetails.size()-1).getTips());
        return result;
    }
    //通过oid查询该订单已选的菜品
    @ResponseBody
    @PostMapping(value = "/orderselectedfood")
    public Map<String,Object> searchSelectedFoodByOid(@RequestParam(name = "oid",required = false) String oid){
        Map<String,Object> result = new HashMap<>();
        List<OrderDetail> orderDetails = orderDetailService.selectByOid(oid);
        result.put("orderDetails",orderDetails);
        return result;
    }
    //修改已点订单
    @ResponseBody
    @PostMapping(value = "/updateSelectedOrder")
    public int updateSelectedOrder(@RequestParam(name = "oid",required = false) String oid,
                        @RequestParam(name = "tid",required = false) Integer tid,
                        @RequestParam(name = "number",required = false) Integer number,
                        @RequestParam(name = "sid",required = false) Integer sid,
                        @RequestParam(name = "name1",required = false) String name1,
                        @RequestParam(name = "phone",required = false) String phone,
                        @RequestParam(name = "tips",required = false) String tips,
                        @RequestParam(name = "recaiarr",required = false) String[] recaiarr,
                        @RequestParam(name = "liangcaiarr",required = false) String[] liangcaiarr
    ){
        System.out.println("oid="+oid+" tid="+tid+" number="+number+" sid="+sid+" name1="+name1+" phone="+phone+" tips="+tips+" recaiarr="+recaiarr+" liangcaiarr="+liangcaiarr.toString());
        List<String> recailist = Arrays.asList(recaiarr);
        List<String> liangcailist = Arrays.asList(liangcaiarr);
        List<String> rejialianglist = new ArrayList<>();
        //合并热菜和凉菜到一个list
        //数组非空才合并，若有一个为空，则这个空的数组不添加
        if (!recailist.get(0).equals("[]")){
            rejialianglist.addAll(recailist);
        }
        if (!liangcailist.get(0).equals("[]")){
            rejialianglist.addAll(liangcailist);
        }
        Orders orders = new Orders();
        orders.setOid(oid);
        orders.setTid(tid);
        orders.setNumber(number);
        orders.setSid(sid);
        orders.setName(name1);
        orders.setPhone(phone);
        //获取之前的下单时间
        Orders olddata = ordersService.selectByOid(oid);
        orders.setTime(olddata.getTime());
        orders.setStatus(0);//0:未完成，1：已完成
        //在order表中更新该条数据
        int orderresult = ordersService.update(orders);
        if (orderresult==0){
            return 0;
        }
        //去除list集合里的特殊字符
        for (int i=1;i<=rejialianglist.size();i++){
            String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？\"]";
            //可以在中括号内加上任何想要替换的字符，实际上是一个正则表达式
            String aa="";//这里是将特殊字符换为aa字符串,""代表直接去掉
            rejialianglist.set(i-1,rejialianglist.get(i-1).replaceAll(regEx,aa));
        }
        OrderDetail orderDetail = new OrderDetail();
        List<OrderDetail> orderDetails = orderDetailService.selectByOid(oid);
        for (int i=orderDetails.size()+1;i<=rejialianglist.size()+orderDetails.size();i++){
            orderDetail.setNumber(i);
            orderDetail.setFname(rejialianglist.get(i-orderDetails.size()-1));
            orderDetail.setOid(oid);
            orderDetail.setTips(tips);
            Foods foods = foodsService.selectByName(rejialianglist.get(i-orderDetails.size()- 1));
            if (foods!=null){
                orderDetail.setPrice(foods.getPrice());
            }
            int orderDetailresult = orderDetailService.insertOrderDetail(orderDetail);
            if (orderDetailresult==0){
                return 0;
            }
        }
        return 1;
    }
    //删除当前点击的已选菜品
    @ResponseBody
    @PostMapping(value = "/deleteoneselectedfood")
    public int deleteOneSelectedFood(@RequestParam(name = "oid",required = false) String oid,
                                     @RequestParam(name = "foodname",required = false) String foodname,
                                     @RequestParam(name = "number",required = false) Integer number){
        int result = orderDetailService.deleteByOidAndFnameAndNumber(oid, foodname, number);
        //更新食物序号
        List<OrderDetail> orderDetails = orderDetailService.selectByOid(oid);
        for (int i=1;i<=orderDetails.size();i++){
            orderDetails.get(i-1).setNumber(i);
            int update = orderDetailService.update(orderDetails.get(i - 1));
            if (update==0){
                return 0;
            }
        }
        return result;
    }

    @ResponseBody
    @PostMapping(value = "/payselectdata")
    public Map<String,Object> paysearchOrderandOrderDetailBytid(@RequestParam(name = "tid",required = false) Integer tid){
        System.out.println("tid="+tid);
        Map<String,Object> result = new HashMap<>();
        Orders orders = ordersService.selectByTidAndOrderStatus(tid,0);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(orders.getTime());
        result.put("time",time);
        result.put("orders",orders);
        List<OrderDetail> orderDetails = orderDetailService.selectByOid(orders.getOid());
        result.put("orderdetail",orderDetails);
        Double preprice = orderDetailService.countFoodPrice(orders.getOid());
        result.put("preprice",preprice);
        return result;
    }
    //支付页面查询当前订单的菜品
    @ResponseBody
    @PostMapping(value="/orderdetailscurrent",produces = "application/json;charset=UTF-8" )
    public Map<String, Object> paysearchOrderDetails(//page：开始页面，可以通过这个来改变页编号
                                            @RequestParam(name = "page",required = false, defaultValue = "1")Integer page,
                                            //limit：每页数据量
                                            @RequestParam(name = "limit",required = false, defaultValue = "10")Integer limit,
                                            @RequestParam(name = "oid",required = false) String oid){
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
    //完成支付
    @ResponseBody
    @PostMapping(value="/payfinish" )
    public int payfinish(@RequestParam(name = "tid",required = false) Integer tid,
                         @RequestParam(name = "oid",required = false) String oid,
                         @RequestParam(name = "preprice",required = false) Double preprice,
                         @RequestParam(name = "discountprice",required = false) Double discountprice,
                         @RequestParam(name = "realprice",required = false) Double realprice,
                         @RequestParam(name = "payment",required = false) String payment){
        Orders orders = ordersService.selectByOid(oid);
        orders.setPreprice(preprice);
        orders.setDiscountprice(discountprice);
        orders.setRealprice(realprice);
        orders.setPayment(payment);
        orders.setStatus(1);//设置订单状态完成
        int update = ordersService.update(orders);
        if (update==0){
            return 0;
        }
        List<Tables> tables = tablesService.searchByTid(tid);
        if (tables.size()==1){
            tables.get(0).setStatus(0);//设置餐桌空闲
            int i = tablesService.updateBytid(tables.get(0));
            if (i==0){
                return 0;
            }
        }
        return 1;
    }
    //删除当前房间的订单
    @ResponseBody
    @PostMapping(value="/deletecurrentorder" )
    public int deletecurrentorder(@RequestParam(name = "tid",required = false) Integer tid){
        Orders orders = ordersService.selectByTidAndOrderStatus(tid, 0);
        int i = ordersService.deleteorderByOid(orders.getOid());
        if (i==0){
            return 0;
        }
        System.out.println(orders.getOid());
        int i1 = orderDetailService.deleteorderdetailByOid(orders.getOid());
        if (i1==0){
            return 0;
        }
        List<Tables> tables = tablesService.searchByTid(tid);
        if (tables.size()==1){
            tables.get(0).setStatus(0);
            int i2 = tablesService.updateBytid(tables.get(0));
            if (i2==0){
                return 0;
            }
        }
        return 1;
    }

}
