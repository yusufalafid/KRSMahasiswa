package id.uucp.krsmobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class DashMhs extends AppCompatActivity {

    private Button SignOut;
    private Button inputMatkul;
    private Button lihatMatkul;
    private Button lihatMkTersedia;

    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_mhs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null){
                    startActivity(new Intent(DashMhs.this, MenuLogin.class));;
                    finish();
                }
            }
        };

        inputMatkul = (Button) findViewById(R.id.input_matkul_mhs);
        inputMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashMhs.this, InputMKMhs.class));
                finish();
            }
        });

        /*lihatMatkul = (Button) findViewById(R.id.lihat_matkul);
        lihatMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashMhs.this, ViewMatkulMhs.class));
                finish();
            }
        });*/


        lihatMkTersedia = (Button) findViewById(R.id.btn_mktersedia);
        lihatMkTersedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashMhs.this, ViewMKTersedia.class));
                finish();
            }
        });

        SignOut = (Button) findViewById(R.id.sign_out);
        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashMhs.this, "Anda Telah Keluar..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashMhs.this, MenuLogin.class));
                SignOut();
            }
        });
    }
    public void SignOut(){
        auth.signOut();
    }
}
