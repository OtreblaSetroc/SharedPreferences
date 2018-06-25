package com.example.sergi.sharedpreferences.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.sergi.sharedpreferences.APP.SharedPrefer;
import com.example.sergi.sharedpreferences.R;

public class MainActivity extends AppCompatActivity {
    private EditText correo;
    private  EditText pass;
    private Switch remenber;
    private Button enviar;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binddUI();

        preferences =getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setEdits();

        enviar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             String  email =correo.getText().toString();
             String pas=pass.getText().toString();
               if (login(email,pas)){
                   onSavePreferences(email,pas);
                   goTO();

               }
           }
       });
    }
    public  void onSavePreferences(String email,String pass){
        if (remenber.isChecked()){
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("email",email);
            editor.putString("pass",pass);
            editor.commit();
        }

    }
    private void setEdits(){
        String email= SharedPrefer.getUsername(preferences);
        String pass = SharedPrefer.getPass(preferences);
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            this.correo.setText(email);
            this.pass.setText(pass);
        }

    }

    public void binddUI(){
        correo=findViewById(R.id.Correo);
        pass=findViewById(R.id.Pass);
        remenber=findViewById(R.id.switch1);
        enviar=findViewById(R.id.Enviar);

    }

    public  boolean isEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();


    }
    public boolean isPassword(String pass){
        return pass.length()>4;
    }

    public boolean login(String email, String pass){
        if (!isEmail(email)){
            Toast.makeText(this,"Introduce un correo valido1",Toast.LENGTH_SHORT).show();
        }else if (!isPassword(pass)){
            Toast.makeText(this,"Introduce una contraseña mayor a 4",Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
      return false;
    }
    public  void  goTO(){
        Intent intent = new Intent(this,Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }


}
