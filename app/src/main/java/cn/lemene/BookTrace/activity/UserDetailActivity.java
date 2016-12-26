package cn.lemene.BookTrace.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.lemene.BookTrace.R;
import cn.lemene.BookTrace.fragment.UserDetailFragment;

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
        System.out.println("come here");
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

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }*/

    /*private User getUser() {
        return mUser;
    }*/
}
