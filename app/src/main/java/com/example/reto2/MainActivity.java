package com.example.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private EditText usernameET;
    private Button loginBtn;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameET = findViewById(R.id.usernameET);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(this::login);
    }

    private void login(View view) {
        String name = usernameET.getText().toString();
        User user = new User(UUID.randomUUID().toString(), name);

        Query query = db.collection("users").whereEqualTo("name", name);
        query.get().addOnCompleteListener(
                task-> {

                    //Si el usuario no existe crearlo e iniciar sesion con él
                    if (task.getResult().size() == 0) {
                        db.collection("users").document(user.getId()).set(user);
                        Intent intent = new Intent(this, AtraparActivity.class);
                        intent.putExtra("uuid", user.getId());
                        startActivity(intent);

                        //Si el usuario existe,descargar usuario e iniciar con él
                    } else {

                        User existingUser = null;
                        for(DocumentSnapshot doc : task.getResult()) {
                            existingUser = doc.toObject(User.class);
                            break;
                        }
                        if (existingUser.getName().equals(name)){
                            Intent intent = new Intent(this, AtraparActivity.class);
                            intent.putExtra("uuid", existingUser.getId());
                            startActivity(intent);
                        }
                    }


                }
        );

    }
}