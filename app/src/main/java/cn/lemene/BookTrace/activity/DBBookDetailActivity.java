package cn.lemene.BookTrace.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cn.lemene.BookTrace.R;
import cn.lemene.BookTrace.fragment.DBBookDetailFragment;
import cn.lemene.BookTrace.module.DBBook;

/**
 * 豆瓣图书详情页面
 * @author snail 2016/10/24 14:22
 * @version v1.0
 */
public class DBBookDetailActivity extends SingleFragmentActivity {

    public static final String KEY_BOOK = "activity_db_book_detail_key_book";

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
        //Intent intent = new Intent(DBBookDetailActivity.this, MarkActivity.class);
        //startActivity(intent);
        AlertDialog.Builder builder = new AlertDialog.Builder(DBBookDetailActivity.this);
        builder.setTitle("标记书籍状态");
        final String[] str = new String[] { "正在读", "准备读", "已经读" };
        builder.setSingleChoiceItems(str, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(DBBookDetailActivity.this, str[i],
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void Comment(View view)
    {
        Intent intent = new Intent(DBBookDetailActivity.this, CommentActivity.class);
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
