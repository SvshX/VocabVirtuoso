package com.testapp.saschamelcher.testlogin.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by saschamelcher on 23/08/15.
 */

@ParseClassName("Vocabs")
public class Vocabs extends ParseObject {

    public Vocabs() {
        super();
    }

    public String getNativeWord() {
        return getString("NativeWord");
    }

    public void setNativeWord(String nativeWord) {
        put("NativeWord", nativeWord);
    }

    public String getTranslation() {
        return getString("Translation");
    }

    public void setTranslation(String translation) {
        put("Translation", translation);
    }

    public String getSentence() {
        return getString("Sentence");
    }

    public void setSentence(String sentence) {
        put("Sentence", sentence);
    }

    public String getVocabSet() {
        return getString("VocabSet");
    }

    public void setVocabSet(String vocabSet) {
        put("VocabSet", vocabSet);
    }

//
//    private Vocabs(Parcel in) {
//        nativeWord = in.readString();
//        translation = in.readString();
//        sentence = in.readString();
//    }

//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(nativeWord);
//        dest.writeString(translation);
//        dest.writeString(sentence);
//
//    }
//
//    public static final Parcelable.Creator<Vocabs> CREATOR = new Parcelable.Creator<Vocabs>() {
//
//        public Vocabs createFromParcel(Parcel in) {
//            return new Vocabs(in);
//        }
//
//        public Vocabs[] newArray(int size) {
//            return new Vocabs[size];
//        }
//
//    };
}
