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

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConverterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txt_amount;
    Button convertBtn;
    Spinner spinnerTo;
    float amount;
    String toSpnValue;
    TextView textResult;
    double final_toRate;
    double final_result;
    private static final String BASE_URL = "http://data.fixer.io/api/";
    private static final String API_KEY = "d628c113e9c378a58d31982c03e19a6b";
    private static final String IMG = "https://www.thomascook.in/images/currency-img.jpg";


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


        convertBtn.setOnClickListener(new View.OnClickListener() {

            @Nullable
            @Override
            public void onClick(View v) {

                if (txt_amount.getText().toString().isEmpty()) {
                    createAlertDialog("Amount can't be empty", "Please enter Amount");
                } else if (spinnerTo.getSelectedItemPosition() == 0) {
                    createAlertDialog("Convert currency can't be empty", "Choose a currency to which you want to convert");
                } else {

//                    Thread.dumpStack();

                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(loggingInterceptor)
                            .build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build();
                    PlaceHolderApi placeHolderApi = retrofit.create(PlaceHolderApi.class);
                    Call<JsonLatestModel> call = placeHolderApi.getLatest(API_KEY);

                    call.enqueue(new Callback<JsonLatestModel>() {
                        @Override
                        public void onResponse(Call<JsonLatestModel> call, Response<JsonLatestModel> response) {
                            if (!response.isSuccessful()) {
                                Log.d("RESPONSE", "NOT OK");
                                //textResult.setText("Code: " + response.code());
                            }
                            Log.i("RESPONSE", "OK");
                            JsonLatestModel jsonLatestModel = response.body();

//                                Field fields[] = JsonRatesModel.class.getDeclaredFields();  //array creation with the names of the fields of RatesResponseApi.class
//                                List<String> currencies = new ArrayList<String>();
//                                List<Double> rates = new ArrayList<Double>();
//
//                                int index = 0;
//                                double d_help;
//                                String str_help;
//                                Object value;
//                                for (Field field : fields) {        //instert
//                                    currencies.add(index, field.getName().toUpperCase()); //array fill with the names
//                                    try {
//                                        value = field.get(jsonLatestModel.rates);
//                                        str_help = value.toString();
//                                        d_help = Double.valueOf(str_help).doubleValue();
//                                        rates.add(index, d_help);  //array fills with the rates
//                                    } catch (IllegalAccessException e) {
//                                        e.printStackTrace();
//                                    }
//                                    index++;
//                                }
//                                HashMap<String, Double> map = new HashMap<>();  //hashmap creation with the currency names and their values
//                                for (int i = 0; i < currencies.size(); i++) {
//                                    map.put(currencies.get(i), rates.get(i));
//                                }
//
//                                final_toRate = map.get(toSpnValue);
//                                amount = Float.parseFloat(txt_amount.getText() + "");
//                                final_result = final_toRate * amount;
//                                textResult.setText(String.valueOf(final_result));

                        }

                        @Override
                        public void onFailure(Call<JsonLatestModel> call, Throwable t) {
                            textResult.setText(t.getMessage());

                        }
                    });
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
        toSpnValue = spinnerTo.getSelectedItem().toString();

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
        String textTo = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
