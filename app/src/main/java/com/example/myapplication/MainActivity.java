package com.example.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    private EditText red_0,red_1,red_2,red_3,green_0,green_1,green_2,green_3,
            blue_0,blue_1,blue_2,blue_3,alpha_0,alpha_1,alpha_2,alpha_3
            ,red_4,green_4,blue_4,alpha_4;
    private Button bt_apply;
    private Button bt_reset;
    private Button bt_random;
    private ImageView iv_test;

    private List<EditText> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        red_0 = findViewById(R.id.red_0);
        red_1 = findViewById(R.id.red_1);
        red_2 = findViewById(R.id.red_2);
        red_3 = findViewById(R.id.red_3);
        red_4 = findViewById(R.id.red_4);

        datas.add(red_0);
        datas.add(red_1);
        datas.add(red_2);
        datas.add(red_3);
        datas.add(red_4);

        green_0 = findViewById(R.id.green_0);
        green_1 = findViewById(R.id.green_1);
        green_2 = findViewById(R.id.green_2);
        green_3 = findViewById(R.id.green_3);
        green_4 = findViewById(R.id.green_4);

        datas.add(green_0);
        datas.add(green_1);
        datas.add(green_2);
        datas.add(green_3);
        datas.add(green_4);

        blue_0 = findViewById(R.id.blue_0);
        blue_1 = findViewById(R.id.blue_1);
        blue_2 = findViewById(R.id.blue_2);
        blue_3 = findViewById(R.id.blue_3);
        blue_4 = findViewById(R.id.blue_4);

        datas.add(blue_0);
        datas.add(blue_1);
        datas.add(blue_2);
        datas.add(blue_3);
        datas.add(blue_4);

        alpha_0 = findViewById(R.id.alpha_0);
        alpha_1 = findViewById(R.id.alpha_1);
        alpha_2 = findViewById(R.id.alpha_2);
        alpha_3 = findViewById(R.id.alpha_3);
        alpha_4 = findViewById(R.id.alpha_4);

        datas.add(alpha_0);
        datas.add(alpha_1);
        datas.add(alpha_2);
        datas.add(alpha_3);
        datas.add(alpha_4);

        bt_apply = findViewById(R.id.bt_change);
        iv_test = findViewById(R.id.imageView2);
        bt_reset = findViewById(R.id.bt_reset);
        bt_random = findViewById(R.id.bt_random);

        bt_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            float[] datas =   new float[] { Float.valueOf(red_0.getText().toString()),Float.valueOf(red_1.getText().toString()), Float.valueOf(red_2.getText().toString()),  Float.valueOf(red_3.getText().toString()),  Float.valueOf(red_4.getText().toString()),   //red vector
                    Float.valueOf(green_0.getText().toString()), Float.valueOf(green_1.getText().toString()), Float.valueOf(green_2.getText().toString()), Float.valueOf(green_3.getText().toString()), Float.valueOf(green_4.getText().toString()), //green vector
                    Float.valueOf(blue_0.getText().toString()),  Float.valueOf(blue_1.getText().toString()),  Float.valueOf(blue_2.getText().toString()),  Float.valueOf(blue_3.getText().toString()),  Float.valueOf(blue_4.getText().toString()),  //blue vector
                    Float.valueOf(alpha_0.getText().toString()), Float.valueOf(alpha_1.getText().toString()), Float.valueOf(alpha_2.getText().toString()), Float.valueOf(alpha_3.getText().toString()), Float.valueOf(alpha_4.getText().toString()) };

            apply(datas);

            }
        });

        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < datas.size();i++){
                    datas.get(i).setText("0");
                    if(i%6==0){
                        datas.get(i).setText("1");
                    }
                }

            }
        });

        bt_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < datas.size();i++){
                    Random random = new Random(System.currentTimeMillis());
                    datas.get(i).setText("0");
                    if(i%6==0){
                        datas.get(i).setText(String.valueOf(random.nextInt(10)));
                    }
                    if((i+1)%5 == 0){
                        datas.get(i).setText(String.valueOf(random.nextInt(10)));
                    }
                }
            }
        });

    }

    private void apply(float[] nums){
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.test_color_matrix);
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888,true);
        Paint paint = new Paint();
        ColorMatrix mCm = new ColorMatrix();
        mCm.set(nums);             //alpha vector
        paint.setColorFilter(new ColorMatrixColorFilter(mCm));
        Canvas canvas = new Canvas(copy);
        canvas.drawBitmap(copy, 0, 0, paint);
        iv_test.setImageBitmap(copy);
    }
}
