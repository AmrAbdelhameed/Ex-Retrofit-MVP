package com.example.amr.mvp_retrofit_demo.Network;

public class ApiUtils {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}