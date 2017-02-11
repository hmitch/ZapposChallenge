package com.hmitch.myapplication.database;


public class ItemsTable {
    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_BRANDNAME = "brandName";
    public static final String COLUMN_IMAGE = "thumbnailImageUrl";
    public static final String COLUMN_PRODUCTID = "productId";
    public static final String COLUMN_ORIGINALPRICE = "originalPrice";
    public static final String COLUMN_STYLEID = "styleId";
    public static final String COLUMN_COLORID = "COLORID";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_PERCENTOFF = "percentOff";
    public static final String COLUMN_PRODUCTURL = "productUrl";
    public static final String COLUMN_PRODUCTNAME = "productName";

    public static final String[] ALL_COLUMNS =
            {COLUMN_BRANDNAME, COLUMN_IMAGE, COLUMN_PRODUCTID, COLUMN_ORIGINALPRICE,
                    COLUMN_STYLEID, COLUMN_COLORID, COLUMN_PRICE, COLUMN_PERCENTOFF, COLUMN_PRODUCTURL, COLUMN_PRODUCTNAME};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_BRANDNAME + " TEXT," +
                    COLUMN_IMAGE + " TEXT," +
                    COLUMN_PRODUCTID + " INTEGER PRIMARY KEY," +
                    COLUMN_ORIGINALPRICE + " REAL," +
                    COLUMN_STYLEID + " INTEGER," +
                    COLUMN_COLORID + " INTEGER," +
                    COLUMN_PRICE + " REAL," +
                    COLUMN_PERCENTOFF + " TEXT," +
                    COLUMN_PRODUCTURL + " TEXT," +
                    COLUMN_PRODUCTNAME + " TEXT" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ITEMS;
}