package com.example.project_final;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

// 일기장 내장 메모리 파일 처리 (8장. 파일 처리), 데이트 피커 (6장. 고급 위젯 다루기) , 토스트 (7장. 메뉴와 대화상자)
public class SecondActivity extends Activity {
    DatePicker dp;
    EditText edtDiary;
    Button btnWrite;
    String fileName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("일기장");

        dp = (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);

        // 데이트피커를 설정하고 Calendar 클래스를 이용하여 현재 날짜의 연, 월, 일을 구한 후 데이트피커 초기화
        // 데이트피커의 날짜가 변경되면 벼경된 날짜에 해당하는 일기 파일(연_월_일.txt)의 내용을 보여줌

        // 현재 날짜를 구한다.
        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        // 데이트피커를 초기화한다.
        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 현재 날짜에 해당하는 파일 이름을 '연_월_일.txt'로 만듦.
                fileName = Integer.toString(year) + "_" + Integer.toString(monthOfYear + 1)+"_"+Integer.toString(dayOfMonth)+".txt";
                // 현재 날짜의 일기 파일을 읽어들임.
                String str = readDiary(fileName);
                // 읽어온 일기 내용을 에디트 텍스트에 출력
                edtDiary.setText(str);
                btnWrite.setEnabled(true);
            }
        });

        // 일기 파일은 [View] > [Tool Windows] > [Device File Explorer] > data > data > [com.example.project_final] > [files]에 저장

        btnWrite.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                try{
                    // 일기 파일을 쓰기 모드로 연다.
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    // editText의 내용을 일기 파일에 byte[]으로 쓰고 파일 닫는다.
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    // 토스트 메세지
                    Toast.makeText(getApplicationContext(), fileName + " 이 저장됨", Toast.LENGTH_SHORT).show();
                } catch(IOException e) {}
            }
        });

    }

    String readDiary(String fName){
        String diaryStr = null;
        FileInputStream inFs;
        try{ // 일기 파일을 열어 입력 파일 스트림에 저장
            inFs = openFileInput(fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnWrite.setHint("수정하기");
        } catch(IOException e){
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        return diaryStr;
    }
}
