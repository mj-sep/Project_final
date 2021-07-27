package com.example.project_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// 메인 화면, 리니어 레이아웃 (5장. 레이아웃 익히기), 구글 지도 (13장. 멀티미디어와 구글 지도)
// 화면 전환 (10장. 액티비티와 인텐트), 옵션 메뉴 (7장. 메뉴와 대화상자)
public class MainActivity extends AppCompatActivity {

    LinearLayout baseLayout;
    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MINJOO");

        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        button1 = (Button) findViewById(R.id.Button1);
        button2 = (Button) findViewById(R.id.Button2);
        button3 = (Button) findViewById(R.id.Button3);

        // 버튼1(일기장) 클릭 시, SecondActivity 호출
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });

        // 버튼2(사진첩 보기) 클릭 시, ThirdActivity 호출
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(intent);
            }
        });

        // 버튼3(그림판) 클릭 시, FourthActivity 호출
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FourthActivity.class);
                startActivity(intent);
            }
        });
    }

    // 옵션 메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    // 옵션메뉴를 클릭했을 때,
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemWhite: // 배경색(흰색) 클릭 시
                baseLayout.setBackgroundColor(Color.WHITE);
                return true;
            case R.id.itemBlack: // 배경색(검정) 클릭 시
                baseLayout.setBackgroundColor(Color.BLACK);
                return true;
        }
        return false;
    }
}