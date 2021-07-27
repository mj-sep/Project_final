package com.example.project_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.project_final.R;

import org.w3c.dom.Text;

// 그림판 그래픽 (9장. 그래픽과 이미지), 옵션 메뉴 (7장. 메뉴와 대화상자)
public class FourthActivity extends AppCompatActivity {
    final static int LINE = 1, CIRCLE = 2, RECT = 3;
    final static int red = 4, blue = 5, black = 6;
    static int curShape = LINE;
    static int color = red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("그림판");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "선 그리기");
        menu.add(0, 2, 0, "원 그리기");
        menu.add(0, 3, 0, "직사각형 그리기");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                curShape = LINE; // 선
                return true;
            case 2:
                curShape = CIRCLE; // 원
                return true;
            case 3:
                curShape = RECT; // 직사각형
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class MyGraphicView extends View {
        int startX = -1, startY = -1, stopX = -1, stopY =-1;
        public MyGraphicView(Context context){
            super(context);
        }
        public boolean onTouchEvent(MotionEvent event){
            switch (event.getAction()){
                // 마우스 누를 때, 시작점 위치 지정
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                // 마우스 움직이고 마우스를 떼면 도착점 위치 지정
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);


            switch (curShape){
                case LINE:
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                case RECT:
                    Rect rect = new Rect(startX, startY, stopX, stopY);
                    canvas.drawRect(rect, paint);
                    break;
            }

        }
    }


}