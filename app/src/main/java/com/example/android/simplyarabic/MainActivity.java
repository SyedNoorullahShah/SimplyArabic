/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.simplyarabic;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView num_text = null;
    private TextView color_text = null;
    private TextView fam_text = null;
    private TextView phrases_text = null;

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.getBackground().setAlpha(240);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_MOVE) {
                v.getBackground().setAlpha(174);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        num_text = findViewById(R.id.numbers);
        num_text.setOnTouchListener(mOnTouchListener);
        num_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, NumbersActivity.class);       //explicit intent
                startActivity(in);
            }
        });


        color_text = findViewById(R.id.colors);
        color_text.setOnTouchListener(mOnTouchListener);
        color_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(in);
            }
        });

        fam_text = findViewById(R.id.family);
        fam_text.setOnTouchListener(mOnTouchListener);
        fam_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(in);
            }
        });

        phrases_text = findViewById(R.id.phrases);
        phrases_text.setOnTouchListener(mOnTouchListener);
        phrases_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(in);
            }
        });
    }
}
