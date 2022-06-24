package com.wu.controller;/*
 * Created by Virok on 2022/5/31
 */

import com.wu.entity.PageResult;
import com.wu.entity.Result;
import com.wu.pojo.Authorization;
import com.wu.pojo.User;
import com.wu.service.AuthorizationService;
import com.wu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @ResponseBody
    @GetMapping("/login/checkUserEmail")
    public boolean checkUserEmail(User user) {
        boolean success = userService.existEmail(user);
        return success;
    }

    @ResponseBody
    @GetMapping("/login/checkUserAccount")
    public boolean checkUserAccount(User user){
        boolean success = userService.existUserAccount(user);
        return  success;
    }



    @ResponseBody
    @PostMapping (value = "/login")
    public Result userLogin(User user, HttpServletRequest request){
        User u = userService.login(user);

        try {
            if (u != null){
                Authorization authorization =
                        authorizationService.selectByAuthorizationId(u.getUserId());
                HttpSession session = request.getSession();
                session.setAttribute("USER_SESSION",authorization);
                return new Result(true,"登录成功",authorization);
            }


            return new Result(false,"密码错误或账号不存在");

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"密码错误或账号不存在");
        }
    }

    @ResponseBody
    @GetMapping ("/login/userRegister")
    public boolean userRegister(User user){
        boolean success = userService.register(user);
        return success;
    }

    @ResponseBody
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        try{
            request.getSession().removeAttribute("USER_SESSION");
            return new Result(true,"退出成功!");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"退出失败!");
        }
    }

    @ResponseBody
    @GetMapping("/searchUser")
    public PageResult searchUser(User user, Integer pageNum, Integer pageSize){
        if(pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 10;
        PageResult pageResult = userService.selectUser(user,pageNum,pageSize);
        return pageResult;
    }


    @ResponseBody
    @GetMapping("/editUser")
    public Result editUser(User user) throws ParseException {
        try{
            Integer count = userService.editUser(user);
            if (count != 1)
                return new Result(false,"信息修改失败!");
            return new Result(true,"信息修改成功!");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"信息修改失败!");
        }
    }


    @ResponseBody
    @GetMapping("/addUser")
    public Result addUser(User user){
        try{
            Integer count  = userService.addUser(user);
            if (count != 1)
                return new Result(false,"用户信息添加失败!");
            return new Result(true,"用户信息添加成功!");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"用户信息添加失败!");
        }
    }

    @ResponseBody
    @GetMapping("/findUserById")
    public Result findUserById(Integer userId){
        try{
            User user = userService.findById(userId);
            if (user == null)
                return new Result(false,"查询失败!");
            return new Result(true,"查询用户信息成功!",user);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"查询失败!");
        }
    }

    @ResponseBody
    @GetMapping("deleteUser")
    public Result deleteUser(Integer userId){
        try{

            Integer count1 = authorizationService.deleteAuthorization(userId);
            Integer count2 = userService.deleteUser(userId);
            if (count1 != 1 || count2 != 1)
                return new Result(false,"删除失败!");
            return new Result(true,"删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }


    @ResponseBody
    @GetMapping("/changePwd")
    public Result changePwd(User user){
        try{
            Integer count = userService.editUser(user);
            if (count == 1)
                return new Result(true,"修改密码成功，请重新登录!");
            return new Result(false,"密码修改失败!");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"密码修改失败!");
        }
    }
}

