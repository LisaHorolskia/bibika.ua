package my.car.bibikaua;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import java.net.URISyntaxException;
import my.car.bibikaua.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    String url = "https://bibika.glide.page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viev = binding.getRoot();
        setContentView(viev);

        binding.weebViev.loadUrl(url);

        WebSettings webSettings = binding.weebViev.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);

        binding.weebViev.setWebViewClient(new WebViewClient(){

            String currentUrl;

            @Override
            public boolean shouldOverrideUrlLoading(WebView viev, String url){
                currentUrl=url;
                if (url.startsWith("http") || url.startsWith("https")) {
                    return false;
                }
                if (url.startsWith("intent")){
                    try {
                        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);

                        String fallbackUrl = intent.getStringExtra("browser_fallback_url");
                        if (fallbackUrl != null){
                            binding.weebViev.loadUrl(fallbackUrl);
                            return true;
                        }}
                    catch (URISyntaxException e){
                        //not an itntent url
                    }
                }
                return true;
            }

        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        binding.weebViev.goBack();
    }
}