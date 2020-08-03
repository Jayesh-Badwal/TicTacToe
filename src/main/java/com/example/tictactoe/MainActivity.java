package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.graphics.Color.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean playerX = true;
    int count;
    Button btn[][] = new Button[3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                btn[i][j] = findViewById(resID);
                btn[i][j].setOnClickListener(this);
            }
        }

        final Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(!((Button) view).getText().toString().equals(""))
            return;

        if(playerX) {
            ((Button) view).setTextColor(YELLOW);
            ((Button) view).setText("X");
        } else {
            ((Button) view).setTextColor(GREEN);
            ((Button) view).setText("O");
        }

        count++;

        if(checkIfWon()) {
            disable(false);
            if(playerX)
                displayWinner("X");
            else
                displayWinner("O");
        } else if(count == 9)
            draw();
        else
            playerX = !playerX;
    }

    private boolean checkIfWon() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = btn[i][j].getText().toString();
            }
        }

        for(int i = 0; i < 3; i++) {
            if(field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && field[i][0] != "")
                return true;
        }

        for(int i = 0; i < 3; i++) {
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && field[0][i] != "")
                return true;
        }

        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && field[0][0] != "")
            return true;

        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && field[0][2] != "")
            return true;

        return false;
    }

    private void displayWinner(String s) {
        TextView status = findViewById(R.id.textView2);
        String str = s + " Won";
        status.setText(str);
    }

    private void draw() {
        TextView status = findViewById(R.id.textView2);
        status.setText("Draw!");
    }

    private void reset() {
        TextView status = findViewById(R.id.textView2);
        status.setText("");
        disable(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btn[i][j].setText("");
            }
        }
        count = 0;
        playerX = true;
    }

    private void disable(boolean check) {
        btn[0][0].setClickable(check);
        btn[0][1].setClickable(check);
        btn[0][2].setClickable(check);
        btn[1][0].setClickable(check);
        btn[1][1].setClickable(check);
        btn[1][2].setClickable(check);
        btn[2][0].setClickable(check);
        btn[2][1].setClickable(check);
        btn[2][2].setClickable(check);
    }

}