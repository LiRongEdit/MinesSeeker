package com.example.haoyup.cmpt276a3.model;

import android.app.VoiceInteractor;

public class OptionSelect {
    private int mines = 6;
    private int row = 4;
    private int col = 6;

    // Singleton
    private static OptionSelect instance;
    private OptionSelect() {

    }
    public static OptionSelect getInstance(){
        if (instance == null){
            instance = new OptionSelect();
        }
        return instance;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
