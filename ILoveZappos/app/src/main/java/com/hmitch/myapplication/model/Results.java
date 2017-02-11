package com.hmitch.myapplication.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.hmitch.myapplication.database.ItemsTable;

public class Results implements Parcelable {

    private String brandName;
    private String thumbnailImageUrl;
    private int productId;
    private String originalPrice;
    private int styleId;
    private int colorId;
    private String price;
    private String percentOff;
    private String productUrl;
    private String productName;

    public Results() {
    }

    public Results(String brandName, String thumbnailImageUrl, int productId, String originalPrice, int styleId, int colorId, String price,
                   String percentOff, String productUrl, String productName) {

        this.brandName = brandName;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.productId = productId;
        this.originalPrice = originalPrice;
        this.styleId = styleId;
        this.colorId =colorId;
        this.price = price;
        this.percentOff = percentOff;
        this.productUrl = productUrl;
        this.productName = this.productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ContentValues toValues() {
        ContentValues values = new ContentValues(10);

        values.put(ItemsTable.COLUMN_BRANDNAME, brandName);
        values.put(ItemsTable.COLUMN_IMAGE, thumbnailImageUrl);
        values.put(ItemsTable.COLUMN_PRODUCTID, productId);
        values.put(ItemsTable.COLUMN_ORIGINALPRICE, originalPrice);
        values.put(ItemsTable.COLUMN_STYLEID, styleId);
        values.put(ItemsTable.COLUMN_COLORID, colorId);
        values.put(ItemsTable.COLUMN_PRICE, price);
        values.put(ItemsTable.COLUMN_PERCENTOFF, percentOff);
        values.put(ItemsTable.COLUMN_PRODUCTURL, productUrl);
        values.put(ItemsTable.COLUMN_PRODUCTNAME, productName);
        return values;
    }

    @Override
    public String toString() {
        return "{" +
                "brandName='" + brandName + '\'' +
                ", thumbnailImageUrl='" + thumbnailImageUrl + '\'' +
                ", productId='" + productId + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", styleId=" + styleId + '\'' +
                ", colorId=" + colorId + '\'' +
                ", price=" + price + '\'' +
                ", percentOff='" + percentOff + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.brandName);
        dest.writeString(this.thumbnailImageUrl);
        dest.writeInt(this.productId);
        dest.writeString(this.originalPrice);
        dest.writeInt(this.styleId);
        dest.writeInt(this.colorId);
        dest.writeString(this.price);
        dest.writeString(this.percentOff);
        dest.writeString(this.productUrl);
        dest.writeString(this.productName);
    }

    protected Results(Parcel in) {
        this.brandName = in.readString();
        this.thumbnailImageUrl = in.readString();
        this.productId = in.readInt();
        this.originalPrice = in.readString();
        this.styleId = in.readInt();
        this.colorId = in.readInt();
        this.price = in.readString();
        this.percentOff = in.readString();
        this.productUrl = in.readString();
        this.productName = in.readString();
    }

    public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel source) {
            return new Results(source);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
}

