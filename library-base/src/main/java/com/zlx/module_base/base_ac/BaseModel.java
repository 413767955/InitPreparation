package com.zlx.module_base.base_ac;

import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BaseModel implements IModel{
    @Override
    public void onCleared() {

    }

    public RequestBody requestBody(HashMap<String, String> hashMap){
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(hashMap));
    }
}
