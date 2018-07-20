package id.uucp.krsmobile;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterMhsRecycleView extends RecyclerView.Adapter<AdapterMhsRecycleView.ViewHolder>{
    private ArrayList<Matkul> daftarMatkulMhs;
    private Context context;

    public AdapterMhsRecycleView(ArrayList<Matkul> matkuls, Context ctx){
        daftarMatkulMhs = matkuls;
        context = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;

        ViewHolder(View v){
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namamatkul);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matkul, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = daftarMatkulMhs.get(position).getNamaMatkul();
        System.out.println("Detail Mata Kuliah "+position+daftarMatkulMhs.size());
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(ViewSingleActivity.getActIntent((Activity) context).putExtra("data", daftarMatkulMhs.get(position)));
            }
        });
        /*holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });*/
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount(){
        return daftarMatkulMhs.size();
    }

    public interface FirebaseDataListener{
        void onDeleteData(Matkul matkul, int position);
    }
}
