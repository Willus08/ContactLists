package posidenpalace.com.contactlists;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Android on 7/11/2017.
 */

public class Person implements Parcelable{

    String Name;
    String Phone;
    String ImageUri;
    Drawable image;

    protected Person(Parcel in) {
        Name = in.readString();
        Phone = in.readString();
        ImageUri = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Person(String name, String phone, Drawable image) {
        Name = name;
        Phone = phone;
        this.image = image;
    }

    public Person(String name, String phone, String imageUri) {
        Name = name;
        Phone = phone;
        ImageUri = imageUri;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Phone);
        dest.writeString(ImageUri);
    }
}
