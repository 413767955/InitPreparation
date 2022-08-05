package com.zlx.module_base.base_api.res_data.account;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FileName: UserInfo
 */
@NoArgsConstructor
@Data
public class UserInfo implements Parcelable {

    private String address_id;
    private String avatar;
    private String balance;
    private String id;
    private String index_reward;
    private String integral;
    private String invite_count;
    private String inviter_id;
    private String mobile;
    private String mobile_all;
    private String nickname;
    private String perfect;
    private String real_name;
    private String team_count;
    private String user_email;
    private String user_level;
    private String user_level_name;
    private String trad_password;


    protected UserInfo(Parcel in) {
        address_id = in.readString();
        avatar = in.readString();
        balance = in.readString();
        id = in.readString();
        index_reward = in.readString();
        integral = in.readString();
        invite_count = in.readString();
        inviter_id = in.readString();
        mobile = in.readString();
        mobile_all = in.readString();
        nickname = in.readString();
        perfect = in.readString();
        real_name = in.readString();
        team_count = in.readString();
        user_email = in.readString();
        user_level = in.readString();
        user_level_name = in.readString();
        trad_password = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address_id);
        parcel.writeString(avatar);
        parcel.writeString(balance);
        parcel.writeString(id);
        parcel.writeString(index_reward);
        parcel.writeString(integral);
        parcel.writeString(invite_count);
        parcel.writeString(inviter_id);
        parcel.writeString(mobile);
        parcel.writeString(mobile_all);
        parcel.writeString(nickname);
        parcel.writeString(perfect);
        parcel.writeString(real_name);
        parcel.writeString(team_count);
        parcel.writeString(user_email);
        parcel.writeString(user_level);
        parcel.writeString(user_level_name);
        parcel.writeString(trad_password);
    }
}
