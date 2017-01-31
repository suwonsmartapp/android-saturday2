package com.example.saturday2.models;

/**
 * Created by junsuk on 2016. 12. 31..
 */

public class Contact {
    private int imageResId;
    private String name;
    private String phoneNumber;

    public Contact(int imageResId, String name, String phoneNumber) {
        this.imageResId = imageResId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Contact{");
        sb.append("imageResId=").append(imageResId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
