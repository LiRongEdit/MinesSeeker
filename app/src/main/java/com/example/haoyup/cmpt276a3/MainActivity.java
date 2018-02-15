package com.example.haoyup.cmpt276a3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.haoyup.cmpt276a3.model.Square;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 6;
    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];
    private Square square[][] = new Square[NUM_ROWS][NUM_COLS];
    private int mineTotal = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Random assign the mines
        fillInMine();
        // Create table
        populateButton();
        // TO DO: Update the scan number
        updateScan();

    }


    private void fillInMine() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                //Initialize the square
                square[i][j] = new Square();
            }
        }
        Random rand = new Random();
        int count = 0;
        int temp = mineTotal;
        while (temp != 0){
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLS; j++) {
                    //make a array of true and false
                    int [] shuffle_List = new int[]{0, 1};
                    Collections.shuffle(Arrays.asList(shuffle_List));
                    //Randomly fill the mine
                    if (shuffle_List[0] == 0 && temp != 0) {
                        square[i][j].setExistence(true);
                        temp--;
                    }
                }
            }
        }
    }

    private void populateButton() {
        //initiating the new table
        TableLayout table = (TableLayout) findViewById(R.id.tableForButton);
        for (int i = 0; i < NUM_ROWS; i++) {
            //make rows
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            table.addView(tableRow);
            //fill up the rows with buttons
            for (int j = 0; j < NUM_COLS; j++) {
                final int FINAL_ROW = i;
                final int FINAL_COL = j;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
//                        if (board[TEMP_ROW][TEMP_COL].isExistence()) {
//                            gridButtonClicked(TEMP_ROW, TEMP_COL);
//                            revealed++;
//                            TextView textview = findViewById(R.id.revealedID);
//                            textview.setText("FOUND " + revealed + "of " + Num_MINES);
//                            board[TEMP_ROW][TEMP_COL].setExistence(false);
//                            for (int i = 0; i < NUM_COLS; i++){
//                                if (board[TEMP_ROW][i].isChecked())
//                                    gridButtonClickedMineFree(TEMP_ROW, i);
//                            }
//                            for (int j = 0; j < NUM_ROWS; j++){
//                                if (board[j][TEMP_COL].isChecked())
//                                    gridButtonClickedMineFree(j, TEMP_COL);
//                            }
//                        }
//                        else{
//                            if (!board[TEMP_ROW][TEMP_COL].isChecked()) {
//                                gridButtonClickedMineFree(TEMP_ROW, TEMP_COL);
//                                scanned_done++;
//                                TextView textview = findViewById(R.id.scanedID);
//                                textview.setText("# of scan used: " + scanned_done);
//                                board[TEMP_ROW][TEMP_COL].setChecked(true);
//                            }
//                        }
                    }
                });
                tableRow.addView(button);
                buttons[i][j] = button;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        Button button = buttons[row][col];

        // Lock Button Sizes:
        lockButtonSizes();
        // Scale image
        //If exist mine, show mine
        if (square[row][col].isExistence() == true && square[row][col].getIndex() == 0) {
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mine);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));
        }
        // If exist mine and already show the image, trigger the scan
        else if (square[row][col].isExistence() == true && square[row][col].getIndex() == 1){
            int count = 0;
            // Check the hide mines in the same row
            for (int i = 0; i < NUM_ROWS; i++) {
                if (square[i][col].isExistence() == true && square[i][col].getIndex() == 0){
                    count++;
                }
            }
            // Check the hide mines in the same column
            for (int j = 0; j < NUM_COLS; j++) {
                if (square[row][j].isExistence() == true && square[row][j].getIndex() == 0){
                    count++;
                }
            }
            square[row][col].setScan(count);
            button.setText("" + count);
        }

        else if (square[row][col].isExistence() == false && square[row][col].getIndex() == 0){
            int count = 0;
            // Check the hide mines in the same row
            for (int i = 0; i < NUM_ROWS; i++) {
                if (square[i][col].isExistence() == true && square[i][col].getIndex() == 0){
                    count++;
                }
            }
            // Check the hide mines in the same column
            for (int j = 0; j < NUM_COLS; j++) {
                if (square[row][j].isExistence() == true && square[row][j].getIndex() == 0){
                    count++;
                }
            }
            square[row][col].setScan(count);
            button.setText("" + count);
        }
        square[row][col].addIndex();
    }

    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void updateScan() {
    }
}
