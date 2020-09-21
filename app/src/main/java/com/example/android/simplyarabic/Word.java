package com.example.android.simplyarabic;

public class Word {
    private String mArabicTranslation;
    private String mDefaultTranslation;
    private int image;
    private int sound;
    public static final int NO_IMAGE = 0;

    public Word(String defaultTranslation, String arabicTranslation, int audio) {
        mDefaultTranslation = defaultTranslation;
        mArabicTranslation = arabicTranslation;
        image = NO_IMAGE;
        sound = audio;
    }

    public Word(String defaultTranslation, String arabicTranslation, int image, int audio) {
        mDefaultTranslation = defaultTranslation;
        mArabicTranslation = arabicTranslation;
        this.image = image;
        sound = audio;
    }

    public String getmMiwokTranslation() {
        return mArabicTranslation;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getImage() {
        return image;
    }

    public int getSound() {
        return sound;
    }
}
