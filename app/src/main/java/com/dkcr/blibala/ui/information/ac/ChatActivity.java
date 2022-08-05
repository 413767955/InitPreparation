package com.dkcr.blibala.ui.information.ac;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.dkcr.blibala.BR;
import com.dkcr.blibala.R;
import com.dkcr.blibala.databinding.ActivityChatBinding;
import com.dkcr.blibala.ui.home.viewmodel.HomeViewModel;
import com.dkcr.blibala.ui.information.adapter.ChatAdapter;
import com.dkcr.blibala.ui.information.bean.AudioMsgBody;
import com.dkcr.blibala.ui.information.bean.FileMsgBody;
import com.dkcr.blibala.ui.information.bean.ImageMsgBody;
import com.dkcr.blibala.ui.information.bean.Message;
import com.dkcr.blibala.ui.information.bean.MsgSendStatus;
import com.dkcr.blibala.ui.information.bean.MsgType;
import com.dkcr.blibala.ui.information.bean.TextMsgBody;
import com.dkcr.blibala.ui.information.bean.VideoMsgBody;
import com.dkcr.blibala.util.ChatUiHelper;
import com.dkcr.blibala.util.GlideEngine;
import com.dkcr.blibala.widget.chat.MediaManager;
import com.dkcr.blibala.widget.chat.RecordButton;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.config.SelectModeConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.zlx.module_base.base_ac.BaseMvvmAc;
import com.zlx.module_base.event.EventHandlers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Description:
 * Author: lyl
 * Date: 2022/7/24 22:03
 */
public class ChatActivity extends BaseMvvmAc<ActivityChatBinding, HomeViewModel> {

    public static void toActivity(Context context){
        Intent intent = new Intent(context,ChatActivity.class);
        context.startActivity(intent);
    }
    public static final String 	  mSenderId="right";
    public static final String     mTargetId="left";
    public static final int       REQUEST_CODE_IMAGE=0000;
    public static final int       REQUEST_CODE_VEDIO=1111;
    public static final int       REQUEST_CODE_FILE=2222;

    @Override
    protected int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_chat;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViews() {
        super.initViews();
        binding.setEvent(new Event());
        initContent();

    }
    private ImageView ivAudio;
    private ChatAdapter mAdapter;
    protected void initContent() {
        mAdapter=new ChatAdapter(null);
        LinearLayoutManager mLinearLayout=new LinearLayoutManager(this);
        binding.rvChatList.setLayoutManager(mLinearLayout);
        binding.rvChatList.setAdapter(mAdapter);
        //mSwipeRefresh.setOnRefreshListener(this);
        initChatUi();
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                final  boolean isSend = mAdapter.getItem(position).getSenderId().equals(ChatActivity.mSenderId);
                if (ivAudio != null) {
                    if (isSend){
                        ivAudio.setBackgroundResource(R.mipmap.audio_animation_list_right_3);
                    }else {
                        ivAudio.setBackgroundResource(R.mipmap.audio_animation_list_left_3);
                    }
                    ivAudio = null;
                    MediaManager.reset();
                }else{
                    ivAudio = view.findViewById(R.id.ivAudio);
                    MediaManager.reset();
                    if (isSend){
                        ivAudio.setBackgroundResource(R.drawable.audio_animation_right_list);
                    }else {
                        ivAudio.setBackgroundResource(R.drawable.audio_animation_left_list);
                    }
                    AnimationDrawable drawable = (AnimationDrawable) ivAudio.getBackground();
                    drawable.start();
                    MediaManager.playSound(ChatActivity.this,((AudioMsgBody)mAdapter.getData().get(position).getBody()).getLocalPath(), new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if (isSend){
                                ivAudio.setBackgroundResource(R.mipmap.audio_animation_list_right_3);
                            }else {
                                ivAudio.setBackgroundResource(R.mipmap.audio_animation_list_left_3);
                            }

                            MediaManager.release();
                        }
                    });
                }
            }
        });

    }

    private void onRefresh() {
        //下拉刷新模拟获取历史消息
        List<Message> mReceiveMsgList=new ArrayList<Message>();
        //构建文本消息
        Message mMessgaeText=getBaseReceiveMessage(MsgType.TEXT);
        TextMsgBody mTextMsgBody=new TextMsgBody();
        mTextMsgBody.setMessage("收到的消息");
        mMessgaeText.setBody(mTextMsgBody);
        mReceiveMsgList.add(mMessgaeText);
        //构建图片消息
        Message mMessgaeImage=getBaseReceiveMessage(MsgType.IMAGE);
        ImageMsgBody mImageMsgBody=new ImageMsgBody();
        mImageMsgBody.setThumbUrl("https://c-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.thumb.700_0.jpeg");
        mMessgaeImage.setBody(mImageMsgBody);
        mReceiveMsgList.add(mMessgaeImage);
        //构建文件消息
        Message mMessgaeFile=getBaseReceiveMessage(MsgType.FILE);
        FileMsgBody mFileMsgBody=new FileMsgBody();
        mFileMsgBody.setDisplayName("收到的文件");
        mFileMsgBody.setSize(12);
        mMessgaeFile.setBody(mFileMsgBody);
        mReceiveMsgList.add(mMessgaeFile);
        mAdapter.addData(0,mReceiveMsgList);
        //mSwipeRefresh.setRefreshing(false);
    }

    private void initChatUi(){
        //mBtnAudio
        final ChatUiHelper mUiHelper= ChatUiHelper.with(this);
        mUiHelper.bindContentLayout(binding.llContent)
                .bindttToSendButton(binding.btnSend)
                .bindEditText(binding.etContent)
                .bindBottomLayout(binding.bottomLayout)
                .bindEmojiLayout(binding.homeEmoji)
                .bindAddLayout(binding.llAdd)
                .bindToAddButton(binding.ivAdd)
                .bindToEmojiButton(binding.ivEmo)
                .bindAudioBtn(binding.btnAudio)
                .bindAudioIv(binding.ivAudio)
                .bindEmojiData();
        //底部布局弹出,聊天列表上滑
        binding.rvChatList.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    binding.rvChatList.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mAdapter.getItemCount() > 0) {
                                binding.rvChatList.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                            }
                        }
                    });
                }
            }
        });
        //点击空白区域关闭键盘
        binding.rvChatList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mUiHelper.hideBottomLayout(false);
                mUiHelper.hideSoftInput();
                binding.etContent.clearFocus();
                binding.ivEmo.setImageResource(R.mipmap.ic_emoji);
                return false;
            }
        });
        //
        ((RecordButton) binding.btnAudio).setOnFinishedRecordListener(new RecordButton.OnFinishedRecordListener() {
            @Override
            public void onFinishedRecord(String audioPath, int time) {
                LogUtils.e("录音结束回调");
                File file = new File(audioPath);
                if (file.exists()) {
                    sendAudioMessage(audioPath,time);
                }
            }
        });

    }

    //文本消息
    private void sendTextMsg(String hello)  {
        final Message mMessgae=getBaseSendMessage(MsgType.TEXT);
        TextMsgBody mTextMsgBody=new TextMsgBody();
        mTextMsgBody.setMessage(hello);
        mMessgae.setBody(mTextMsgBody);
        //开始发送
        mAdapter.addData(mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);
    }
    int i = 1;
    //模拟收到的信息
    private void receiveTextMsg()  {
        /*final Message mMessgae=getBaseSendMessage(MsgType.RECEIVE_TEXT);
        TextMsgBody mTextMsgBody=new TextMsgBody();
        mTextMsgBody.setMessage(hello);
        mMessgae.setSentStatus(MsgSendStatus.SENDING);
        mMessgae.setTargetId(mTargetId);
        mMessgae.setBody(mTextMsgBody);
        //开始发送
        mAdapter.addData(mMessgae);*/
        //模拟两秒后发送成功
        //updateMsg(mMessgae);
        //下拉刷新模拟获取历史消息
        List<Message> mReceiveMsgList=new ArrayList<>();
        //构建文本消息
        Message mMessgaeText=getBaseReceiveMessage(MsgType.RECEIVE_TEXT);
        TextMsgBody mTextMsgBody=new TextMsgBody();
        mTextMsgBody.setMessage("收到的消息"+(i++));
        mMessgaeText.setBody(mTextMsgBody);
        mReceiveMsgList.add(mMessgaeText);
        LogUtils.e(mAdapter.getData().size());
        mAdapter.addData(mAdapter.getData().size(),mReceiveMsgList);
    }



    //图片消息
    private void sendImageMessage(final LocalMedia media) {
        final Message mMessgae=getBaseSendMessage(MsgType.IMAGE);
        ImageMsgBody mImageMsgBody=new ImageMsgBody();
        mImageMsgBody.setThumbPath(media.getRealPath());
        mMessgae.setBody(mImageMsgBody);
        //开始发送
        mAdapter.addData( mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);
    }


    //视频消息
    private void sendVedioMessage(final LocalMedia media) {
        final Message mMessgae=getBaseSendMessage(MsgType.VIDEO);
        //生成缩略图路径
        String vedioPath=media.getPath();
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(vedioPath);
        Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime();
        String imgname = System.currentTimeMillis() + ".jpg";
        String urlpath = Environment.getExternalStorageDirectory() + "/" + imgname;
        File f = new File(urlpath);
        try {
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        }catch ( Exception e) {
            LogUtils.e("视频缩略图路径获取失败："+e.toString());
            e.printStackTrace();
        }
        VideoMsgBody mImageMsgBody=new VideoMsgBody();
        mImageMsgBody.setExtra(urlpath);
        mMessgae.setBody(mImageMsgBody);
        //开始发送
        mAdapter.addData( mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);

    }

    //文件消息
    private void sendFileMessage(String from, String to, final String path) {
        final Message mMessgae=getBaseSendMessage(MsgType.FILE);
        FileMsgBody mFileMsgBody=new FileMsgBody();
        mFileMsgBody.setLocalPath(path);
        mFileMsgBody.setDisplayName(FileUtils.getFileName(path));
        mFileMsgBody.setSize(FileUtils.getFileLength(path));
        mMessgae.setBody(mFileMsgBody);
        //开始发送
        mAdapter.addData( mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);

    }

    //语音消息
    private void sendAudioMessage(  final String path,int time) {
        final Message mMessgae=getBaseSendMessage(MsgType.AUDIO);
        AudioMsgBody mFileMsgBody=new AudioMsgBody();
        mFileMsgBody.setLocalPath(path);
        mFileMsgBody.setDuration(time);
        mMessgae.setBody(mFileMsgBody);
        //开始发送
        mAdapter.addData( mMessgae);
        //模拟两秒后发送成功
        updateMsg(mMessgae);
    }


    private Message getBaseSendMessage(MsgType msgType){
        Message mMessgae=new Message();
        mMessgae.setUuid(UUID.randomUUID()+"");
        mMessgae.setSenderId(mSenderId);
        mMessgae.setTargetId(mTargetId);
        mMessgae.setSentTime(System.currentTimeMillis());
        mMessgae.setSentStatus(MsgSendStatus.SENDING);
        mMessgae.setMsgType(msgType);
        return mMessgae;
    }


    private Message getBaseReceiveMessage(MsgType msgType){
        Message mMessgae=new Message();
        mMessgae.setUuid(UUID.randomUUID()+"");
        mMessgae.setSenderId(mTargetId);
        mMessgae.setTargetId(mSenderId);
        mMessgae.setSentTime(System.currentTimeMillis());
        mMessgae.setSentStatus(MsgSendStatus.SENDING);
        mMessgae.setMsgType(msgType);
        return mMessgae;
    }


    private void updateMsg(final Message mMessgae) {
        binding.rvChatList.scrollToPosition(mAdapter.getItemCount() - 1);
        //模拟2秒后发送成功
        new Handler().postDelayed(new Runnable() {
            public void run() {
                int position=0;
                mMessgae.setSentStatus(MsgSendStatus.SENT);
                //更新单个子条目
                for (int i=0;i<mAdapter.getData().size();i++){
                    Message mAdapterMessage=mAdapter.getData().get(i);
                    if (mMessgae.getUuid().equals(mAdapterMessage.getUuid())){
                        position=i;
                    }
                }
                mAdapter.notifyItemChanged(position);

                receiveTextMsg();//模拟收到的信息

            }
        }, 1000);

    }

//    @OnClick({R.id.btn_send,R.id.rlPhoto,R.id.rlVideo,R.id.rlLocation,R.id.rlFile})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_send:
//                sendTextMsg(mEtContent.getText().toString());
//                mEtContent.setText("");
//                break;
//            case R.id.rlPhoto:
//                PictureFileUtil.openGalleryPic(ChatActivity.this,REQUEST_CODE_IMAGE);
//                break;
//            case R.id.rlVideo:
//                PictureFileUtil.openGalleryAudio(ChatActivity.this,REQUEST_CODE_VEDIO);
//                break;
//            case R.id.rlFile:
//                PictureFileUtil.openFile(ChatActivity.this,REQUEST_CODE_FILE);
//                break;
//            case R.id.rlLocation:
//                break;
//        }
//    }

    public class Event extends EventHandlers {

        public void onSend(){
            sendTextMsg(binding.etContent.getText().toString());
            binding.etContent.setText("");
        }
        public void onRlPhoto(){
            PictureSelector.create(ChatActivity.this)
                    .openGallery(SelectMimeType.ofImage())
                    .setImageEngine(GlideEngine.createGlideEngine())
                    .setSelectionMode(SelectModeConfig.MULTIPLE)
                    .setMaxSelectNum(9)
                    .forResult(new OnResultCallbackListener<LocalMedia>() {
                        @Override
                        public void onResult(ArrayList<LocalMedia> result) {
                            for (LocalMedia media : result) {
                                LogUtils.e("获取图片路径成功:"+  media.getRealPath());
                                sendImageMessage(media);
                            }
                        }

                        @Override
                        public void onCancel() {
                            LogUtils.e("onCancel");
                        }
                    });
        }
        public void onRlVideo(){
            PictureSelector.create(ChatActivity.this)
                    .openGallery(SelectMimeType.ofVideo())
                    .setImageEngine(GlideEngine.createGlideEngine())
                    .setSelectionMode(SelectModeConfig.SINGLE)
                    .setRecordVideoMaxSecond(10)
                    .isPreviewVideo(true)
                    .forResult(new OnResultCallbackListener<LocalMedia>() {
                        @Override
                        public void onResult(ArrayList<LocalMedia> result) {
                            for (LocalMedia media : result) {
                                LogUtils.e("获取图片路径成功:"+  media.getRealPath());
                                sendImageMessage(media);
                            }
                        }

                        @Override
                        public void onCancel() {
                            LogUtils.e("onCancel");
                        }
                    });
        }
        public void onRlFile(){
            ToastUtils.showShort("打开File");
        }
    }

}
