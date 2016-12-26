package cn.lemene.BookTrace.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import cn.lemene.BookTrace.R;

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
        finish();
    }

    public void cancel(View view)
    {
        finish();
    }

}

