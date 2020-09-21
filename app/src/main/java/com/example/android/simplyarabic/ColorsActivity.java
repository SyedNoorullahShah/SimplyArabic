package com.example.android.simplyarabic;

import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private RecyclerView list;
    private WordsAdapter adapter;
    private ArrayList<Word> colors;
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

    private void releaseMediaFile() {
        if (mediaPlayer != null) {
            Log.d("HEREE", "releaseMediaFile: ");
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        colors = new ArrayList<Word>();
        setColors(colors);
        adapter = new WordsAdapter(this, colors, R.color.category_colors, audioPlaybackListener);
        list.setAdapter(adapter);
    }

    private void setColors(ArrayList<Word> colors) {
        colors.add(new Word("red", "أحمر", R.drawable.color_red, R.raw.red));
        colors.add(new Word("green", "أخضر", R.drawable.color_green, R.raw.green));
        colors.add(new Word("brown", "بنى", R.drawable.color_brown, R.raw.brown));
        colors.add(new Word("black", "أسود", R.drawable.color_black, R.raw.black));
        colors.add(new Word("gray", "رمادى", R.drawable.color_gray, R.raw.grey));
        colors.add(new Word("dusty yellow", "أصفر مغبر", R.drawable.color_dusty_yellow, R.raw.dusty_yellow));
        colors.add(new Word("yellow", "أصفر", R.drawable.color_mustard_yellow, R.raw.yellow));
        colors.add(new Word("white", "أبيض", R.drawable.color_white, R.raw.white));
    }
}
