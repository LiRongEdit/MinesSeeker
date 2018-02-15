package com.example.haoyup.cmpt276a3.model;


public class Square {
    private int index; //Count for the times click the button
    private boolean existence;
    private int scan;


    //constructors
    public Square()
    {
        index = 0;
        existence = false; //If not exist, set false
        scan = 0;
    }

    public int getIndex() {return index;}

    public boolean isExistence() {
        return existence;
    }

    public void addIndex() {index++;}

    public void setExistence(boolean exist) {
        existence = exist;
    }

    public void setScan(int n) {scan = n;}

}
