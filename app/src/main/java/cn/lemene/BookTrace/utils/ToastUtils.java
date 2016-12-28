package cn.lemene.BookTrace.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;



public class ToastUtils {
    private static Toast sToast;

    public static void showToast(Context context, String text) {
        if (sToast == null) {
            sToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            sToast.setDuration(Toast.LENGTH_SHORT);
            sToast.setText(text);
        }

        sToast.show();
    }

    public static void showToast(Context context, int resid) {
        if (sToast == null) {
            sToast = Toast.makeText(context, resid, Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            sToast.setDuration(Toast.LENGTH_SHORT);
            sToast.setText(resid);
        }

        sToast.show();
    }
}
