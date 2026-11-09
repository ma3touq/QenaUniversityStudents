
package eg.qu.edu.studentportal;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.ConsoleMessage;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.parseColor("#145A43"));
        getWindow().setNavigationBarColor(Color.BLACK);

        FrameLayout root = new FrameLayout(this);

        webView = new WebView(this);
        webView.setBackgroundColor(Color.WHITE);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage message) {
                android.util.Log.d(
                    "WEBVIEW_TEST",
                    message.message() + " | Line: " + message.lineNumber()
                );
                return true;
            }
        });

        root.addView(webView, new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        ));

        setContentView(root);

        String html =
            "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1'>" +
            "<meta charset='UTF-8'>" +
            "<style>" +
            "body { background:#ffffff; color:#145A43; text-align:center;" +
            "font-family:sans-serif; padding:30px 10px; }" +
            ".box { background:#e8f5ee; padding:25px; border-radius:15px;" +
            "margin:20px auto; max-width:400px; }" +
            "button { background:#145A43; color:white; border:0;" +
            "padding:15px 25px; border-radius:10px; font-size:18px; }" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<h2>اختبار تطبيق طلاب جامعة قنا</h2>" +
            "<div class='box'>" +
            "<h3>WebView يعمل</h3>" +
            "<p>إذا كنت ترى هذه الرسالة فالمحتوى ظهر بنجاح.</p>" +
            "<p id='result'>اختبار JavaScript لم يتم بعد</p>" +
            "<button onclick=\"document.getElementById('result').innerHTML=" +
            "'JavaScript يعمل بنجاح'; this.innerHTML='تم الاختبار بنجاح';\">" +
            "اضغط للاختبار</button>" +
            "</div>" +
            "</body>" +
            "</html>";

        webView.loadDataWithBaseURL(
            "https://test.local/",
            html,
            "text/html",
            "UTF-8",
            null
        );
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
