package com.wu.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.Page;
import com.wu.entity.DateTime;
import com.wu.entity.PageResult;
import com.wu.entity.Result;
import com.wu.pojo.*;
import com.wu.pojo.Record;
import com.wu.service.*;
import com.wu.untils.MapUntil;
import com.wu.untils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class RecordController {

    @Autowired
    private RecordService recordService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private RecordItemService recordItemService;

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AuthorizationService authorizationService;
    @ResponseBody
    @GetMapping("/addRecord")
    //userId 用户id用于生成随机字符串
    public  Result addRecord(Record record, HttpServletRequest request){
        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");
        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getWareAuthorization()==2){
            try {
                HttpSession session = request.getSession();
                String recordCode= RandomUtils.RandomLabel(Authorization.getUser().getUserId().toString(),"Num");
                record.setRecordCode(recordCode);
                record.setRecordName(Authorization.getUser().getUserName());
                Integer count=recordService.addRecord(record);
                if(count!=1){
                    if(record.getRecordStatus()==1){
                        return  new Result(false,"出库信息添加失败");
                    }else {
                        return  new Result(false,"入库信息添加失败");
                    }
                }
                if(record.getRecordStatus()==1){
                    return  new Result(true,"出库信息添加成功");
                }else {
                    return  new Result(true,"入库信息添加成功");
                }
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false,"操作失败");
            }
        }else {
            return new Result(false,"您没有权限进行操作");
        }


    }

    @ResponseBody
    @GetMapping("/editRecord")
    public Result editRecord(Record record,HttpServletRequest request){
        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");
        if( record.getRecordOrigin()==null || record.getRecordOrigin()==""
                || record.getRecordWarehouse()==null ||record.getRecordWarehouse()==""){
            return new Result(false,"填写修改数据不规范");
        }
        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getWareAuthorization()==2){
            try {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                simpleDateFormat.format(record.getRecordTime());
                System.out.println(record.getRecordTime());
                Integer count=recordService.editRecord(record);
                if(count<=0){
                    return new Result(false,"信息修改失败");
                }else{
                    return new Result(true,"信息修改成功");
                }
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false,"信息修改失败");
            }
        }else {
            return new Result(false,"您没有权限进行操作");
        }


    }

   @ResponseBody
   @GetMapping("/deleteRecord")
   public Result deleteRecord(Integer recordId,HttpServletRequest request){
       Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");
       if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getWareAuthorization()==2){
           try {
               Integer count=recordService.deleteRecord(recordId);
               if(count!=1){
                   return new Result(false,"信息删除失败");
               }else{
                   return new Result(true,"信息删除成功");
               }
           }catch (Exception e){
               e.printStackTrace();
               return new Result(false,"信息删除失败");
           }
       }else {
           return new Result(false,"您没有权限进行操作");
       }


    }

    @ResponseBody
    @GetMapping("/searchRecord")
    public PageResult searchRecord(Record record, DateTime dateTime, Integer pageNum, Integer pageSize){

        if(pageNum==null) pageNum=1;
        if (pageSize==null)pageSize=10;
        Map<String, Object> map=new HashMap<>();
        map.put("startTime",dateTime.getStartTime());
        map.put("endTime",dateTime.getEndTime());
        Map<String,Object> map1= MapUntil.transMap(record,map);
        PageResult pageResult=recordService.selectRecord(map1,pageNum,pageSize);
        return  pageResult;
    }
    @ResponseBody
    @GetMapping("/searchTypes")
    public Result searchTypes(){
        return  new Result(true,"信息查询成功",typeService.selectAllType());
    }

    @ResponseBody
    @GetMapping("/addGoods_RecordItem")
    public Result addGoods_RecordItem(@RequestParam Map Goods_Record_Map, HttpServletRequest request){
        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");

        String str= (String) Goods_Record_Map.get("Goods_Record_Map");

        String str2= (String) Goods_Record_Map.get("Goods_Record_Id");

        Integer Goods_Record_Id=Integer.parseInt(str2);
        Map map1=(Map) JSONUtils.parse(str);


        boolean flag=true;
        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getWareAuthorization()==2){

            Set<Object> set=map1.keySet();
            for(Object key : set){

                RecordItem recordItem=new RecordItem();



                recordItem.setGoodsId(Integer.parseInt(key.toString()));

                recordItem.setRecordId(Goods_Record_Id);
                recordItem.setRecordItemCount(Integer.parseInt(map1.get(key).toString()));

                Record record=recordService.selectByRecordId(Goods_Record_Id);

                try{
                    //出库
                    if(record.getRecordStatus()==1){

                        Integer count=recordItemService.addRecordItem(recordItem);
                        //相应商品数量减少
                        Goods goods=goodsService.findGoodsById(recordItem.getGoodsId());
                        goods.setGoodsStock(goods.getGoodsStock()-recordItem.getRecordItemCount());
                        Integer count1=goodsService.updateGoods(goods);
                        if(count!=1 || count1!=1){
                            System.out.println("添加失败");
                            flag=false;
                            break;
                        }
                    }else {//入库
                        System.out.println("进入入库");
                        System.out.println(recordItem);
                        Integer count=recordItemService.addRecordItem(recordItem);
                        System.out.println();
                        //相应商品数量增加
                        Goods goods=goodsService.findGoodsById(recordItem.getGoodsId());
                        goods.setGoodsStock(goods.getGoodsStock()+recordItem.getRecordItemCount());
                        Integer count1= goodsService.updateGoods(goods);
                        System.out.println("更新商品");
                        if(count!=1 || count1 !=1){
                            System.out.println("添加失败");
                            flag=false;
                            break;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return  new Result(false,"库单明细信息添加失败");
                }

            }
            if(flag==false){
                return  new Result(false,"库单明细信息添加失败");
            }else {
                return  new Result(true,"库单明细信息添加成功");
            }
        }else {
            return new Result(false,"您没有权限进行操作");
        }
    }

}
