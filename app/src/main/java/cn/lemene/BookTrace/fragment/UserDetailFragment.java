package cn.lemene.BookTrace.fragment;

/**
 * Created by xu on 2016/12/25.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lemene.BookTrace.R;
import cn.lemene.BookTrace.adapter.BookSummaryAdapter;
import cn.lemene.BookTrace.module.Summary;
import cn.lemene.BookTrace.module.User;

/**
 * 用户详情页面
 * @author snail 2016/10/25 9:38
 * @version v1.0
 */

public class UserDetailFragment extends Fragment {
    private User mUser ;

    @BindView(R.id.user_detail_collapsing_toolbar)
    protected CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.user_detail_view_pager)
    protected ViewPager mViewPager;

    @BindView(R.id.user_detail_tab_layout)
    protected TabLayout mTabLayout;

    @BindView(R.id.user_detail_toolbar)
    protected Toolbar mToolbar;

    @BindView(R.id.user_detail_cover)
    protected SimpleDraweeView mUserImg;

    private static final String USER_KEY = "activity_user_detail";

    public static UserDetailFragment newInstance() {
        User user = new User();
        Bundle args = new Bundle();
        args.putSerializable(USER_KEY,user);
        UserDetailFragment fragment = new UserDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);
        initView(view);
        return view;
    }

    private void init() {
        Logger.init(getClass().getSimpleName());
        initArgs();
    }

    private void initArgs() {
        Bundle args = getArguments();
        if (args != null) {
            mUser = (User) args.getSerializable(USER_KEY);
        }
        checkBook();
    }

    private void initView(View view) {
        ButterKnife.bind(this, view);

        initToolbar();
        initViewPager();
        initTabLayout();

        mCollapsingToolbar.setTitle(mUser.getId());
        mUserImg.setImageURI("aaaaa");
    }

    private void initViewPager() {
        List<Summary> summaries = new ArrayList<>();
        summaries.add(new Summary(getString(R.string.book_want), mUser.getBookWantString()));
        summaries.add(new Summary(getString(R.string.book_reading), mUser.getBookReadingString()));
        summaries.add(new Summary(getString(R.string.book_read), mUser.getBookReadingString()));
        mViewPager.setAdapter(new BookSummaryAdapter(getChildFragmentManager(), summaries));
    }

    @SuppressWarnings("ConstantConditions")
    private void initToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void checkBook() {
        if (mUser == null) {
            Logger.e("mUser is null, check book failed");
            getActivity().finish();
        }
    }

    /*private String getBookDetail(DBBook book) {
        if (book == null) {
            return null;
        }

        DBBook.Rating rating = book.getRating();
        String ratingString = String.format(getString(R.string.book_rating), rating.getAverage(), rating.getNumRaters());
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(getString(R.string.book_author), book.getAuthors()) + "\n");
        builder.append(String.format(getString(R.string.book_origin_title), book.getOriginTitle()) + "\n");
        builder.append(String.format(getString(R.string.book_sub_title), book.getSubtile()) + "\n");
        builder.append(String.format(getString(R.string.book_pub_date), book.getPUbDate()) + "\n");
        builder.append(String.format(getString(R.string.book_publisher), book.getPUblisher()) + "\n");
        builder.append(String.format(getString(R.string.book_page), book.getPages()) + "\n");
        builder.append(String.format(getString(R.string.book_binding), book.getBinding()) + "\n");
        builder.append(String.format(getString(R.string.book_isbn13), book.getIsbn13()) + "\n");
        builder.append(String.format(getString(R.string.book_price), book.getPrice()) + "\n");
        builder.append(String.format(getString(R.string.book_tag), book.getTagString()) + "\n");
        builder.append(ratingString + "\n");
        builder.append(String.format(getString(R.string.book_web_link), book.getWebLink()) + "\n");
        return builder.toString();
    }

    private String getBookComments(DBBook book) {
        if (book == null) {
            return null;
        }
        return book.getCommentString();
    }*/
}

