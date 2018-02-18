package com.example.haoyup.cmpt276a3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.haoyup.cmpt276a3.model.OptionSelect;

public class OptionActivity extends AppCompatActivity {

    private OptionSelect optionSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        optionSelect = OptionSelect.getInstance();
        setUpOkButton();
        createRadioButton();
    }

    private void setUpOkButton() {
        Button button = (Button) findViewById(R.id.okBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MenuActivity.makeIntent(OptionActivity.this);
                startActivity(intent);

                finish();
            }
        });
    }

    private void createRadioButton() {
        // Size group
        RadioGroup sizeGroup = (RadioGroup) findViewById(R.id.radio_group_size);

        int[] size = getResources().getIntArray(R.array.board_size);
        // Create the button
        for (int i = 0; i < size.length-1; i = i+2){
            final int sizeRow = size[i];
            final int sizeCol = size[i+1];

            RadioButton button = new RadioButton(this);
            button.setText(sizeRow + " rows by " + sizeCol +" columns");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    optionSelect.setRow(sizeRow);
                    optionSelect.setCol(sizeCol);
                }
            });

            // Add to radio group
            sizeGroup.addView((button));
        }

        // Mine group
        RadioGroup mineGroup = (RadioGroup) findViewById(R.id.radio_group_mine);

        int[] mine = getResources().getIntArray(R.array.num_mine);
        // Create the button
        for (int i = 0; i < mine.length; i++) {
            final int num = mine[i];
            RadioButton button = new RadioButton(this);
            button.setText(num +" mines");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    optionSelect.setMines(num);
                }
            });

            // Add to radio group
            mineGroup.addView((button));
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, OptionActivity.class);
    }
}
