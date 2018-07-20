package id.uucp.krsmobile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterRecycleView extends RecyclerView.Adapter<AdapterRecycleView.ViewHolder> {
    private ArrayList<Matkul> daftarMatkul;
    private Context context;
    FirebaseDataListener listener;

    public AdapterRecycleView(ArrayList<Matkul> matkuls, Context ctx){
        daftarMatkul = matkuls;
        context = ctx;
        listener = (ViewMatkulDosen)ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        //CardView cvMain;

        ViewHolder(View v){
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namamatkul);
            //cvMain = (CardView) v.findViewById(R.id.cv_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matkul, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        final String name = daftarMatkul.get(position).getNamaMatkul();
        System.out.println("Mata Kuliah satu per satu "+position+daftarMatkul.size());
        holder.tvTitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                context.startActivity(ViewSingleActivity.getActIntent((Activity) context).putExtra("data", daftarMatkul.get(position)));
            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);

                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                context.startActivity(InputMatkulDosen.getActIntent((Activity) context).putExtra("data", daftarMatkul.get(position)));

                            }
                        }
                );
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                listener.onDeleteData(daftarMatkul.get(position), position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount(){
        return daftarMatkul.size();
    }

    public interface FirebaseDataListener{
        void onDeleteData(Matkul matkul, int position);
    }
}
