package com.dkcr.blibala.ui.recommend.bean;

import android.content.Context;

import com.dkcr.blibala.base.constant.AppConstant;
import com.dkcr.blibala.base.constant.FriendCircleConstant;
import com.dkcr.blibala.util.SpanUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author KCrason
 * @date 2018/5/2
 */
public class DataCenter {

/*    public static void init() {
        new Thread(DataCenter::loadEmojis).start();
    }*/

    //public static final List<EmojiDataSource> emojiDataSources = new ArrayList<>();

//    public static void loadEmojis() {
//        for (int i = 0; i < 2; i++) {
//            EmojiDataSource emojiDataSource = new EmojiDataSource();
//            List<EmojiBean> typeEmojiBeans = new ArrayList<>();
//            if (i == 0) {
//                for (int j = 0; j < Constants.TYPE01_EMOJI_NAME.length; j++) {
//                    EmojiBean emojiBean = new EmojiBean();
//                    emojiBean.setEmojiName(Constants.TYPE01_EMOJI_NAME[j]);
//                    emojiBean.setEmojiResource(Constants.TYPE01_EMOJI_DREWABLES[j]);
//                    typeEmojiBeans.add(emojiBean);
//                }
//                emojiDataSource.setEmojiType(Constants.EmojiType.EMOJI_TYPE_01);
//            } else {
//                for (int j = 0; j < Constants.TYPE02_EMOJI_NAME.length; j++) {
//                    EmojiBean emojiBean = new EmojiBean();
//                    emojiBean.setEmojiName(Constants.TYPE02_EMOJI_NAME[j]);
//                    emojiBean.setEmojiResource(Constants.TYPE02_EMOJI_DREWABLES[j]);
//                    typeEmojiBeans.add(emojiBean);
//                }
//                emojiDataSource.setEmojiType(Constants.EmojiType.EMOJI_TYPE_02);
//            }
//            emojiDataSource.setEmojiList(typeEmojiBeans);
//            emojiDataSources.add(emojiDataSource);
//        }
//    }


    public static List<FriendCircleBean> makeFriendCircleBeans(Context context) {
        List<FriendCircleBean> friendCircleBeans = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            FriendCircleBean friendCircleBean = new FriendCircleBean();
            int randomValue = (int) (Math.random() * 300);
            if (randomValue < 100) {
                friendCircleBean.setViewType(FriendCircleConstant.FriendCircleType.FRIEND_CIRCLE_TYPE_ONLY_WORD);
            } else if (randomValue < 200) {
                friendCircleBean.setViewType(FriendCircleConstant.FriendCircleType.FRIEND_CIRCLE_TYPE_WORD_AND_IMAGES);
            } else {
                friendCircleBean.setViewType(FriendCircleConstant.FriendCircleType.FRIEND_CIRCLE_TYPE_WORD_AND_URL);
            }
            friendCircleBean.setCommentBeans(makeCommentBeans(context));
            friendCircleBean.setImageUrls(makeImages());
            List<PraiseBean> praiseBeans = makePraiseBeans();
            friendCircleBean.setPraiseSpan(SpanUtils.makePraiseSpan(context, praiseBeans));
            friendCircleBean.setPraiseBeans(praiseBeans);
            friendCircleBean.setContent(FriendCircleConstant.CONTENT[(int) (Math.random() * 10)]);

            UserBean userBean = new UserBean();
            userBean.setUserName(FriendCircleConstant.USER_NAME[(int) (Math.random() * 30)]);
            userBean.setUserAvatarUrl(FriendCircleConstant.IMAGE_URL[(int) (Math.random() * 50)]);
            friendCircleBean.setUserBean(userBean);


            OtherInfoBean otherInfoBean = new OtherInfoBean();
            otherInfoBean.setTime(FriendCircleConstant.TIMES[(int) (Math.random() * 20)]);
            int random = (int) (Math.random() * 30);
            if (random < 20) {
                otherInfoBean.setSource(FriendCircleConstant.SOURCE[random]);
            } else {
                otherInfoBean.setSource("");
            }
            friendCircleBean.setOtherInfoBean(otherInfoBean);
            friendCircleBeans.add(friendCircleBean);
        }
        return friendCircleBeans;
    }


    private static List<String> makeImages() {
        List<String> imageBeans = new ArrayList<>();
        int randomCount = (int) (Math.random() * 9);
        if (randomCount == 0) {
            randomCount = randomCount + 1;
        } else if (randomCount == 8) {
            randomCount = randomCount + 1;
        }
        for (int i = 0; i < randomCount; i++) {
            imageBeans.add(FriendCircleConstant.IMAGE_URL[(int) (Math.random() * 50)]);
        }
        return imageBeans;
    }


    private static List<PraiseBean> makePraiseBeans() {
        List<PraiseBean> praiseBeans = new ArrayList<>();
        int randomCount = (int) (Math.random() * 20);
        for (int i = 0; i < randomCount; i++) {
            PraiseBean praiseBean = new PraiseBean();
            praiseBean.setPraiseUserName(FriendCircleConstant.USER_NAME[(int) (Math.random() * 30)]);
            praiseBeans.add(praiseBean);
        }
        return praiseBeans;
    }


    private static List<CommentBean> makeCommentBeans(Context context) {
        List<CommentBean> commentBeans = new ArrayList<>();
        int randomCount = (int) (Math.random() * 20);
        for (int i = 0; i < randomCount; i++) {
            CommentBean commentBean = new CommentBean();
            if ((int) (Math.random() * 100) % 2 == 0) {
                commentBean.setCommentType(FriendCircleConstant.CommentType.COMMENT_TYPE_SINGLE);
                commentBean.setChildUserName(FriendCircleConstant.USER_NAME[(int) (Math.random() * 30)]);
            } else {
                commentBean.setCommentType(FriendCircleConstant.CommentType.COMMENT_TYPE_REPLY);
                commentBean.setChildUserName(FriendCircleConstant.USER_NAME[(int) (Math.random() * 30)]);
                commentBean.setParentUserName(FriendCircleConstant.USER_NAME[(int) (Math.random() * 30)]);
            }

            commentBean.setCommentContent(FriendCircleConstant.COMMENT_CONTENT[(int) (Math.random() * 30)]);
            commentBean.build(context);
            commentBeans.add(commentBean);
        }
        return commentBeans;
    }
}
