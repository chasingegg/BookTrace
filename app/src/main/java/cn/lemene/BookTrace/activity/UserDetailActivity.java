package cn.lemene.BookTrace.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import java.util.ArrayList;

import cn.lemene.BookTrace.R;
import cn.lemene.BookTrace.fragment.UserDetailFragment;


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
