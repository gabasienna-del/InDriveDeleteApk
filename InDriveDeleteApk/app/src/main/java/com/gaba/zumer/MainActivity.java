package com.gaba.zumer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerCountry; EditText editPhone; Button btnGetCode;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCountry = findViewById(R.id.spinner_country);
        editPhone = findViewById(R.id.edit_phone);
        btnGetCode = findViewById(R.id.btn_get_code);

        List<Country> countries = new ArrayList<>();
        countries.add(new Country("🇰🇿", "+7"));
        countries.add(new Country("🇷🇺", "+7"));
        countries.add(new Country("🇺🇸", "+1"));
        countries.add(new Country("🇹🇷", "+90"));
        countries.add(new Country("🇺🇿", "+998"));
        countries.add(new Country("🇰🇬", "+996"));
        countries.add(new Country("🇺🇦", "+380"));

        ArrayAdapter<Country> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);

        btnGetCode.setOnClickListener(v -> {
            Country c = (Country) spinnerCountry.getSelectedItem();
            String local = editPhone.getText() == null ? "" : editPhone.getText().toString().trim();
            String phone = (c != null ? c.dial : "") + local;
            if (local.length() < 4) {
                Toast.makeText(this, "Введите корректный номер", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
            intent.putExtra("sms_body", "Код на удаление аккаунта InDrive");
            try { startActivity(intent); }
            catch (Exception e) { Toast.makeText(this, "Нет приложения для SMS", Toast.LENGTH_SHORT).show(); }
        });
    }

    static class Country {
        String flag; String dial;
        Country(String f, String d) { flag=f; dial=d; }
        @Override public String toString(){ return flag + "  " + dial; }
    }
}
