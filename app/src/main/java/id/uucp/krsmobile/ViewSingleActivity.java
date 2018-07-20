package id.uucp.krsmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewSingleActivity extends AppCompatActivity {
    private Button submit;
    private EditText kodeMatkul;
    private EditText namaMatkul;
    private EditText jmlSks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_matkul_dosen);
        kodeMatkul = (EditText) findViewById(R.id.kodeMatkul);
        namaMatkul = (EditText) findViewById(R.id.namaMatkul);
        jmlSks = (EditText) findViewById(R.id.jmlSks);
        submit = (Button) findViewById(R.id.btn_submit);

        kodeMatkul.setEnabled(false);
        namaMatkul.setEnabled(false);
        jmlSks.setEnabled(false);
        submit.setVisibility(View.GONE);

        Matkul matkul = (Matkul) getIntent().getSerializableExtra("data");
        if (matkul!=null){
            kodeMatkul.setText(matkul.getKodeMatkul());
            namaMatkul.setText(matkul.getNamaMatkul());
            jmlSks.setText(matkul.getJmlSks());
        }
    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, ViewSingleActivity.class);
    }
}
