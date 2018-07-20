package id.uucp.krsmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuLogin extends AppCompatActivity {

    private Button dosen;
    private Button mhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login);

        dosen = (Button) findViewById(R.id.btn_dosen);
        mhs = (Button) findViewById(R.id.btn_mhs);

        dosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuLogin.this, Login.class));
                finish();
            }
        });

        mhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuLogin.this, LoginMhs.class));
                finish();
            }
        });
    }
}
