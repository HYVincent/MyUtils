package com.vincent.library.util;


import android.text.TextUtils;

import com.vincent.library.test.AddressEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description ：
 * project name：CCloud
 * author : Vincent
 * creation date: 2017/5/4 18:35
 *
 * @version 1.0
 */

public class ListUtils<T> {

    /**
     * List数据模糊查询 注意非空判断 否则空指针
     * @param name
     * @param list
     * @return
     */
    public static List search(String name,List list){
        List results = new ArrayList();
        Pattern pattern = Pattern.compile(name);
        for(int i=0; i < list.size(); i++){
            Matcher matcher = pattern.matcher(((AddressEntity)list.get(i)).getConsignee());
            if(matcher.find()){
                results.add(list.get(i));
            }
        }
        return results;
    }

    /**
     * 模糊查询 注意非空判断 否则空指针
     * @param name
     * @param list
     * @return
     */
    public static List search2(String name,List list){
        List results = new ArrayList();
        Pattern pattern = Pattern.compile(name);
        for(int i=0; i < list.size(); i++){
            Matcher matcher = pattern.matcher(((AddressEntity)list.get(i)).getContactNumber());
            if(matcher.find()){
                results.add(list.get(i));
            }
        }
        return results;
    }

    /**
     * 去重 注意非空判断 否则空指针
     * @param list
     * @return
     */
    public static List clear(List list){
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    public static List search3(String name,List list){
        List results = new ArrayList();
        Pattern pattern = Pattern.compile(name);
        for(int i=0; i < list.size(); i++){
            Matcher matcher = pattern.matcher(((AddressEntity)list.get(i)).getAddress());
            if(matcher.find()){
                results.add(list.get(i));
            }
        }
        return results;
    }

    /**
     * 精确查询  注意非空判断 否则空指针
     * @param name
     * @param list
     * @return
     */
    public List search4(String name,List list){
        List results = new ArrayList();
        Pattern pattern = Pattern.compile(name);
//        Pattern pattern = Pattern.compile(name,Pattern.CASE_INSENSITIVE);//不区分大小写
        for(int i=0; i < list.size(); i++){
            Matcher matcher = pattern.matcher(((AddressEntity)list.get(i)).getAddress());
            if(matcher.matches()){
                results.add(list.get(i));
            }
        }
        return results;
    }

    /**
     * list 转为 String 注意非空判断 否则空指针
     * @param list
     * @return
     */
    public static String listToString(List<String> list){
        if(list==null){
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for(String string :list) {
            if(first) {
                first=false;
            }else{
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }

    /**
     * @param strs
     * @return
     */
    public static List<String> stringToList(String strs){
        String str[] = strs.split(",");
        return Arrays.asList(str);
    }
}
