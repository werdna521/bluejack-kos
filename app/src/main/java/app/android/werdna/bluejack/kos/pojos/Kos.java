package app.android.werdna.bluejack.kos.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Kos implements Parcelable {

    private String _name;
    private String _facility;
    private Integer _price;
    private String _desc;
    private Double _longitude;
    private Double _latitude;
    private String _imageURL;

    public Kos(String name, String facility, Integer price, String desc, Double longitude, Double latitude,
               String imageURL) {
        _name = name;
        _facility = facility;
        _price = price;
        _desc = desc;
        _longitude = longitude;
        _latitude = latitude;
        _imageURL = imageURL;
    }

    private Kos(Parcel in) {
        _name = in.readString();
        _facility = in.readString();
        _price = in.readInt();
        _desc = in.readString();
        _longitude = in.readDouble();
        _latitude = in.readDouble();
        _imageURL = in.readString();
    }

    public static final Creator<Kos> CREATOR = new Creator<Kos>() {
        @Override
        public Kos createFromParcel(Parcel in) {
            return new Kos(in);
        }

        @Override
        public Kos[] newArray(int size) {
            return new Kos[size];
        }
    };

    public static Creator<Kos> getCreator() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeString(_facility);
        dest.writeInt(_price);
        dest.writeString(_desc);
        dest.writeDouble(_longitude);
        dest.writeDouble(_latitude);
        dest.writeString(_imageURL);
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

    public String getImageURL() {
        return _imageURL;
    }

    public void setImageURL(String imageURL) {
        _imageURL = imageURL;
    }

    public void setLatitude(Double latitude) {
        _latitude = latitude;
    }
}
