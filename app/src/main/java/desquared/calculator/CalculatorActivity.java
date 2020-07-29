package desquared.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn_add, btn_sub, btn_div,
            btn_mul, btnDot, btnC, btn_res;

    EditText editText;

    double num1, num2;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn_add = findViewById(R.id.add_btn);
        btn_sub = findViewById(R.id.sub_btn);
        btn_mul = findViewById(R.id.mul_btn);
        btn_div = findViewById(R.id.div_btn);
        btnDot = findViewById(R.id.btn_dot);
        btnC = findViewById(R.id.c_btn);
        btn_res = findViewById(R.id.res_btn);
        editText = findViewById(R.id.txt);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "9");
            }
        });


        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + ".");
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText == null) {
                    editText.setText("");
                } else {
                    num1 = Double.parseDouble(editText.getText() + "");
                    editText.setText(null);
                    flag = 1;
                }
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText == null) {
                    editText.setText("");
                } else {
                    num1 = Double.parseDouble(editText.getText() + "");
                    editText.setText(null);
                    flag = 2;
                }
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText == null) {
                    editText.setText("");
                } else {
                    num1 = Double.parseDouble(editText.getText() + "");
                    editText.setText(null);
                    flag = 3;
                }

            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText == null) {
                    editText.setText("");
                } else {
                    num1 = Double.parseDouble(editText.getText() + "");
                    editText.setText(null);
                    flag = 4;
                }

            }
        });

        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText == null) {
                    editText.setText("");
                } else {
                    num2 = Double.parseDouble(editText.getText() + "");

                    switch (flag) {

                        case (1):
                            editText.setText(num1 + num2 + "");
                            flag = 0;
                            break;
                        case (2):
                            editText.setText(num1 - num2 + "");
                            flag = 0;
                            break;
                        case (3):
                            editText.setText(num1 * num2 + "");
                            flag = 0;
                            break;
                        case (4):
                            editText.setText(num1 / num2 + "");
                            flag = 0;
                            break;

                        default:
                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(CalculatorActivity.this);
                            dlgAlert.setMessage("ERROR");
                            dlgAlert.setTitle("Error Message...");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.show();
                    }
                }


            }
        });


    }

}
