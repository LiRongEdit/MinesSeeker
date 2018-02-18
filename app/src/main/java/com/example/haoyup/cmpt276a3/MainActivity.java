package com.example.haoyup.cmpt276a3;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
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

import com.example.haoyup.cmpt276a3.model.OptionSelect;
import com.example.haoyup.cmpt276a3.model.Square;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private OptionSelect optionSelect;
    private int NUM_ROWS;
    private int NUM_COLS;
    private Button buttons[][];
    private Square square[][];
    private int mineTotal;
    private int found = 0;
    private int scanUsed = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        optionSelect = OptionSelect.getInstance();
        NUM_ROWS = optionSelect.getRow();
        NUM_COLS = optionSelect.getCol();
        mineTotal = optionSelect.getMines();
        buttons = new Button[NUM_ROWS][NUM_COLS];
        square = new Square[NUM_ROWS][NUM_COLS];

        // Random assign the mines
        fillInMine();
        // Create table
        populateButton();
        // Update the scan number
        updateUI();


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
                    int n = rand.nextInt(2);
                    //Randomly fill the mine
                    if (n == 0 && temp != 0) {
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

            found++;

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
            button.setText("" + count);
            scanUsed++;
        }
        // Trigger the scan
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
            button.setText("" + count);
            scanUsed++;
        }
        square[row][col].addIndex();

        // Update the scan number
        updateUI();
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

    private void updateUI() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                Button button = buttons[i][j];
                if (found == mineTotal) {
                    button.setText("" + 0);
                }
            }
        }

        // Alert dialog
        if (found == mineTotal) {
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            DialogFragment dialog = new DialogFragment();
            dialog.show(manager, "MessageDialog");

        }
            // Set up the textview of mine finding and scan used
        TextView textFind = (TextView) findViewById(R.id.findMine);
        textFind.setText("Found " + found + " of " + mineTotal + " mines");
        TextView textScan = (TextView) findViewById(R.id.scanUsed);
        textScan.setText("# Scans used: " + scanUsed);

        // update the scan
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (square[i][j].isExistence() == false && square[i][j].getIndex() > 0)
                {
                    int count = 0;
                    // Check the hide mines in the same row
                    for (int row = 0; row < NUM_ROWS; row++) {
                        if (square[row][j].isExistence() == true && square[row][j].getIndex() == 0){
                            count++;
                        }
                    }
                    // Check the hide mines in the same column
                    for (int col = 0; col < NUM_COLS; col++) {
                        if (square[i][col].isExistence() == true && square[i][col].getIndex() == 0){
                            count++;
                        }
                    }
                    buttons[i][j].setText("" + count);
                }
                else if (square[i][j].isExistence() == true && square[i][j].getIndex() > 1)
                {
                    int count = 0;
                    // Check the hide mines in the same row
                    for (int row = 0; row < NUM_ROWS; row++) {
                        if (square[row][j].isExistence() == true && square[row][j].getIndex() == 0){
                            count++;
                        }
                    }
                    // Check the hide mines in the same column
                    for (int col = 0; col < NUM_COLS; col++) {
                        if (square[i][col].isExistence() == true && square[i][col].getIndex() == 0){
                            count++;
                        }
                    }
                    buttons[i][j].setText("" + count);
                }
            }
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, MainActivity.class);
    }
}
