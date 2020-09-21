package com.example.android.simplyarabic;

import android.media.MediaPlayer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private RecyclerView numbers;
    private WordsAdapter numbersAdapter;
    private ArrayList<Word> nums;
    private MediaPlayer mediaPlayer;
    private NumbersViewHolder currentItem;
    private NumbersViewHolder prevItem;

    private AudioPlaybackListener audioPlaybackListener = new AudioPlaybackListener() {
        @Override
        public void onPlay(NumbersViewHolder numbersViewHolder) {
            currentItem = numbersViewHolder;
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                stopAudio();
                if (prevItem.getAdapterPosition() != currentItem.getAdapterPosition()) {
                    prevItem.getPlayButton().setImageResource(R.drawable.ic_play_arrow);
                    startAudio(currentItem.getAudio());
                }

            } else {
                startAudio(currentItem.getAudio());
            }
            prevItem = currentItem;
        }
    };

    private void stopAudio() {
        mediaPlayer.stop();
        currentItem.getPlayButton().setImageResource(R.drawable.ic_play_arrow);
        releaseMediaFile();
    }

    private void startAudio(int audio) {
        mediaPlayer = MediaPlayer.create(this, audio);
        mediaPlayer.setOnCompletionListener(audioCompleteListener);
        currentItem.getPlayButton().setImageResource(R.drawable.ic_pause);
        mediaPlayer.start();

    }


    private MediaPlayer.OnCompletionListener audioCompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            currentItem.getPlayButton().setImageResource(R.drawable.ic_play_arrow);
            releaseMediaFile();
        }
    };


    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaFile();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.words_list);
        numbers = findViewById(R.id.list);
        numbers.setLayoutManager(new LinearLayoutManager(this));
        nums = new ArrayList<Word>();
        setNumbers(nums);
        numbersAdapter = new WordsAdapter(this, nums, R.color.category_phrases, audioPlaybackListener);
        numbers.setAdapter(numbersAdapter);
    }

    private void releaseMediaFile() {
        if (mediaPlayer != null) {
            Log.d("HEREE", "releaseMediaFile: ");
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void setNumbers(ArrayList<Word> nums) {
        nums.add(new Word("one", "وا حد", R.drawable.number_one, R.raw.one));
        nums.add(new Word("two", "إثنان", R.drawable.number_two, R.raw.two));
        nums.add(new Word("three", "ثلاثة", R.drawable.number_three, R.raw.three));
        nums.add(new Word("four", "أربعة", R.drawable.number_four, R.raw.four));
        nums.add(new Word("five", "خمسة", R.drawable.number_five, R.raw.five));
        nums.add(new Word("six", "ستّة", R.drawable.number_six, R.raw.arabic_six));
        nums.add(new Word("seven", "سبعة", R.drawable.number_seven, R.raw.arabic_seven));
        nums.add(new Word("eight", "ثمانية", R.drawable.number_eight, R.raw.arabic_eight));
        nums.add(new Word("nine", "تسعة", R.drawable.number_nine, R.raw.arabic_nine));
        nums.add(new Word("ten", "عشرة", R.drawable.number_ten, R.raw.arabic_ten));
    }
}