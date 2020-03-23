package app.android.werdna.bluejack.kos.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String _userId;
    private String _username;
    private String _password;
    private String _phoneNumber;
    private String _gender;
    private String _dateOfBirth;

    public User(String userId, String username, String password, String phoneNumber, String gender, String dateOfBirth) {
        this._userId = userId;
        this._username = username;
        this._password = password;
        this._phoneNumber = phoneNumber;
        this._gender = gender;
        this._dateOfBirth = dateOfBirth;
    }

    private User(Parcel in) {
        this._userId = in.readString();
        this._username = in.readString();
        this._password = in.readString();
        this._phoneNumber = in.readString();
        this._gender = in.readString();
        this._dateOfBirth = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public static Creator<User> getCreator() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_userId);
        dest.writeString(_username);
        dest.writeString(_password);
        dest.writeString(_phoneNumber);
        dest.writeString(_gender);
        dest.writeString(_dateOfBirth);
    }

    public String getUserId() {
        return _userId;
    }

    public void setUserId(String userId) {
        _userId = userId;
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        _username = username;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        _phoneNumber = phoneNumber;
    }

    public String getGender() {
        return _gender;
    }

    public void setGender(String gender) {
        _gender = gender;
    }

    public String getDateOfBirth() {
        return _dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        _dateOfBirth = dateOfBirth;
    }
}
