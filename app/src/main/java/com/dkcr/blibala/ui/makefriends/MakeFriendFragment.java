package com.dkcr.blibala.ui.makefriends;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.FragmentMakeFriendBinding;
import com.dkcr.blibala.ui.makefriends.ac.FriendDetailsActivity;
import com.dkcr.blibala.ui.makefriends.adapter.CardAdapter;
import com.dkcr.blibala.ui.makefriends.bean.CardBean;
import com.lin.cardlib.CardLayoutManager;
import com.lin.cardlib.CardSetting;
import com.lin.cardlib.CardTouchHelperCallback;
import com.lin.cardlib.OnSwipeCardListener;
import com.lin.cardlib.utils.ReItemTouchHelper;
import com.zlx.module_base.base_fg.BaseMvvmFg;
import com.zlx.module_base.event.EventHandlers;
import com.zlx.module_base.viewmodel.BaseViewModel;

import java.util.List;

/**
 * 交友
 */
public class MakeFriendFragment extends BaseMvvmFg<FragmentMakeFriendBinding, BaseViewModel> implements View.OnClickListener{

    ReItemTouchHelper mReItemTouchHelper;

    public static MakeFriendFragment newInstance() {
        MakeFriendFragment fragment = new MakeFriendFragment();
        return fragment;
    }

    @Override
    protected int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_make_friend;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initViews() {
        super.initViews();
        binding.setEvent(new Event());
        initCard();
    }
    
    private void initCard(){
        List<CardBean> list = CardMaker.initCards();
        CardSetting setting=new CardSetting();
        setting.setSwipeListener(new OnSwipeCardListener<CardBean>() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float dx, float dy, int direction) {
                switch (direction) {
                    case ReItemTouchHelper.DOWN:
                        Log.e("aaa", "swiping direction=down");
                        break;
                    case ReItemTouchHelper.UP:
                        Log.e("aaa", "swiping direction=up");
                        break;
                    case ReItemTouchHelper.LEFT:
                        Log.e("aaa", "swiping direction=left");
                        break;
                    case ReItemTouchHelper.RIGHT:
                        Log.e("aaa", "swiping direction=right");
                        break;
                }
            }

            @Override
            public void onSwipedOut(RecyclerView.ViewHolder viewHolder, CardBean o, int direction) {
                switch (direction) {
                    case ReItemTouchHelper.DOWN:
                        Toast.makeText(getContext(), "swipe down out", Toast.LENGTH_SHORT).show();
                        break;
                    case ReItemTouchHelper.UP:
                        Toast.makeText(getContext(), "swipe up out ", Toast.LENGTH_SHORT).show();
                        break;
                    case ReItemTouchHelper.LEFT:
                        Toast.makeText(getContext(), "swipe left out", Toast.LENGTH_SHORT).show();
                        break;
                    case ReItemTouchHelper.RIGHT:
                        Toast.makeText(getContext(), "swipe right out", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(getContext(), "cards are consumed", Toast.LENGTH_SHORT).show();
            }
        });
        CardTouchHelperCallback helperCallback = new CardTouchHelperCallback(binding.recyclerview, list,setting);
        mReItemTouchHelper = new ReItemTouchHelper(helperCallback);
        CardLayoutManager layoutManager = new CardLayoutManager(mReItemTouchHelper, setting);
        binding.recyclerview.setLayoutManager(layoutManager);
        CardAdapter cardAdapter = new CardAdapter(list);
        binding.recyclerview.setAdapter(cardAdapter);

        binding.turnLeft.setOnClickListener(this);
        binding.turnRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.turn_left:
                mReItemTouchHelper.swipeManually(ReItemTouchHelper.LEFT);
                break;
            case R.id.turn_right:
                mReItemTouchHelper.swipeManually(ReItemTouchHelper.RIGHT);
                break;
        }
    }

    public class Event extends EventHandlers {
        public void onDetailsClick(){
            FriendDetailsActivity.toActivity(getContext());
        }
    }
}