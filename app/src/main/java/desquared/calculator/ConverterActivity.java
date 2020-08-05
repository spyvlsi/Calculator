package desquared.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;


public class ConverterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txt_amount;
    Button convertBtn;
    Spinner spinnerTo;
    TextView textResult;
    String textTo;
    float amount;
    double final_toRate;
    double final_result;
    private static final String IMG = "https://www.thomascook.in/images/currency-img.jpg";
    private static final String VOLLEY_URL = "http://data.fixer.io/api/latest?access_key=d628c113e9c378a58d31982c03e19a6b";
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        Glide.with(this).load(IMG).into(imageView);

        txt_amount = findViewById(R.id.amount_field);
        spinnerTo = findViewById(R.id.to_spinner);
        convertBtn = findViewById(R.id.convBtn);
        textResult = findViewById(R.id.restxt2);

        mQueue = Volley.newRequestQueue(this);
        convertBtn.setOnClickListener(new View.OnClickListener() {

            @Nullable
            @Override
            public void onClick(View v) {

                if (txt_amount.getText().toString().isEmpty()) {
                    createAlertDialog("Amount can't be empty", "Please enter Amount");
                } else if (spinnerTo.getSelectedItemPosition() == 0) {
                    createAlertDialog("Convert currency can't be empty", "Choose a currency to which you want to convert");
                } else {

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, VOLLEY_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    JsonLatestModel jsonLatestModel = new Gson().fromJson(response, JsonLatestModel.class);
                                    Log.i("RESPONSE", response);
                                    final_toRate = jsonLatestModel.rates.get(textTo);
                                    amount = Float.parseFloat(txt_amount.getText() + "");
                                    final_result = final_toRate * amount;
                                    textResult.setText("The result is: " + String.valueOf(final_result) + " " + textTo);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textResult.setText("That didn't work! Please check your Internet connection");
                            error.printStackTrace();
                        }
                    });
                    mQueue.add(stringRequest);
                }
            }

        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.page_converter);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.page_calculator):
                        startActivity(new Intent(getApplicationContext(), CalculatorActivity.class));
                        return true;
                    case (R.id.page_converter):
                        return true;
                }
                return false;
            }
        });


        ArrayAdapter<CharSequence> adapterTo = ArrayAdapter.createFromResource(this,
                R.array.currencies, android.R.layout.simple_spinner_item);
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTo.setAdapter(adapterTo);
        spinnerTo.setOnItemSelectedListener(this);
    }

    private void createAlertDialog(String title, String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ConverterActivity.this);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        textTo = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
