package cn.lemene.BookTrace.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lemene.BookTrace.R;


public class SummaryFragment extends Fragment {
    private String mSummary;

    @BindView(R.id.summary_fragment_text_view)
    protected TextView mTextView;

    private static final String KEY_SUMMARY = "summary_fragment_summary";

    public static SummaryFragment newInstance(String summary) {
        Bundle args = new Bundle();
        args.putString(KEY_SUMMARY, summary);
        SummaryFragment fragment = new SummaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_summary, container, false);
        initView(view);
        return view;
    }

    private void init() {
        Logger.init(getClass().getSimpleName());

        Bundle args = getArguments();
        if (args != null) {
            mSummary = args.getString(KEY_SUMMARY);
        }
    }

    private void initView(View view) {
        ButterKnife.bind(this, view);
        mTextView.setText(mSummary);
    }
}
