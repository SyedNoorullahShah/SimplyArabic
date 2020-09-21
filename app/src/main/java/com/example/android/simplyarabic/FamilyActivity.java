package com.example.android.simplyarabic;

import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private RecyclerView list;
    private WordsAdapter adapter;
    private ArrayList<Word> family;
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
        family = new ArrayList<Word>();
        setFamily(family);
        adapter = new WordsAdapter(this, family, R.color.category_family, audioPlaybackListener);
        list.setAdapter(adapter);
    }

    private void setFamily(ArrayList<Word> family) {
        family.add(new Word("father", "أب", R.drawable.family_father, R.raw.father_in_arabic));
        family.add(new Word("mother", "أم", R.drawable.family_mother, R.raw.mother));
        family.add(new Word("son", "ابن", R.drawable.family_son, R.raw.son_in_arabic));
        family.add(new Word("daughter", "ابنة", R.drawable.family_daughter, R.raw.daughter_in_arabic));
        family.add(new Word("older brother", "الأخ الأكبر", R.drawable.family_older_brother, R.raw.older_brother));
        family.add(new Word("younger brother", "الأخ الأصغر", R.drawable.family_younger_brother, R.raw.younger_brother));
        family.add(new Word("older sister", "الأخت الكبرى", R.drawable.family_older_sister, R.raw.older_sister));
        family.add(new Word("younger sister", "الاخت الصغرى", R.drawable.family_younger_sister, R.raw.younger_sister));
        family.add(new Word("grandmother", "جدة", R.drawable.family_grandmother, R.raw.grandmother_in_arabic));
        family.add(new Word("grandfather", "جد", R.drawable.family_grandfather, R.raw.grandfather_in_arabic));
    }

}

