package app.android.werdna.bluejack.kos.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class BookingTransaction implements Parcelable {

    private String _bookingId;
    private String _userId;
    private String _name;
    private String _facility;
    private Integer _price;
    private String _desc;
    private Double _longitude;
    private Double _latitude;
    private String _bookingDate;

    public BookingTransaction(String bookingId, String userId, String name, String facility, Integer price, String desc, Double longitude, Double latitude, String bookingDate) {
        _bookingId = bookingId;
        _userId = userId;
        _name = name;
        _facility = facility;
        _price = price;
        _desc = desc;
        _longitude = longitude;
        _latitude = latitude;
        _bookingDate = bookingDate;
    }

    private BookingTransaction(Parcel in) {
        _bookingId = in.readString();
        _userId = in.readString();
        _name = in.readString();
        _facility = in.readString();
        _price = in.readInt();
        _desc = in.readString();
        _longitude = in.readDouble();
        _latitude = in.readDouble();
        _bookingDate = in.readString();
    }

    public static final Creator<BookingTransaction> CREATOR = new Creator<BookingTransaction>() {
        @Override
        public BookingTransaction createFromParcel(Parcel in) {
            return new BookingTransaction(in);
        }

        @Override
        public BookingTransaction[] newArray(int size) {
            return new BookingTransaction[size];
        }
    };

    public static Creator<BookingTransaction> getCreator() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_bookingId);
        dest.writeString(_userId);
        dest.writeString(_name);
        dest.writeString(_facility);
        dest.writeInt(_price);
        dest.writeString(_desc);
        dest.writeDouble(_longitude);
        dest.writeDouble(_latitude);
        dest.writeString(_bookingDate);
    }

    public String getBookingId() {
        return _bookingId;
    }

    public void setBookingId(String bookingId) {
        _bookingId = bookingId;
    }

    public String getUserId() {
        return _userId;
    }

    public void setUserId(String userId) {
        _userId = userId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getFacility() {
        return _facility;
    }

    public void setFacility(String facility) {
        _facility = facility;
    }

    public Integer getPrice() {
        return _price;
    }

    public void setPrice(Integer price) {
        _price = price;
    }

    public String getDesc() {
        return _desc;
    }

    public void setDesc(String desc) {
        _desc = desc;
    }

    public Double getLongitude() {
        return _longitude;
    }

    public void setLongitude(Double longitude) {
        _longitude = longitude;
    }

    public Double getLatitude() {
        return _latitude;
    }

    public void setLatitude(Double latitude) {
        _latitude = latitude;
    }

    public String getBookingDate() {
        return _bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        _bookingDate = bookingDate;
    }
}
