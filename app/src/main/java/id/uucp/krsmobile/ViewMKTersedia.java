package id.uucp.krsmobile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewMKTersedia extends AppCompatActivity{

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Matkul> daftarMatkul;

    public ViewMKTersedia() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mktersedia);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("matkul").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                daftarMatkul = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    Matkul matkul = noteDataSnapshot.getValue(Matkul.class);
                    matkul.setKey(noteDataSnapshot.getKey());

                    daftarMatkul.add(matkul);
                }

                adapter = new AdapterMhsRecycleView(daftarMatkul, ViewMKTersedia.this);
                rvView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, ViewMKTersedia.class);
    }
}
