package cn.lemene.BookTrace.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import cn.lemene.BookTrace.R;
import cn.lemene.BookTrace.interfaces.GradeAddService;
import cn.lemene.BookTrace.module.GradeAddResponse;
import cn.lemene.BookTrace.module.UserContainer;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RateActivity extends Activity implements RatingBar.OnRatingBarChangeListener {
    private RatingBar mSmallRatingBar;
    private RatingBar mIndicatorRatingBar;
    private TextView mRatingText;

    private String id;
    private float rate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        SimpleDraweeView book_cover = (SimpleDraweeView) findViewById(R.id.cover);

        Intent intent1 = getIntent();
        String URI = intent1.getStringExtra("bookURI");
        book_cover.setImageURI(URI);

        Intent intent = getIntent();
        id = intent.getStringExtra("bookId");

        mRatingText = (TextView) findViewById(R.id.rating);
        mRatingText.setText("评分：" + 0 + "/" + 5);

        // We copy the most recently changed rating on to these indicator-only
        // rating bars
        mIndicatorRatingBar = (RatingBar) findViewById(R.id.indicator_ratingbar);
        mSmallRatingBar = (RatingBar) findViewById(R.id.small_ratingbar);

        // The different rating bars in the layout. Assign the listener to us.
        ((RatingBar)findViewById(R.id.ratingbar)).setOnRatingBarChangeListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        final int numStars = ratingBar.getNumStars();
        mRatingText.setText("评分：" + rating + "/" + numStars);

        if (mIndicatorRatingBar.getRating() != rating) {
            mIndicatorRatingBar.setRating(rating);
            mSmallRatingBar.setRating(rating);
        }
        final float ratingBarStepSize = ratingBar.getStepSize();
        if (mIndicatorRatingBar.getStepSize() != ratingBarStepSize) {
            mIndicatorRatingBar.setStepSize(ratingBarStepSize);
            mSmallRatingBar.setStepSize(ratingBarStepSize);
        }
        rate = rating;

    }

    public void confirm(View view)
    {
        if (UserContainer.isLogFlag == true) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(UserContainer.BASE_IP_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            GradeAddService.gradeAddService graSer = retrofit.create(GradeAddService.gradeAddService.class);

            Gson gson = new Gson();
            HashMap<String, String> paramsMap = new HashMap<>();
            paramsMap.put("userid", UserContainer.userID);
            paramsMap.put("id", id);
            paramsMap.put("grade", Float.toString(rate));
            String strEntity = gson.toJson(paramsMap);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), strEntity);
            Call<GradeAddResponse> call = graSer.getGradeAddResult(body);
            call.enqueue(new Callback<GradeAddResponse>() {

                @Override
                public void onResponse(Call<GradeAddResponse> call, Response<GradeAddResponse> response) {
                    Logger.d("Rating done");
                }

                @Override
                public void onFailure(Call<GradeAddResponse> call, Throwable t) {

                }
            });
            Toast.makeText(getBaseContext(), "评论成功", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(getBaseContext(), "尚未登录，不能评分", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public void cancel(View view)
    {
        finish();
    }

}

