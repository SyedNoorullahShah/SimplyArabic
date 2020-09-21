package com.example.android.simplyarabic;

import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private RecyclerView list;
    private WordsAdapter adapter;
    private ArrayList<Word> phrases;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.words_list);
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        phrases = new ArrayList<Word>();
        setPhrases(phrases);
        adapter = new WordsAdapter(this, phrases, R.color.category_phrases, audioPlaybackListener);
        list.setAdapter(adapter);
    }

    private void setPhrases(ArrayList<Word> phrases) {
        phrases.add(new Word("Where are you from?", "من أين أنت؟", R.raw.where_from));
        phrases.add(new Word("What is your name?", "ما اسمك؟", R.raw.what_is_your_name));
        phrases.add(new Word("My name is...", "اسمي...", R.raw.my_name));
        phrases.add(new Word("How are you?", "كيف حالك؟", R.raw.how_are));
        phrases.add(new Word("I’m fine.", "أنا بخير", R.raw.fine));
        phrases.add(new Word("Nice to meet you", "سررت بلقائك", R.raw.nice_to_meet));
        phrases.add(new Word("How do you say … in Arabic?", "كيف يمكنك قول … باللغة العربية؟", R.raw.how_do_say));
        phrases.add(new Word("Can you speak English?", "هل تتحدث / تتحدثين الانجليزية؟", R.raw.can_you_speak));
        phrases.add(new Word("Can you recommend a good restaurant nearby?", "هل تعرف مطعما جيدا قريبا؟", R.raw.restaurant_nearby));
        phrases.add(new Word("I can speak a little bit of Arabic.", "يمكنني التحدث قليلاً باللغة العربية", R.raw.i_can_speak));

    }
}
