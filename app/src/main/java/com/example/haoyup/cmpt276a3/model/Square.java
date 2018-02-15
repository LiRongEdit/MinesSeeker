package com.example.haoyup.cmpt276a3.model;


public class Square {
//    private int x;
//    private int y;
    private int index;
    private boolean existence;
//    private boolean checked;
    //private int scan;


    //constructors
    public Square()
    {
//        this.x = 0;
//        this.y = 0;
        index = 0;
        existence = false; //If not exist, set false
//        this.checked = false;
    }

//    public Square(int x_coordinate, int y_coordinate) {
//        this.x = x_coordinate;
//        this.y = y_coordinate;
//    }

    //getters
//    public int getX() {
//        return x;
//    }

//    public int getY() {
//        return y;
//    }

    public int getIndex() {return index;}

    public boolean isExistence() {
        return existence;
    }

//    public boolean isChecked() {
//        return checked;
//    }
    //public int getScan() {return scan;}


    //setters

    public void setIndex(int i) {index = i;}

    public void setExistence(boolean exist) {
        existence = exist;
    }

//    public void setChecked(boolean checked) {
//        this.checked = checked;
//    }

    //public void setScan(int scan) {this.scan = scan;}

}
