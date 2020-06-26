package com.example.myspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner mCountrySp;
    private Spinner mCitySp;
    private Spinner mNumHouseSp;
    private Button mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initBtnOk();
    }
    private void initBtnOk(){
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Адрес доставки: " +
                        mCountrySp.getSelectedItem().toString()+ ", г. "+
                        mCitySp.getSelectedItem().toString()+ ", д. " +
                        mNumHouseSp.getSelectedItem(), Toast.LENGTH_LONG).show();

            }
        });

    }
    private void initViews() {
        mCountrySp = findViewById(R.id.spCountry);
        mCitySp = findViewById(R.id.spCity);
        mNumHouseSp = findViewById(R.id.spNamHouse);
        mBtnOk = findViewById(R.id.btnOk);
        initSpCountry();
        initSpNumHouse();
    }
    private void initSpCountry(){
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.country, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountrySp.setAdapter(adapterCountries);
        // определяем выбор страны
        mCountrySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] country = getResources().getStringArray(R.array.country);
                initSpCity(country[i]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
    private void initSpCity(String country) {
        ArrayAdapter<CharSequence> adapter = null;
        switch (country) {
            case "Россия":
                adapter = ArrayAdapter.createFromResource(this, R.array.ru_city, android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapter = ArrayAdapter.createFromResource(this, R.array.ua_city, android.R.layout.simple_spinner_item);
                break;
            case "Белоруссия":
                adapter = ArrayAdapter.createFromResource(this, R.array.bu_city, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mCitySp.setAdapter(adapter);
        }
    }
    private void initSpNumHouse(){
        Integer [] numHouse = new Integer[50];
        for (int i=1; i<=50; i++)
            numHouse[i-1]=i;
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, numHouse);
        mNumHouseSp.setAdapter(adapter);
    }
}