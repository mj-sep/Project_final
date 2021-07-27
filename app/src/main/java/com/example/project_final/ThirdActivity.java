package com.example.project_final;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

 // 뷰플리퍼 (6장. 고급위젯 다루기), 오디오 (13장. 멀티미디어와 구글지도)
public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        setTitle("사진첩");

        final MediaPlayer mPlayer;
        mPlayer = MediaPlayer.create(this,R.raw.song1);

        Button btnStart = (Button) findViewById(R.id.btnStart);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);

        viewFlipper.setFlipInterval(1500); // 1.5초

        // 스위치 클릭 시 음악 재생
        final Switch switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch1.isChecked() == true)
                    mPlayer.start();
                else
                    mPlayer.stop();
            }
        });

        // 자동보기 시작 버튼 클릭 시
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.startFlipping();
            }
        });

        // 자동보기 정지 버튼 클릭 시
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.stopFlipping();
            }
        });

    }
}
