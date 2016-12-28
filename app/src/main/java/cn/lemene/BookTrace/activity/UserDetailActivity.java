package cn.lemene.BookTrace.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import java.util.ArrayList;

import cn.lemene.BookTrace.R;
import cn.lemene.BookTrace.fragment.UserDetailFragment;
import cn.lemene.BookTrace.interfaces.UserInformationService;
import cn.lemene.BookTrace.module.Books;
import cn.lemene.BookTrace.module.UserContainer;
import cn.lemene.BookTrace.module.UserInformationResponse;
import okhttp3.OkHttpClient;
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
public class UserDetailActivity extends SingleFragmentActivity {

    public static final String USER_KEY = "activity_user_detail";
    //private MyApplication mApp ;
    //private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //mApp = (MyApplication) getApplication();
        //user = mApp.getUser();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);


    }

    @Override
    public Fragment createFragment() {
        return UserDetailFragment.newInstance();
    }


    @Override
    public int getFragmentContainer() {
        return R.id.user_detail_fragment_container;
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

    /*private User getUser() {
        return mUser;
    }*/
}
