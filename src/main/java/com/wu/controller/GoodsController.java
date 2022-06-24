package com.wu.controller;

import com.wu.entity.PageResult;
import com.wu.entity.Result;
import com.wu.pojo.Authorization;
import com.wu.pojo.Goods;
import com.wu.pojo.User;
import com.wu.service.AuthorizationService;
import com.wu.service.GoodsService;
import com.wu.service.UserService;
import com.wu.untils.MapUntil;
import com.wu.untils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorizationService authorizationService;

    @ResponseBody
    @RequestMapping("/deleteGoods")
    public Result deleteGoods(Integer goodsId,HttpServletRequest request){
        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");
        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getGoodsAuthorization()==2){
            System.out.println("这是商品删除请求");
            try {
                Integer count=goodsService.deleteGoods(goodsId);
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
            System.out.println("hello");
            return new Result(false,"您没有权限删除");
        }


    }
    @ResponseBody
    @RequestMapping("/addGoods")
    public Result addGoods(Goods goods, HttpServletRequest request){
        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");
        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getGoodsAuthorization()==2){
            try {
                if(goods.getTypeId()!=null || goods.getTypeId()!=0){

                    HttpSession session = request.getSession();
                    Integer userId=Authorization.getUser().getUserId();
                    if(userId==null||userId==0){
                        return  new Result(false,"请先登录");
                    }else {
                        goods.setGoodsCode(RandomUtils.RandomLabel(userId.toString(),"Num"));

                        Integer count=goodsService.addGoods(goods);
                        if(count!=1){
                            return  new Result(false,"信息添加失败");
                        }
                        else {

                            return  new Result(true,"信息添加成功");
                        }
                    }

                }else {
                    return  new Result(false,"商品类别不能为空");
                }

            }catch (Exception e){
                e.printStackTrace();
                return new Result(false,"操作失败");
            }
        }else {
            return new Result(false,"您没有权限增加");
        }

    }
    @ResponseBody
    @RequestMapping("/editGoods")
    public Result editGoods(Goods goods,HttpServletRequest request){
        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");


        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getGoodsAuthorization()==2){
            try {
                if(goods.getGoodsPrice()<0|| goods.getGoodsStock()<0 || goods.getGoodsName()==null ||goods.getGoodsName()==""
                        ||goods.getGoodsSize()==null || goods.getGoodsSize()=="" || goods.getGoodsColor()==null || goods.getGoodsColor()==""
                        || goods.getGoodsCompany()==null ||goods.getGoodsCompany()==""){
                    return new Result(false,"填写修改数据不规范");
                }

                System.out.println("修改前：");
                System.out.println(goodsService.findGoodsById(goods.getGoodsId()));

                Integer count=goodsService.updateGoods(goods);
                System.out.println("修改后");
                System.out.println(goodsService.findGoodsById(goods.getGoodsId()));

                if(count <=0){
                    return new Result(false,"信息修改失败");
                }else {
                    return new Result(true,"信息修改成功");
                }
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false,"信息修改失败");
            }
        }else {
            return new Result(false,"您没有权限编辑");
        }

    }

    @ResponseBody
    @RequestMapping("/findGoods")
    //status 1表示有库存 2表示无库存 0表示所有库存
    public PageResult findGoods(Goods goods,Float lowPrice,Float highPrice,Integer status ,Integer pageNum, Integer pageSize){
        if(pageNum==null) pageNum=1;
        if (pageSize==null)pageSize=10;
       // System.out.println("findGoodsController");
        Map<String, Object> map=new HashMap<>();
        Map<String,Object> map1= MapUntil.transMap(goods,map);
        map1.put("lowPrice",lowPrice);
        map1.put("highPrice",highPrice);
        map1.put("status",status);
        PageResult pageResult=goodsService.selectGoods(map1,pageNum,pageSize);

        return  pageResult;
    }


}