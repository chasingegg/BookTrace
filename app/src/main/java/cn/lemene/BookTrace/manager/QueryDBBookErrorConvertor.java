package cn.lemene.BookTrace.manager;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.lemene.BookTrace.module.QueryDBBookErrorRespone;



public class QueryDBBookErrorConvertor {
    public static QueryDBBookErrorRespone fromJson(String json) {
        if (!TextUtils.isEmpty(json)) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(json, QueryDBBookErrorRespone.class);
        } else {
            return null;
        }
    }
}
