package net.sotree.util;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

/**
 * Created by lims on 2016. 8. 23..
 * TextUtil 클래스
 */
public class TextUtil {

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        Spanned htmlText;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            htmlText = Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            htmlText = Html.fromHtml(source);
        }

        return htmlText;
    }
}
