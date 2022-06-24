package com.wu.controller;

import com.wu.entity.PageResult;
import com.wu.entity.Result;
import com.wu.pojo.Authorization;
import com.wu.pojo.Goods;
import com.wu.pojo.RecordItem;
import com.wu.pojo.User;
import com.wu.service.AuthorizationService;
import com.wu.service.GoodsService;
import com.wu.service.RecordItemService;
import com.wu.service.RecordService;
import com.wu.untils.MapUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RecordItemController {
    @Autowired
    private RecordItemService recordItemService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AuthorizationService authorizationService;

    @ResponseBody
    @GetMapping("/addRecordItem")
    public Result addRecordItem(RecordItem recordItem,String goodsCode,String recordCode,HttpServletRequest request,Integer  recordStatus){
        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");
        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getWareAuthorization()==2){
            System.out.println(recordItem);
            System.out.println(goodsCode);
            System.out.println(recordCode);
            if(recordService.selectByRecordCode(recordCode)==null || goodsService.findGoodsByCode(goodsCode)==null){
                System.out.println("判断不存在");
                return new Result(false,"库单或商品不存在");
            }else {
                System.out.println("判断存在");

                //反映当前订单详细是属于出库订单还是属于入库订单 1/2 出/入
                if(recordStatus != recordService.selectByRecordCode(recordCode).getRecordStatus()){
                    return new Result(false,"库单和库单详情不匹配");
                }else {
                    try {
                        recordItem.setRecordId(recordService.selectByRecordCode(recordCode).getRecordId());
                        recordItem.setGoodsId(goodsService.findGoodsByCode(goodsCode).getGoodsId());
                        //获取当前商品库存
                        Integer stock=goodsService.findGoodsById(recordItem.getGoodsId()).getGoodsStock();
                        //如果是出库
                        if(recordStatus==1){
                            //如果库存小于出库数量，则出库失败
                            if(stock.intValue()<recordItem.getRecordItemCount().intValue()){
                                return  new Result(false,"库存不足");
                            }else {
                                Integer count=recordItemService.addRecordItem(recordItem);
                                //相应商品数量减少
                                Goods goods=goodsService.findGoodsById(recordItem.getGoodsId());
                                goods.setGoodsStock(goods.getGoodsStock()-recordItem.getRecordItemCount());
                                Integer count1=goodsService.updateGoods(goods);
                                if(count!=1 || count1!=1){
                                    return  new Result(false,"出库明细信息添加失败");
                                }else{
                                    return  new Result(false,"出库明细信息添加成功");
                                }
                            }
                        }else {//如果是入库
                            Integer count=recordItemService.addRecordItem(recordItem);
                            //相应商品数量增加
                            Goods goods=goodsService.findGoodsById(recordItem.getGoodsId());
                            goods.setGoodsStock(goods.getGoodsStock()+recordItem.getRecordItemCount());
                            Integer count1= goodsService.updateGoods(goods);
                            if(count!=1 || count1 !=1){
                                return  new Result(false,"入库明细信息添加失败");
                            }else{
                                return  new Result(false,"入库明细信息添加成功");
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        if(recordStatus==1){
                            return  new Result(false,"出库明细信息添加失败");
                        }else{
                            return  new Result(false,"入库明细信息添加失败");
                        }
                    }
                }

            }

        }else {
            return new Result(false,"您没有权限进行操作");
        }



    }






    @ResponseBody
    @GetMapping("/editRecordItem")
    public Result editRecord(RecordItem recordItem, String goodsCode, HttpServletRequest request){
        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");
        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getWareAuthorization()==2){

            if(goodsService.findGoodsByCode(goodsCode)==null){
                return new Result(false,"商品不存在,修改失败");
            }else {
                //修改后的新商品
                Goods updateGoods=goodsService.findGoodsByCode(goodsCode);
                //判断是出库还是入库
                Integer recordStatus=recordService.selectByRecordId(recordItem.getRecordId()).getRecordStatus();
                //修改前的订单详情信息
                RecordItem recordItem1=recordItemService.selectByRecordItemId(recordItem.getRecordItemId());
                //商品没变的情况
                if(updateGoods.getGoodsId().intValue()==recordItem.getGoodsId().intValue()){
                    try {
                        //订单修改之前的的订单商品数量
                        Integer beforeCount=recordItem1.getRecordItemCount();

                        //订单修改之后的订单商品数量
                        Integer afterCount=recordItem.getRecordItemCount();
                        //订单详情修改前后的商品数量变化
                        Integer subNum=beforeCount-afterCount;
                        //如果是出库修改
                        if(recordStatus==1){
                            //如果出库订单详细 修改后的订单商品数量比之前的少,说明对应的商品数量需要增加

                            if(subNum>0){
                                Goods goods=goodsService.findGoodsById(recordItem.getGoodsId());
                                goods.setGoodsStock(goods.getGoodsStock()+subNum);
                                //更新出库明细和商品信息
                                Integer count=recordItemService.editRecordItem(recordItem);
                                Integer count1=goodsService.updateGoods(goods);

                                if(count!=1 || count1 !=1){
                                    return new Result(false,"信息修改失败");
                                }else{
                                    return new Result(true,"信息修改成功");
                                }
                            }else{//如果出库订单明细 修改订单明细商品数量比之前多 说明对应的商品数量需要减少，在减少操作前还需要进一步判断商品库存数量是否足够
                                //获取对应商品实体
                                Goods goods=goodsService.findGoodsById(recordItem.getGoodsId());
                                //此时subNum为负数 ，相加小于0表示库存不足 无法修改
                                if(goods.getGoodsStock()+subNum<0){
                                    return new Result(false,"库存不足修改失败");
                                }else {//subNum此时为负数 如果相加大于等于0 表示商品数量足够可以修改
                                    //商品信息进行修改
                                    goods.setGoodsStock(goods.getGoodsStock()+subNum);
                                    Integer count=recordItemService.editRecordItem(recordItem);
                                    Integer count1=goodsService.updateGoods(goods);
                                    if(count!=1 || count1 !=1){
                                        return new Result(false,"信息修改失败");
                                    }else{
                                        return new Result(true,"信息修改成功");
                                    }
                                }
                            }
                        }else {//如果是入库修改订单明细信息
                            //如果入库订单详细 修改后的订单商品数量比之前的少,说明对应的商品数量需要减少 在减少前需要判断当前库存是否可以减少
                            if(subNum>0){
                                //获取对应商品实体
                                Goods goods=goodsService.findGoodsById(recordItem.getGoodsId());
                                //此时subNum为正数 ，相减小于0表示库存不足 无法修改
                                if(goods.getGoodsStock()-subNum<0){
                                    return new Result(false,"库存不足修改失败");
                                }else {//subNum此时为正数 如果相减大于等于0 表示商品数量足够可以修改
                                    //商品信息进行修改
                                    goods.setGoodsStock(goods.getGoodsStock()-subNum);
                                    Integer count=recordItemService.editRecordItem(recordItem);
                                    Integer count1=goodsService.updateGoods(goods);
                                    if(count!=1 || count1 !=1){
                                        return new Result(false,"信息修改失败");
                                    }else{
                                        return new Result(true,"信息修改成功");
                                    }
                                }

                            }else{//如果入库库订单明细 修改订单明细商品数量比之前多 说明对应的商品数量需要增加此时subNum为负数
                                Goods goods=goodsService.findGoodsById(recordItem.getGoodsId());
                                goods.setGoodsStock(goods.getGoodsStock()-subNum);
                                //更新出库明细和商品信息
                                Integer count=recordItemService.editRecordItem(recordItem);
                                Integer count1=goodsService.updateGoods(goods);
                                if(count!=1 || count1 !=1){
                                    return new Result(false,"信息修改失败");
                                }else{
                                    return new Result(true,"信息修改成功");
                                }
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        return new Result(false,"信息修改失败");
                    }
                }else {//商品变了的情况
                    //若是出库单只有新商品库存够的情况才能修改，同时修改后老商品需要加上目前修改的商品数量，而新商品需要减少对应数量
                    // 若是入库单无需考虑新商品库存，但是需要考虑旧商品库存是否充足，若是充足则新商品加上数量，旧商品减去数量
                    try {
                        //出库
                        if(recordStatus.intValue()==1){
                            //新商品库存够
                            if(updateGoods.getGoodsStock().intValue()>recordItem.getRecordItemCount().intValue()){
                                //老商品
                                Goods oldGoods=goodsService.findGoodsById(recordItem.getGoodsId());
                                //老商品需要加上目前修改的商品数量
                                oldGoods.setGoodsStock(oldGoods.getGoodsStock()+recordItem.getRecordItemCount());
                                int flag1=goodsService.updateGoods(oldGoods);
                                //新商品需要减少对应数量
                                updateGoods.setGoodsStock(updateGoods.getGoodsStock()-recordItem.getRecordItemCount());
                                int flag2=goodsService.updateGoods(updateGoods);
                                if(flag1!=1 || flag2 !=1){
                                    return new Result(false,"信息修改失败");
                                }else{
                                    return new Result(true,"信息修改成功");
                                }
                            }else {
                                return new Result(false,"修改的新商品库存不足");
                            }
                        }else{//入库
                            //老商品
                            Goods oldGoods=goodsService.findGoodsById(recordItem.getGoodsId());
                            //老商品库存充足
                            if(oldGoods.getGoodsStock().intValue()>recordItem.getRecordItemCount()){
                                //老商品需要减去目前修改的商品数量
                                oldGoods.setGoodsStock(oldGoods.getGoodsStock()-recordItem.getRecordItemCount());
                                int flag1=goodsService.updateGoods(oldGoods);
                                //新商品需要增加对应数量
                                updateGoods.setGoodsStock(updateGoods.getGoodsStock()+recordItem.getRecordItemCount());
                                int flag2=goodsService.updateGoods(updateGoods);
                                if(flag1!=1 || flag2 !=1){
                                    return new Result(false,"信息修改失败");
                                }else{
                                    return new Result(true,"信息修改成功");
                                }
                            }else { //老商品库存不足
                                return new Result(false,"修改前的老商品库存不足");
                            }


                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        return new Result(false,"信息修改失败");
                    }

                }



            }
        }else {
            return new Result(false,"您没有权限进行操作");
        }



    }


    @ResponseBody
    @GetMapping("/deleteRecordItem")
    public Result deleteItemRecord(Integer recordItemId,HttpServletRequest request){

        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");
        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getWareAuthorization()==2){
            try {
                System.out.println(recordItemId);
                Integer count=recordItemService.deleteRecordItem(recordItemId);
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
    @GetMapping("/searchRecordItem")
    public PageResult searchRecordItem(RecordItem record,String recordCode,Integer recordStatus,Integer pageNum, Integer pageSize){
        System.out.println("进入RecordItem查询");
        if(pageNum==null) pageNum=1;
        if (pageSize==null)pageSize=10;
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("recordStatus",recordStatus);
        map.put("recordCode",recordCode);
        Map<String,Object> map1= MapUntil.transMap(record,map);
        PageResult pageResult=recordItemService.selectRecordItem(map1,pageNum,pageSize);
        return  pageResult;
    }


}
