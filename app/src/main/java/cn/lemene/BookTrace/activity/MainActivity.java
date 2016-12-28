package cn.lemene.BookTrace.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lemene.BookTrace.R;
import cn.lemene.BookTrace.module.UserContainer;
import cn.lemene.BookTrace.view.DBBookSearchView;
import cn.lemene.BookTrace.view.MainNavigationView;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    protected DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    protected MainNavigationView mNavigationView;

    @BindView(R.id.search_view)
    protected DBBookSearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserContainer.username = "DefaultUser";
        UserContainer.userID = "99999999";
        UserContainer.wantReadList = new ArrayList<>() ;
        UserContainer.wantReadList.add("please login.");
        UserContainer.readingList = new ArrayList<>() ;
        UserContainer.readingList.add("please login.");
        UserContainer.hasReadList = new ArrayList<>() ;
        UserContainer.hasReadList.add("please login.");
        init();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        mSearchView.setSearchView(searchView);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void init() {
        Logger.init(getClass().getSimpleName());
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setDrawer(mDrawer);
    }


    private void selectDrawerItem(MenuItem menuItem) {


        switch (menuItem.getItemId()) {
            case R.id.nav_camera:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_gallery:
                if(!UserContainer.username.equals("DefaultUser"))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("确定注销登录?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which)
                        {
                            UserContainer.username = "DefaultUser";
                            UserContainer.userID = "99999999";
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                    builder.show();
                }
                else {
                    //else start the login activity
                    Intent intent1 = new Intent(this, SignInActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.nav_slideshow:
                Intent intent2 = new Intent(this, AboutUsActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_user:
                Intent intent3 = new Intent(this, UserDetailActivity.class);
                startActivity(intent3);
                break;

            default:

                break;
        }


    }
}
