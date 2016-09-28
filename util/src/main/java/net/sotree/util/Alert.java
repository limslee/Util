package net.sotree.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by lims on 2016. 4. 6..
 * Last updated on 2016. 9. 5
 * 각종 알림 유틸
 */
public class Alert {

    public static void bar(Activity activity, String message) {
        bar(activity, message, Snackbar.LENGTH_LONG, null);
    }

    public static void bar(Activity activity, int resId) {
        bar(activity, resId, Snackbar.LENGTH_LONG, null);
    }

    public static void bar(Activity activity, String message, int length) {
        bar(activity, message, length, null);
    }

    public static void bar(Activity activity, int resId, int length) {
        bar(activity, resId, length, null);
    }

    public static void bar(Activity activity, String message, View.OnClickListener listener) {
        bar(activity, message, Snackbar.LENGTH_LONG, listener);
    }

    public static void bar(Activity activity, int resId, View.OnClickListener listener) {
        bar(activity, resId, Snackbar.LENGTH_LONG, listener);
    }

    public static void bar(Activity activity, String message, int length, View.OnClickListener listener) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            if (listener == null) {
                Snackbar.make(view, message, length).show();
            } else {
                Snackbar.make(view, message, length).setAction(android.R.string.ok, listener).show();
            }
        }
    }

    public static void bar(Activity activity, int resId, int length, View.OnClickListener listener) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            if (listener == null) {
                Snackbar.make(view, resId, length).show();
            } else {
                Snackbar.make(view, resId, length).setAction(android.R.string.ok, listener).show();
            }
        }
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, String message, boolean lengthLong) {
        if (lengthLong) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void toast(Context context, int resId, boolean lengthLong) {
        if (lengthLong) {
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
        }
    }

    public static void alert(Context context, int resId) {
        alert(context, resId, null);
    }

    public static void alert(Context context, String message) {
        alert(context, message, null);
    }

    public static void alert(Context context, int resId, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(resId);

        builder.setPositiveButton(android.R.string.ok, listener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void alert(Context context, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(message);

        builder.setPositiveButton(android.R.string.ok, listener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void confirm(Context context, int resId, DialogInterface.OnClickListener listenerOK) {
        confirm(context, resId, listenerOK, null);
    }

    public static void confirm(Context context, String message, DialogInterface.OnClickListener listenerOK) {
        confirm(context, message, listenerOK, null);
    }

    public static void confirm(Context context, int resId, DialogInterface.OnClickListener listenerOK, DialogInterface.OnClickListener listenerCancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(resId);

        builder.setPositiveButton(android.R.string.ok, listenerOK);
        builder.setNegativeButton(android.R.string.cancel, listenerCancel);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void confirm(Context context, String message, DialogInterface.OnClickListener listenerOK, DialogInterface.OnClickListener listenerCancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(message);

        builder.setPositiveButton(android.R.string.ok, listenerOK);
        builder.setNegativeButton(android.R.string.cancel, listenerCancel);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
