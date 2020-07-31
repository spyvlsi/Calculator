package desquared.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConverterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txt_amount;
    Button converBtn;
    Spinner spinnerFrom;
    Spinner spinnerTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        txt_amount = findViewById(R.id.ammount_field);
        spinnerFrom = findViewById(R.id.from_spinner);
        spinnerTo = findViewById(R.id.to_spinner);
        converBtn = findViewById(R.id.convBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerFrom.setOnItemSelectedListener(this);


        converBtn.setOnClickListener(new View.OnClickListener() {
            float amount;

            @Nullable
            @Override
            public void onClick(View v) {

                if (txt_amount.getText().toString().isEmpty()) {
                    createAlertDialog("Amount can't be empty", "Please enter Amount");
                } else if (spinnerFrom.getSelectedItemPosition() == 0) {
                    createAlertDialog("Base currency can't be empty", "Choose a currency from which you wan to convert");
                } else if (spinnerTo.getSelectedItemPosition() == 0) {
                    createAlertDialog("Convert currency can't be empty", "Choose a currency to which you want to convert");
                } else {
                    amount = Float.parseFloat(txt_amount.getText() + "");
                }

//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://data.fixer.io/api/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                PlaceHolderApi placeHolderApi = retrofit.create(PlaceHolderApi.class);
//                Call<List<ConvertApiResponse>> call = placeHolderApi.getCurrencies();
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

        ArrayAdapter<CharSequence> adapterFrom = ArrayAdapter.createFromResource(this,
                R.array.currencies, android.R.layout.simple_spinner_item);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerFrom.setOnItemSelectedListener(this);

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
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
