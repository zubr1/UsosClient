package com.uksw.fraktal.www.usosclient.rest.services;

import com.uksw.fraktal.www.usosclient.rest.models.TimeTable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zubr on 19.02.2017.
 */
//https://apps.usos.uksw.edu.pl/services/tt/student?oauth_consumer_key=26DYUWpXK4qh7sChfdLs&oauth_nonce=8679800&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1487535607&oauth_token=8EqcXaQABK9DnQc5vY9W&oauth_version=1.0&start=2017-02-20&oauth_signature=R7P4M4%2b7DFatYh8om8HGXHdhxMo%3d

public interface UsosService {
    @GET("/services/tt/student")
    Call<TimeTable> getTimeTable(@Query("oauth_consumer_key") String consumerKey, @Query("oauth_nonce") String nonce,
                                 @Query("oauth_signature_method") String signatureMethod, @Query("oauth_timestamp") String timeStamp,
                                 @Query("oauth_token") String token, @Query("oauth_version") String version,
                                 @Query("start") String startDate, @Query("oauth_signature") String signature);

    //"https://apps.usos.uksw.edu.pl/services/oauth/request_token?oauth_callback=oob&oauth_consumer_key=26DYUWpXK4qh7sChfdLs&oauth_nonce=4165163&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1487886436&oauth_version=1.0&scopes=studies&oauth_signature=TiXpn8bTKybMET4PdCPOsV6XiY4%3d"
    //@GET("/services/oauth/request_token")
}
