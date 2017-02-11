package com.hmitch.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hmitch.myapplication.model.Results;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.hmitch.myapplication.R.id.imageView;

@SuppressWarnings("FieldCanBeLocal")
public class DetailActivity extends AppCompatActivity {

    private TextView tvName, tvDescription, tvPrice;
    private ImageView itemImage;
    private static Results item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
        if (item == null) {
            throw new AssertionError("Null data item received!");
        }

        tvName = (TextView) findViewById(R.id.tvItemName);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        itemImage = (ImageView) findViewById(imageView);

        tvName.setText(item.getProductName());
        tvDescription.setText(item.getBrandName());
        tvPrice.setText(item.getPrice());
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(item.getThumbnailImageUrl()).getContent());
            itemImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream = null;

    }

    public void addToCart(View view) {
        Results item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
        ImageButton button = (ImageButton) findViewById(R.id.addToCartBttn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        Toast.makeText(this, item.getProductName() + " added to cart", Toast.LENGTH_SHORT).show();
    }
}