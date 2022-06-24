package com.wu.controller;
import com.wu.entity.PageResult;
import com.wu.entity.Result;
import com.wu.pojo.Authorization;
import com.wu.pojo.User;
import com.wu.service.AuthorizationService;
import com.wu.untils.MapUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;

    @ResponseBody
    @GetMapping("/searchAuthorization")
    public PageResult searchAuthorization(Authorization authorization,String userAccount,String userName,Integer pageNum,Integer pageSize){
        if(pageNum==null) pageNum=1;
        if (pageSize==null)pageSize=10;
        Map<String, Object> map=new HashMap<>();
        map.put("userAccount",userAccount);
        map.put("userName",userName);
        Map<String,Object> map1= MapUntil.transMap(authorization,map);
        PageResult pageResult=authorizationService.selectAuthorization(map1,pageNum,pageSize);
        return  pageResult;
    }

    @ResponseBody
    @GetMapping("/editAuthorization")
    //status 1代表解除权限 2代表赋予权限 authorizationStatus 1代表用户权限 2代表商品权限 3代表库存权限
    public Result editAuthorization(Authorization authorization , Integer status, Integer authorizationStatus, HttpServletRequest request){
        Authorization auth = (Authorization)request.getSession().getAttribute("USER_SESSION");
        Integer role=auth.getUser().getUserRole();
        if(role==1){
            return new Result(false,"您没有权限赋予他人权限");
        }else{
            if(authorizationStatus==1){
                authorization.setUserAuthorization(status);
            }else if(authorizationStatus==2){
                authorization.setGoodsAuthorization(status);
            }else if(authorizationStatus==3){
                authorization.setWareAuthorization(status);
            }
            try {
                Integer count=authorizationService.editAuthorization(authorization);
                if(count !=1){
                    return new Result(false,"权限操作失败");
                }else {
                    return new Result(true,"权限操作成功");
                }
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false,"权限操作失败");
            }
        }

    }


    @ResponseBody
    @GetMapping("/selectByAuthorizationId")
    public Result selectByAuthorizationId(Integer userId){
        try{
            Authorization authorization = authorizationService.selectByAuthorizationId(userId);
            return new Result(true,"查找成功!",authorization);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"查找失败!");
        }

    }

}
