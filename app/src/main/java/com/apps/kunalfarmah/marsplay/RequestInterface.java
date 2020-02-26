package com.apps.kunalfarmah.marsplay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RequestInterface {
        @GET("search?q=title:DNA")
        Call<Response> getJSON();
        //@GET("/search")
        //Call<Response> search(@Query("title") String val);
}
