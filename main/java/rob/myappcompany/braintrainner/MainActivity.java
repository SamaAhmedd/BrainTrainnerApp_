package rob.myappcompany.braintrainner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton , tryAgainButton;
    TextView score , timer , equ  , done, ans1Text , ans2Text , ans3Text , ans4Text , right, wrong;
    Random rand;
    int firstNum , secondNum , ans1 , ans2 , ans3 , rightAns , rightAnsCounter , totalAnsCounter;
    CountDownTimer countDownTimer;


    public void start(View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        score.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        equ.setVisibility(View.VISIBLE);
        ans1Text.setVisibility(View.VISIBLE);
        ans2Text.setVisibility(View.VISIBLE);
        ans3Text.setVisibility(View.VISIBLE);
        ans4Text.setVisibility(View.VISIBLE);

        setNewGame(goButton);
    }

    public void setNewGame(View view)
    {
        score.setText("0/0");
        rightAnsCounter = 0 ;
        totalAnsCounter = 0;
        tryAgainButton.setVisibility(View.INVISIBLE);
        done.setVisibility(View.INVISIBLE);
        ans1Text.setEnabled(true);
        ans2Text.setEnabled(true);
        ans3Text.setEnabled(true);
        ans4Text.setEnabled(true);

        countDownTimer = new CountDownTimer(30000 , 1000){
            public void onTick(long l){
                timer.setText(Integer.toString((int)l/1000) + "s");
            }

            public void onFinish(){
                tryAgainButton.setVisibility(View.VISIBLE);
                done.setVisibility(View.VISIBLE);
                wrong.setVisibility(View.INVISIBLE);
                right.setVisibility(View.INVISIBLE);
                ans1Text.setEnabled(false);
                ans2Text.setEnabled(false);
                ans3Text.setEnabled(false);
                ans4Text.setEnabled(false);
            }
        }.start();

       generateRandomEquation();
       generateRandomAnswers();
    }

    public boolean ans1Pressed(View view)
    {
        return true;
    }

    public void chooseAnswer(View view)
    {
       String rightTag= "";
        int a1 = Integer.valueOf((String) ans1Text.getText());
        int a2 = Integer.valueOf((String) ans2Text.getText());
        int a3 = Integer.valueOf((String) ans3Text.getText());
       int a4 = Integer.valueOf((String) ans4Text.getText());

        if(a1==rightAns)
            rightTag= ans1Text.getTag().toString();
        else if(a2==rightAns)
            rightTag= ans2Text.getTag().toString();
        else if(a3==rightAns)
            rightTag= ans3Text.getTag().toString();
        else if(a4==rightAns)
            rightTag= ans4Text.getTag().toString();


       if(rightTag.equals(view.getTag().toString()))
       {
           rightAnsCounter++;
           wrong.setVisibility(View.INVISIBLE);
           right.setVisibility(View.VISIBLE);
       }
       else {
           right.setVisibility(View.INVISIBLE);
           wrong.setVisibility(View.VISIBLE);
       }

       totalAnsCounter++;
       score.setText(Integer.toString(rightAnsCounter) + "/" + Integer.toString(totalAnsCounter));
       generateRandomEquation();
       generateRandomAnswers();
    }
    public void generateRandomEquation() {

          int r = rand.nextInt(4);
          firstNum = rand.nextInt(10)+1;
          secondNum = rand.nextInt(10)+1;

          if(r==0) {
              equ.setText(Integer.toString(firstNum) + "+" + Integer.toString(secondNum));
              rightAns = firstNum+secondNum;
          }
          else if(r==1) {
              equ.setText(Integer.toString(firstNum) + "-" + Integer.toString(secondNum));
              rightAns = firstNum-secondNum;
          }
          else if(r==2) {
              equ.setText(Integer.toString(firstNum) + "*" + Integer.toString(secondNum));
              rightAns = firstNum*secondNum;
          }
          else if(r==3) {
              equ.setText(Integer.toString(firstNum) + "/" + Integer.toString(secondNum));
              rightAns = (int)firstNum/secondNum;
          }

    }

    public void generateRandomAnswers() {

        int r = rand.nextInt(4);

        ans1 = rand.nextInt(20) + 1;
        ans2 = rand.nextInt(20) + 1;
        ans3 = rand.nextInt(20) + 1;

        while(ans1==rightAns || ans2==rightAns || ans3== rightAns) {
            ans1 = rand.nextInt(20) + 1;
            ans2 = rand.nextInt(20) + 1;
            ans3 = rand.nextInt(20) + 1;
        }

        if(r==0)
        {
            ans1Text.setText(Integer.toString(rightAns));
            ans2Text.setText(Integer.toString(ans1));
            ans3Text.setText(Integer.toString(ans3));
            ans4Text.setText(Integer.toString(ans2));
        }
        else if(r==1) {
            ans1Text.setText(Integer.toString(ans1));
            ans2Text.setText(Integer.toString(rightAns));
            ans3Text.setText(Integer.toString(ans3));
            ans4Text.setText(Integer.toString(ans2));
        }
        else if(r==2){
            ans1Text.setText(Integer.toString(ans1));
            ans2Text.setText(Integer.toString(ans2));
            ans3Text.setText(Integer.toString(rightAns));
            ans4Text.setText(Integer.toString(ans3));

        }
        else if(r==3){
            ans1Text.setText(Integer.toString(ans1));
            ans2Text.setText(Integer.toString(ans2));
            ans3Text.setText(Integer.toString(ans3));
            ans4Text.setText(Integer.toString(rightAns));

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        goButton = (Button) findViewById(R.id.startButton);
        tryAgainButton = (Button) findViewById(R.id.tryAgainButton);
        score = (TextView) findViewById(R.id.scoreText);
        timer = (TextView) findViewById(R.id.timerText);
        equ = (TextView) findViewById(R.id.equText);
        done = (TextView) findViewById(R.id.doneText);
        ans1Text = (TextView) findViewById(R.id.text1);
        ans2Text = (TextView) findViewById(R.id.text2);
        ans3Text = (TextView) findViewById(R.id.text3);
        ans4Text = (TextView) findViewById(R.id.text4);
        right  = (TextView) findViewById(R.id.correctText);
        wrong = (TextView) findViewById(R.id.wrongText);
        rand = new Random();

        goButton.setVisibility(View.VISIBLE);
        tryAgainButton.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        equ.setVisibility(View.INVISIBLE);
        done.setVisibility(View.INVISIBLE);
        right.setVisibility(View.INVISIBLE);
        wrong.setVisibility(View.INVISIBLE);
        ans1Text.setVisibility(View.INVISIBLE);
        ans2Text.setVisibility(View.INVISIBLE);
        ans3Text.setVisibility(View.INVISIBLE);
        ans4Text.setVisibility(View.INVISIBLE);

    }
}
