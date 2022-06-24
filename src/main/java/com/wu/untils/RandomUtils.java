package com.wu.untils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomUtils {

    /**
     *
     * @param uid 标识用户登录Id
     * @param preStr 用于指定随机首部字母
     * @return
     */
    public static String RandomLabel(String uid,String preStr){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String label=preStr;
        label+=simpleDateFormat.format(date);
        label=(label+uid).replace(" ","");
        label=label.replace("-","");
        label=label.replace(":","");
        return label;
    }


}
