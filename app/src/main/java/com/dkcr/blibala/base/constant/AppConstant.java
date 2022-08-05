package com.dkcr.blibala.base.constant;

/**
 * Description:
 * Author: lyl
 * Date: 2022/7/19 0:47
 */
public class AppConstant {

    public final static class LanguageType{
        //简体中文
        public final static String LANGUAGE_ZH_CN = "zh_cn";
        //繁体中文
        public final static String LANGUAGE_ZH_TC = "zh_tc";
        //英文
        public final static String LANGUAGE_EN = "en";
        //日文
        public final static String LANGUAGE_JA = "ja";
        //韩文
        public final static String LANGUAGE_KR = "kr";
        //泰文
        public final static String LANGUAGE_TH = "th";
    }

    //朋友圈类型
    public final static class CommentType {
        //单一评论
        public final static int COMMENT_TYPE_SINGLE = 0;
        //回复评论
        public final static int COMMENT_TYPE_REPLY = 1;
    }

    //隐私政策
    public static final String PRIVACY_POLICY = "https://api.eatingmeat.icu/licence/privacy_policy.html";
    //用户协议
    public static final String USER_AGREEMENT = "https://api.eatingmeat.icu/licence/user_agreement.html";

}
