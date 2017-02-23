package com.uksw.fraktal.www.usosclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.uksw.fraktal.www.usosclient.rest.adapters.TimeTableAdapter;
import com.uksw.fraktal.www.usosclient.rest.models.TimeTable;
import com.uksw.fraktal.www.usosclient.rest.services.UsosService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanActivity extends AppCompatActivity {

    //not working yet
    private UsosService usosService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        usosService = retrofit.create(UsosService.class);

        String consumerKey = getResources().getString(R.string.consumer_key);
        String nonce = TimeTableAdapter.GenerateNonce();
        String signatureMethod = null;
        String timeStamp = TimeTableAdapter.GenerateTimeStamp();
        String token = null;
        String version = getResources().getString(R.string.oauth_version);
        /*
            You have to specify date using below format. Usos api returns array with timetables for specified date and 6 next days
            (unfortunately it's maximum).
            So, in order to fetch for example 2 months of timetable You're forced to do it view times.
         */
        String startDate = "2017-02-27";
        String signature = null;



        Call<TimeTable> timeTableCall = usosService.getTimeTable(consumerKey, nonce, signatureMethod, timeStamp,
                token, version, startDate, signature);

        timeTableCall.enqueue(new Callback<TimeTable>() {
            @Override
            public void onResponse(Call<TimeTable> call, Response<TimeTable> response) {
                int statusCode = response.code();
                TimeTable timeTable = response.body();

                Log.d("PlanActivity: ", "statusCode: " + statusCode);

                //Example of implementation fetched data:
                /*
                    String nameOfLecture = timeTable.getName().getPl();


                 */
            }

            @Override
            public void onFailure(Call<TimeTable> call, Throwable t) {
                //Toast msg or something like that
                Log.d("PlanActivity: ", "cannot retrieve data");
            }
        });


    }
}



























