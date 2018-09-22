package com.example.amr.mvp_retrofit_demo.network;

import com.example.amr.mvp_retrofit_demo.utils.AppManger;

public class ApiUtils {
    public static APIService getAPIService() {
        return RetrofitClient.getClient(AppManger.BaseURL).create(APIService.class);
    }
}