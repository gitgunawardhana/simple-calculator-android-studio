package simple.cal.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultView,solutionView;
    MaterialButton buttonRem;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       resultView = findViewById(R.id.result_tv);
       solutionView = findViewById(R.id.solution_tv);

       assignId(buttonRem,R.id.button_rem);
       assignId(buttonDivide,R.id.button_divide);
       assignId(buttonMultiply,R.id.button_multiply);
       assignId(buttonPlus,R.id.button_plus);
       assignId(buttonMinus,R.id.button_minus);
       assignId(buttonEquals,R.id.button_equals);
       assignId(button0,R.id.button_0);
       assignId(button1,R.id.button_1);
       assignId(button2,R.id.button_2);
       assignId(button3,R.id.button_3);
       assignId(button4,R.id.button_4);
       assignId(button5,R.id.button_5);
       assignId(button6,R.id.button_6);
       assignId(button7,R.id.button_7);
       assignId(button8,R.id.button_8);
       assignId(button9,R.id.button_9);
       assignId(buttonAC,R.id.button_ac);
       assignId(buttonDot,R.id.button_dot);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataForCalculate = solutionView.getText().toString();

        if(buttonText.equals("DEL")){
            solutionView.setText("");
            resultView.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionView.setText(resultView.getText());
            return;
        }else{
            dataForCalculate = dataForCalculate+buttonText;
        }
        solutionView.setText(dataForCalculate);

        String sumResult = getResult(dataForCalculate);

        if(!sumResult.equals("Err")){
            resultView.setText(sumResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String sumResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(sumResult.endsWith(".0")){
                sumResult = sumResult.replace(".0","");
            }
            return sumResult;
        }catch (Exception e){
            return "Err";
        }
    }

}