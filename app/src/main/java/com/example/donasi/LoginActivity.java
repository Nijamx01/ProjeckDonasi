package com.example.donasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;

    private EditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edt_email_login);
        edtPassword = findViewById(R.id.edt_pass);

        // Initialize buttons
        btnLogin = findViewById(R.id.btn_login);
        btnRegister =  findViewById(R.id.btn_regis);

        // Set click listeners
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email = edtEmail.getText().toString();
               String password =  edtPassword.getText().toString();

               if (!(email.isEmpty() || password.isEmpty())) {
                   RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                   StringRequest request = new StringRequest(Request.Method.GET, Db_Helper.urlLogin
                           + "?email=" + email + "&password=" + password, new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           if (response.equals("Selamat Datang")) {
                               Toast.makeText(getApplicationContext(),"Login Berhasil",Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(getApplicationContext(),MainActivity.class));
                           }else {
                               Toast.makeText(getApplicationContext(),"Login Gagal",Toast.LENGTH_SHORT).show();
                           }
                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                       }
                   });
               }
               else {
                   Toast.makeText(getApplicationContext(),"Email atau Password Salah", Toast.LENGTH_SHORT).show();

               }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn_register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(btn_register);
            }
        });

        // Set window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}