package com.example.viewdemo;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView imgViewSample;
    final String TAG = "GESTURE_DEMO";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txtViewSample = findViewById(R.id.txtViewSample);
        imgViewSample = findViewById(R.id.imgViewSample);
        Button btnShowImageOrText = findViewById(R.id.btnShowImageOrText);
        Button btnShowBoth = findViewById(R.id.btnShowBoth);

        Drawable img = ResourcesCompat
                            .getDrawable(getResources(),R.drawable.border,getTheme());
        //setBounds for img
        if (img!= null){
            img.setBounds(0, 0,
                    img.getIntrinsicWidth(),img.getIntrinsicHeight());
        }
        txtViewSample.setCompoundDrawables(img,null,img,null);
        txtViewSample.setCompoundDrawablePadding(8);
        txtViewSample.setAlpha(1.0f); //1.0f fully opaque, 0.0f fully transparent

        btnShowBoth.setOnClickListener((View view) -> {
                txtViewSample.setVisibility(View.VISIBLE);
                imgViewSample.setVisibility(View.VISIBLE);
        });

        btnShowImageOrText.setOnClickListener((View view) -> {
            if(btnShowImageOrText.getText()
                    .equals(getString(R.string.txtShowImage))) {
                imgViewSample.setVisibility(View.VISIBLE);
                txtViewSample.setVisibility(View.INVISIBLE);
                btnShowImageOrText.setText(R.string.txtShowText);
            } else {
                txtViewSample.setVisibility(View.VISIBLE);
                imgViewSample.setVisibility(View.GONE);
                btnShowImageOrText.setText(R.string.txtShowImage);
            }
        });

        txtViewSample.setOnTouchListener(new CustomTouchListener(MainActivity.this) {
            @Override
            public void onLongClick() {
                super.onLongClick();
                Log.d(TAG,"Long click on textview detected");
                //Set text STRIKETROUGH
                if(txtViewSample.getPaint().isStrikeThruText())
                    //Make text not strikethrough
                    txtViewSample.setPaintFlags(txtViewSample.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG); //AND operation with negated strike thru binary flag (copy all flags and turns strikethrough flag to zero
                else
                    //Make text Strikethrough
                    txtViewSample.setPaintFlags(txtViewSample.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); //OR operatuon with strike through binary flag (copies all flags and turns strikethorugh flag to 1)

            }

            @Override
            public void onSingleClick() {
                super.onSingleClick();
                Log.d(TAG,"Single click on textview detected");
            }

            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                Log.d(TAG,"Double click on textview detected");
            }

            @Override
            public void onUpSwipe() {
                super.onUpSwipe();
                Log.d(TAG,"Up Swipe on textview detected");
                //Move object UP
                int horzGravity = txtViewSample.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK; //AND operation current gravity and horz gravity mask
                txtViewSample.setGravity(horzGravity | Gravity.TOP);  //Combine current horz gravity and move to TOP
            }

            @Override
            public void onDownSwipe() {
                super.onDownSwipe();
                Log.d(TAG,"Down Swipe on textview detected");
                //Move object DOWN
                int horzGravity = txtViewSample.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK; //AND operation current gravity and horz gravity mask
                txtViewSample.setGravity(horzGravity | Gravity.BOTTOM);  //Combine current horz gravity and move to BOTTOM
            }

            @Override
            public void onLeftSwipe() {
                super.onLeftSwipe();
                Log.d(TAG,"Left Swipe on textview detected");
                //Move object LEFT
                int vertGravity = txtViewSample.getGravity() & Gravity.VERTICAL_GRAVITY_MASK; //AND operation current gravity and vert gravity mask
                txtViewSample.setGravity(vertGravity | Gravity.LEFT); //Combine current vert gravity and move to LEFT
            }

            @Override
            public void onRightSwipe() {
                super.onRightSwipe();
                Log.d(TAG,"Right Swipe on textview detected");
                //Move object RIGHT
                int vertGravity = txtViewSample.getGravity() & Gravity.VERTICAL_GRAVITY_MASK;  //AND operation current gravity and vert gravity mask
                txtViewSample.setGravity(vertGravity | Gravity.RIGHT);   //Combine current vert gravity and move to RIGHT

            }
        });

        imgViewSample.setOnTouchListener(new CustomTouchListener(MainActivity.this){
            @Override
            public void onSingleClick() {
                super.onSingleClick();
                Log.d(TAG,"Single click on imageview detected");
                //Change image source
                if(imgViewSample.getDrawable().getConstantState() != ResourcesCompat.getDrawable(getResources(), R.drawable.bird, getTheme()).getConstantState()){
                    imgViewSample.setImageResource(R.drawable.bird);
                }else{
                    imgViewSample.setImageResource(R.drawable.fire);
                }
            }

            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                Log.d(TAG,"Double click on imageview detected");
                //Change image size
                if(imgViewSample.getScaleType() == ImageView.ScaleType.FIT_CENTER){
                    imgViewSample.setScaleType(ImageView.ScaleType.FIT_XY);
                }else{
                    imgViewSample.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
            }
        });


         /*try {
            //bunch of statements
            //int num = Integer.parseInt("2.3");
        } catch (Exception ex){
            Toast
                    .makeText(this, "Enter valid input", Toast.LENGTH_SHORT)
                    .show();
            ex.printStackTrace(System.err); //not a good idea
            Log.d(TAG,Log.getStackTraceString(ex));
            Log.d(TAG,"Error in input found");
        }*/

    }

}