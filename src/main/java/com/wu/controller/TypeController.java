package com.wu.controller;

import com.wu.entity.PageResult;
import com.wu.entity.Result;
import com.wu.pojo.Authorization;
import com.wu.pojo.Type;
import com.wu.pojo.User;
import com.wu.service.AuthorizationService;
import com.wu.service.TypeService;
import com.wu.untils.MapUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private AuthorizationService authorizationService;


    @ResponseBody
    @GetMapping("/searchType")
    public PageResult searchUser(Type type, Integer pageNum, Integer pageSize){
        if(pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 10;
        PageResult pageResult = typeService.selectType(type,pageNum,pageSize);
        return pageResult;
    }


    @ResponseBody
    @GetMapping("/editType")
    public Result editUser(Type type, HttpServletRequest request) throws ParseException {
        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");
        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getGoodsAuthorization()==2){
            try{
                Integer count = typeService.editType(type);
                if (count != 1)
                    return new Result(false,"信息修改失败!");
                return new Result(true,"信息修改成功!");
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false,"信息修改失败!");
            }
        }else {
            return new Result(false,"您没有权限进行操作");
        }

    }


    @ResponseBody
    @GetMapping("/addType")
    public Result addUser(Type type,HttpServletRequest request){
        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");
        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getGoodsAuthorization()==2){
            try{
                Integer count  = typeService.addType(type);
                if (count != 1)
                    return new Result(false,"商品类别信息添加失败!");
                return new Result(true,"商品类别信息添加成功!");
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false,"商品类别信息添加失败!");
            }
        }else {
            return new Result(false,"您没有权限进行操作");
        }

    }

    @ResponseBody
    @GetMapping("/deleteType")
    public Result deleteUser(Integer typeId,HttpServletRequest request){
        Authorization Authorization = (Authorization)request.getSession().getAttribute("USER_SESSION");
        if(Authorization.getUser().getUserRole()==0||authorizationService.selectByAuthorizationId(Authorization.getUser().getUserId()).getGoodsAuthorization()==2){
            try{
                Integer count = typeService.deleteType(typeId);
                if (count != 1)
                    return new Result(false,"删除失败!");
                return new Result(true,"删除成功!");
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false,"删除失败!");
            }
        }else {
            return new Result(false,"您没有权限进行操作");
        }

    }

}
