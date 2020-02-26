package com.apps.kunalfarmah.marsplay;


import retrofit2.Call;
import retrofit2.http.GET;


public interface RequestInterface {
        @GET("search?q=title:DNA")
        Call<Farmah> getJSON();
        //@GET("/search")
        //Call<Response> search(@Query("title") String val);
}
