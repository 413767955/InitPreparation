package com.dkcr.blibala.ui.recommend;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.FragmentRecommendBinding;
import com.dkcr.blibala.ui.home.fragment.HomeFragment;
import com.dkcr.blibala.ui.home.viewmodel.HomeViewModel;
import com.dkcr.blibala.ui.recommend.adapter.FriendCircleAdapter;
import com.dkcr.blibala.ui.recommend.bean.DataCenter;
import com.dkcr.blibala.ui.recommend.bean.FriendCircleBean;
import com.dkcr.blibala.ui.recommend.others.FriendsCircleAdapterDivideLine;
import com.dkcr.blibala.ui.recommend.others.GlideSimpleTarget;
import com.dkcr.blibala.util.Utils;
import com.gyf.immersionbar.ImmersionBar;
import com.zlx.module_base.base_fg.BaseMvvmFg;

import java.util.List;

import ch.ielse.view.imagewatcher.ImageWatcher;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 推荐
 */
public class RecommendFragment extends BaseMvvmFg<FragmentRecommendBinding, HomeViewModel>
        implements SwipeRefreshLayout.OnRefreshListener, ImageWatcher.OnPictureLongPressListener,
        ImageWatcher.Loader {

    public static RecommendFragment newInstance() {
        RecommendFragment fragment = new RecommendFragment();
        return fragment;
    }

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Disposable mDisposable;
    private FriendCircleAdapter mFriendCircleAdapter;
    private ImageWatcher mImageWatcher;

    @Override
    protected int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_recommend;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .titleBar(binding.toolbar,true)
                .statusBarDarkFont(true)
                .keyboardEnable(true)
                .init();
    }

    @Override
    protected void initViews() {
        super.initViews();
        mSwipeRefreshLayout = binding.swpieRefreshLayout;

        mSwipeRefreshLayout.setOnRefreshListener(this);

//        findViewById(R.id.img_back).setOnClickListener(v ->
//                startActivity(new Intent(MainActivity.this, EmojiPanelActivity.class)));

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(getContext()).resumeRequests();
                } else {
                    Glide.with(getContext()).pauseRequests();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.addItemDecoration(new FriendsCircleAdapterDivideLine());
        mFriendCircleAdapter = new FriendCircleAdapter(getContext(), binding.recyclerView, mImageWatcher);
        binding.recyclerView.setAdapter(mFriendCircleAdapter);
//        mImageWatcher.setTranslucentStatus(Utils.calcStatusBarHeight(getContext()));
//        mImageWatcher.setErrorImageRes(ch.ielse.view.imagewatcher.R.mipmap.error_picture);
//        mImageWatcher.setOnPictureLongPressListener(this);
//        mImageWatcher.setLoader(this);
        Utils.showSwipeRefreshLayout(mSwipeRefreshLayout, this::asyncMakeData);

    }

    private void asyncMakeData() {
        mDisposable = Single.create((SingleOnSubscribe<List<FriendCircleBean>>) emitter ->
                        emitter.onSuccess(DataCenter.makeFriendCircleBeans(getContext())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((friendCircleBeans, throwable) -> {
                    Utils.hideSwipeRefreshLayout(mSwipeRefreshLayout);
                    if (friendCircleBeans != null && throwable == null) {
                        mFriendCircleAdapter.setFriendCircleBeans(friendCircleBeans);
                    }
                });
    }
    @Override
    public void onRefresh() {
        asyncMakeData();
    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {

    }

    @Override
    public void load(Context context, String url, ImageWatcher.LoadCallback lc) {
        Glide.with(context).asBitmap().load(url).into(new GlideSimpleTarget(lc));
    }
}