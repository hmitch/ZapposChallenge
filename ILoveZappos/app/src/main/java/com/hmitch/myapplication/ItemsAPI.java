package com.hmitch.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.hmitch.myapplication.model.QueryItem;

public interface ItemsAPI {

    @GET("Search?term=&key=b743e26728e16b81da139182bb2094357c31d331")
    Call<QueryItem> getFeed(@Query("term") String term);

}
