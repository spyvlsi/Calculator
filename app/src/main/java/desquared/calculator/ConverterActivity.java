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

public class ConverterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txt_amount;
    Button converBtn;
    Spinner spinnerTo;
    float amount;
    String toSpnValue;
    EditText editTextResult;
    private PlaceHolderApi placeHolderApi;
    final String api_key = "d628c113e9c378a58d31982c03e19a6b";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        txt_amount = findViewById(R.id.amount_field);
        spinnerTo = findViewById(R.id.to_spinner);
        converBtn = findViewById(R.id.convBtn);
        editTextResult = findViewById(R.id.restxt2);


        converBtn.setOnClickListener(new View.OnClickListener() {

            @Nullable
            @Override
            public void onClick(View v) {

                if (txt_amount.getText().toString().isEmpty()) {
                    createAlertDialog("Amount can't be empty", "Please enter Amount");
                } else if (spinnerTo.getSelectedItemPosition() == 0) {
                    createAlertDialog("Convert currency can't be empty", "Choose a currency to which you want to convert");
                } else {
                    amount = Float.parseFloat(txt_amount.getText() + "");
//                    editTextResult.setText(amount + "");
//                    createConversion();
//                    getResult();
                }

//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://data.fixer.io/api/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                PlaceHolderApi placeHolderApi = retrofit.create(PlaceHolderApi.class);
//                Call<List<ConvertApiResponse>> call = placeHolderApi.get.Result();
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
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
