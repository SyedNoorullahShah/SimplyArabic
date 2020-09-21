package com.example.android.simplyarabic;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordsAdapter extends RecyclerView.Adapter {

    private ArrayList<Word> words;
    private LayoutInflater inflater;
    private int background;
    private AudioPlaybackListener playbackListener;

    WordsAdapter(Context activity, ArrayList<Word> words, int bg, AudioPlaybackListener playbackListener) {
        this.playbackListener = playbackListener;
        inflater = LayoutInflater.from(activity);
        this.words = words;
        background = bg;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.number_items, viewGroup, false);
        return new NumbersViewHolder(view, background, playbackListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        NumbersViewHolder holder = (NumbersViewHolder) viewHolder;
        holder.setArabicTerm(words.get(i).getmMiwokTranslation());
        holder.setEnglishTerm(words.get(i).getmDefaultTranslation());
        if (words.get(i).getImage() != Word.NO_IMAGE) {
            holder.setImageView(words.get(i).getImage());
        }
        holder.setAudio(words.get(i).getSound());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }
}

class NumbersViewHolder extends RecyclerView.ViewHolder {
    private TextView arabicTerm;
    private TextView englishTerm;
    private ImageView imageView;
    private ImageButton playButton;
    private int audio;
    private AudioPlaybackListener audioPlaybackListener;

    private final View.OnClickListener mBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            audioPlaybackListener.onPlay(NumbersViewHolder.this);
        }
    };

    public NumbersViewHolder(@NonNull View itemView, int background, AudioPlaybackListener playbackListener) {
        super(itemView);
        audioPlaybackListener = playbackListener;
        arabicTerm = itemView.findViewById(R.id.miwok_term);
        englishTerm = itemView.findViewById(R.id.eng_term);
        imageView = itemView.findViewById(R.id.img);
        playButton = itemView.findViewById(R.id.play_pause);
        itemView.setBackgroundResource(background);
        playButton.setOnClickListener(mBtnListener);
    }

    public void setEnglishTerm(String eng) {
        englishTerm.setText(eng);
    }

    public void setArabicTerm(String miwok) {
        arabicTerm.setText(miwok);
    }

    public void setImageView(int img) {
        imageView.setImageResource(img);
    }

    public void setAudio(int sound) {
        audio = sound;
    }

    public ImageButton getPlayButton() {
        return playButton;
    }

    public int getAudio() {
        return audio;
    }
}
