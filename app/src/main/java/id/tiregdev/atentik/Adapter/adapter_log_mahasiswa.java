package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.tiregdev.atentik.Model.object_log_mahasiswa;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 14/03/18.
 */

public class adapter_log_mahasiswa extends RecyclerView.Adapter<adapter_log_mahasiswa.holder_log_mahasiswa> {

    private List<object_log_mahasiswa> itemList;
    private Context context;

    public adapter_log_mahasiswa(Context context, List<object_log_mahasiswa> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_log_mahasiswa onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_log_mahasiswa,null);
        holder_log_mahasiswa hn = new holder_log_mahasiswa(layoutView);
        return hn;
    }

    @Override
    public void onBindViewHolder(holder_log_mahasiswa holder, int position){
        holder.nama.setText(itemList.get(position).getNama());
        holder.nim.setText(itemList.get(position).getNim());
        holder.jamHadir.setText(itemList.get(position).getJam());
        holder.telat.setText(itemList.get(position).getTelat());
        holder.kompen.setText(itemList.get(position).getKompen());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_log_mahasiswa extends RecyclerView.ViewHolder {
        public TextView nama, nim, jamHadir, telat, kompen;

        public holder_log_mahasiswa(View itemView){
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            nim = itemView.findViewById(R.id.nim);
            jamHadir = itemView.findViewById(R.id.jam);
            telat = itemView.findViewById(R.id.telat);
            kompen = itemView.findViewById(R.id.kompen);
        }
    }

}