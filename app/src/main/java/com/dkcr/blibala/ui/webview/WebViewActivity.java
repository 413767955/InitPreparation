package com.dkcr.blibala.ui.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ActivityWebViewBinding;
import com.dkcr.blibala.ui.home.viewmodel.HomeViewModel;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.zlx.module_base.base_ac.BaseMvvmAc;

public class WebViewActivity extends BaseMvvmAc<ActivityWebViewBinding, HomeViewModel> {

    public static void toActivity(Context context,String webUrl){
        Intent intent = new Intent(context,WebViewActivity.class);
        intent.putExtra("webUrl",webUrl);
        context.startActivity(intent);
    }

    public static void toActivity(Context context,String webUrl,String title){
        Intent intent = new Intent(context,WebViewActivity.class);
        intent.putExtra("webUrl",webUrl);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    private String webUrl;
    private String title;

    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_web_view;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initParam() {
        webUrl = getIntent().getStringExtra("webUrl");
        title = getIntent().getStringExtra("title");
    }

    @Override
    public void initViews() {
        if (!TextUtils.isEmpty(title)) {
            //setAcTitle(title);
            binding.titleBar.setTitle(title);
        }
        AgentWeb.with(this)
                .setAgentWebParent(binding.container, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(com.just.agentweb.R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                //.setWebLayout(new WebLayout(this))
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(getUrl());
    }

    @Override
    public void initEvents() {
        super.initEvents();
        binding.titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                onBackPressed();
            }
        });
    }


    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setAcTitle(title);
        }
    };
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
            Log.i("Info", "BaseWebActivity onPageStarted");
        }
    };
    private String getUrl() {
        return webUrl;
    }
}