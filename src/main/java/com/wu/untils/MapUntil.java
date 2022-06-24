package com.wu.untils;

import org.springframework.cglib.beans.BeanMap;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * map工具类
 */
public class MapUntil {
    /**
     * 用于将Map和Bean合并成为一个Map
     * @param object
     * @param map
     * @return
     */
    public static Map<String,Object> transMap(Object object,Map<String,Object> map){
        //BeanMap beanMap =BeanMap.create(object);
        Map<String,Object> beanMap=new HashMap<String ,Object>();
        try{
            //1.获取bean信息
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
            if(properties != null && properties.length>0){
                for (PropertyDescriptor prop :properties) {
                    //2.得到属性名
                    String name = prop.getName();
                    //3.过滤class属性
                    if(!"class".equals(name)){
                        //4.得到属性的get方法
                        Method getter = prop.getReadMethod();
                        //5.获取属性值
                        Object value = getter.invoke(object);
                        //6.放入map中
                        beanMap.put(name,value);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Set<String> set=map.keySet();
        for(String key: set){
            beanMap.put(key,map.get(key));
        }
        return beanMap;
    }



}
