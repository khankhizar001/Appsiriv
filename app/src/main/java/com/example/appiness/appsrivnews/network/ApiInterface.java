package com.example.appiness.appsrivnews.network;

import com.example.appiness.appsrivnews.pojo.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by appiness on 11/5/18.
 */

public interface ApiInterface {
    @GET("get_headlines.php?")
    Call<List> getList(@Query("include") String include, @Query("limit") String limit, @Query("sortby") String sortby,
                                 @Query("order") String order, @Query("format") String format);
}
