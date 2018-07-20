package id.uucp.krsmobile;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewMatkulMhs extends AppCompatActivity implements AdapterRecycleView.FirebaseDataListener{

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Matkul> daftarMatkul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_matkul_mhs);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("matkul").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                daftarMatkul = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    Matkul matkul = noteDataSnapshot.getValue(Matkul.class);
                    matkul.setKey(noteDataSnapshot.getKey());

                    daftarMatkul.add(matkul);
                }

                adapter = new AdapterRecycleView(daftarMatkul, ViewMatkulMhs.this);
                rvView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, ViewMatkulMhs.class);
    }
    @Override
    public void onDeleteData(Matkul matkul, final int position){
        if(database!=null){
            database.child("matkul").child(matkul.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(ViewMatkulMhs.this,"success delete", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
