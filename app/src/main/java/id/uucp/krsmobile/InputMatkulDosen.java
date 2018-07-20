package id.uucp.krsmobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import id.uucp.krsmobile.Matkul;

public class InputMatkulDosen extends AppCompatActivity {

    private DatabaseReference database;

    private Button submit;
    private EditText kodeMatkul;
    private EditText namaMatkul;
    private EditText jmlSks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_matkul_dosen);

        kodeMatkul = (EditText) findViewById(R.id.kodeMatkul);
        namaMatkul = (EditText) findViewById(R.id.namaMatkul);
        jmlSks = (EditText) findViewById(R.id.jmlSks);
        submit = (Button) findViewById(R.id.btn_submit);

        //mengambil reference ke DB firebase
        database = FirebaseDatabase.getInstance().getReference();

        final Matkul matkul = (Matkul) getIntent().getSerializableExtra("data");

        if (matkul != null){
            kodeMatkul.setText(matkul.getKodeMatkul());
            namaMatkul.setText(matkul.getNamaMatkul());
            jmlSks.setText(matkul.getJmlSks());
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    matkul.setKodeMatkul(kodeMatkul.getText().toString());
                    matkul.setNamaMatkul(kodeMatkul.getText().toString());
                    matkul.setJmlSks(kodeMatkul.getText().toString());

                    updateMatkul(matkul);
                }
            });
        } else {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isEmpty(kodeMatkul.getText().toString()) && !isEmpty(namaMatkul.getText().toString()) && !isEmpty(jmlSks.getText().toString()))
                        submitMatkul(new Matkul(kodeMatkul.getText().toString(), namaMatkul.getText().toString(), jmlSks.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.btn_submit), "Data mata kuliah tidak boleh kososng", Snackbar.LENGTH_LONG).show();
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(kodeMatkul.getWindowToken(), 0);
                }
            });
        }
    }
    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }


    private void updateMatkul(Matkul matkul){
        database.child("matkul")
                .child(matkul.getKey())
                .setValue(matkul)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Snackbar.make(findViewById(R.id.btn_submit), "Data berhasil dirubah", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void submitMatkul(Matkul matkul){
        database.child("matkul").push().setValue(matkul).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                kodeMatkul.setText("");
                namaMatkul.setText("");
                jmlSks.setText("");
                Snackbar.make(findViewById(R.id.btn_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, InputMatkulDosen.class);
    }
}
