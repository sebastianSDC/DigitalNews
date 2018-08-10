package com.example.fcauserano.digitalnews.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fcauserano.digitalnews.R;
import com.example.fcauserano.digitalnews.model.POJO.Mensaje;
import com.example.fcauserano.digitalnews.views.adapters.ChatAdapter;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChatActivity extends AppCompatActivity {


    private EditText edtMensaje;
    private RecyclerView recyclerMensajes;
    private Button btnEnviar;
    private TextView mensajeBienvenida;
    private FirebaseUser user;
    private Mensaje mensaje;
    private Button botonLogout;

    private ChatAdapter chatAdapter;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mensaje = new Mensaje();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mensaje.setAutor(user.getEmail());
        edtMensaje = findViewById(R.id.txt_mensaje_chat);
        recyclerMensajes = findViewById(R.id.rv_mensajes);
        btnEnviar = findViewById(R.id.btn_enviar);
        mensajeBienvenida = findViewById(R.id.mensaje_bienvenida);
        mensajeBienvenida.setText("Bienvenido " + user.getEmail());

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");

        chatAdapter = new ChatAdapter(this);
        recyclerMensajes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerMensajes.setAdapter(chatAdapter);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm");
                String fecha = dateFormat.format(Calendar.getInstance().getTime());
                mensaje.setMensaje(edtMensaje.getText().toString());
                mensaje.setFecha(fecha);
                databaseReference.push().setValue(mensaje);
                edtMensaje.setText("");
                recyclerMensajes.scrollToPosition(chatAdapter.getItemCount() - 1);
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Mensaje mensajeNuevo = dataSnapshot.getValue(Mensaje.class);
                chatAdapter.agregarMensaje(mensajeNuevo);
                recyclerMensajes.scrollToPosition(chatAdapter.getItemCount() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        botonLogout = findViewById(R.id.boton_logout);

        botonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        //si es que esta logueado con facebook, tengo que desloguearlo
                        LoginManager.getInstance().logOut();
                    }
                    //esto es para desloguearlo de firebase, ya se que entro con facebok o nativo
                    FirebaseAuth.getInstance().signOut();
                    Intent intent= new Intent(ChatActivity.this,HomeActivity.class);
                    startActivity(intent);
                    onBackPressed();
                }
            }
        });

    }
}


