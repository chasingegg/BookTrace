package cn.lemene.BookTrace.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import cn.lemene.BookTrace.R;
import cn.lemene.BookTrace.fragment.DBBookDetailFragment;
import cn.lemene.BookTrace.interfaces.MarkService;
import cn.lemene.BookTrace.module.CommonResponse;
import cn.lemene.BookTrace.module.DBBook;
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

/**
 * 豆瓣图书详情页面
 * @author snail 2016/10/24 14:22
 * @version v1.0
 */
public class DBBookDetailActivity extends SingleFragmentActivity {

    public static final String KEY_BOOK = "activity_db_book_detail_key_book";
    private String markStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
    }

    @Override
    public Fragment createFragment() {
        return DBBookDetailFragment.newInstance(getExtraBook());
    }

    @Override
    public int getFragmentContainer() {
        return R.id.book_detail_fragment_container;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private DBBook getExtraBook() {
        Intent intent = getIntent();
        return (DBBook) intent.getSerializableExtra(KEY_BOOK);
    }

    public void Mark(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(DBBookDetailActivity.this);
        builder.setTitle("标记书籍状态");
        final String[] str = new String[] { "正在读", "准备读", "已经读" };
        builder.setSingleChoiceItems(str, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(DBBookDetailActivity.this, str[i],
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                markStatus=str[i];

                DBBook tmp = getExtraBook();
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

                    MarkService.markService markSer = retrofit.create(MarkService.markService.class);

                    Gson gson = new Gson();
                    HashMap<String, String> paramsMap = new HashMap<>();
                    paramsMap.put("id", tmp.getId());
                    paramsMap.put("userid", UserContainer.userID);
                    paramsMap.put("status", markStatus);
                    paramsMap.put("bookname", tmp.getTitle());
                    String strEntity = gson.toJson(paramsMap);
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), strEntity);
                    Call<CommonResponse> call = markSer.getMarkResult(body);
                    call.enqueue(new Callback<CommonResponse>() {
                        @Override
                        public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

                        }

                        @Override
                        public void onFailure(Call<CommonResponse> call, Throwable t) {
                            String msg = t.getLocalizedMessage();
                            Logger.e(t, "query mark error " + msg);
                        }
                    });
                    Toast.makeText(getBaseContext(), "标记成功", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getBaseContext(), "尚未登录，无法标记", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }

    public void Comment(View view)
    {
        DBBook tmp = getExtraBook();

        String id = tmp.getId();

        Intent intent = new Intent(DBBookDetailActivity.this, CommentActivity.class);
        intent.putExtra("bookId",id);
        startActivity(intent);
    }

    public void Rate(View view)
    {
        DBBook tmp = getExtraBook();

        String URI = tmp.getCoveres().getLarge();
        String id = tmp.getId();

        Intent intent = new Intent(DBBookDetailActivity.this, RateActivity.class);

        intent.putExtra("bookURI",URI);
        intent.putExtra("bookId",id);

        startActivity(intent);
    }
}
