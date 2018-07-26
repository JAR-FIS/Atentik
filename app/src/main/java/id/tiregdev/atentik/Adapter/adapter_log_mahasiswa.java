package id.tiregdev.atentik.Adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.tiregdev.atentik.Activity.CekToken;
import id.tiregdev.atentik.AtentikClient;
import id.tiregdev.atentik.Model.object_log_mahasiswa;
import id.tiregdev.atentik.R;
import id.tiregdev.atentik.Util.AtentikHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HVS on 14/03/18.
 */

public class adapter_log_mahasiswa extends RecyclerView.Adapter<adapter_log_mahasiswa.holder_log_mahasiswa> {

    private List<object_log_mahasiswa> itemList;
    private Context context;
    String tokens, pilihans;

    public adapter_log_mahasiswa(Context context, List<object_log_mahasiswa> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public holder_log_mahasiswa onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_log_mahasiswa,null);
        holder_log_mahasiswa hn = new holder_log_mahasiswa(layoutView);
        CekToken ct = new CekToken();
        tokens = ct.Cek(layoutView.getContext());

        RadioButton terlambat = layoutView.findViewById(R.id.RBtelat);
        terlambat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LayoutInflater factory = LayoutInflater.from(context);
                final View exitDialogView = factory.inflate(R.layout.dialog_input_jam_telat, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(context).create();

                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Toast.makeText(context, "save sukses", Toast.LENGTH_SHORT).show();
                    }
                });

                exitDialogView.findViewById(R.id.tidak).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                    }
                });

                exitDialog.show();
            }
        });

        return hn;
    }

    @Override
    public void onBindViewHolder(holder_log_mahasiswa holder, int position){
        holder.nama.setText(itemList.get(position).getNama());
        holder.nim.setText(itemList.get(position).getNim());
        holder.jamHadir.setText(itemList.get(position).getJam_hadir());
        holder.telat.setText(itemList.get(position).getWaktu_telat());
        holder.kompen.setText(itemList.get(position).getKompen());
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_log_mahasiswa extends RecyclerView.ViewHolder {
        public TextView nama, nim, jamHadir, telat, kompen;
        public Button btnSet;
        public RadioButton rb1, rb2;
        public RadioGroup RG;

        public holder_log_mahasiswa(final View itemView){
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            nim = itemView.findViewById(R.id.nim);
            jamHadir = itemView.findViewById(R.id.jam);
            telat = itemView.findViewById(R.id.telat);
            kompen = itemView.findViewById(R.id.kompen);
            btnSet = itemView.findViewById(R.id.set);
            rb1 = itemView.findViewById(R.id.RBtepatWaktu);
            rb2 = itemView.findViewById(R.id.RBtidakMasuk);
            RG = itemView.findViewById(R.id.RG);

            btnSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (rb1.isChecked()) {
                        pilihans = "1";
                    } else if (rb2.isChecked()) {
                        pilihans = "3";
                    }
                    Toast.makeText(v.getContext(), pilihans + ", " + nim.getText().toString() +", " + telat.getText().toString(), Toast.LENGTH_SHORT).show();
                    AtentikClient client = AtentikHelper.getClient().create(AtentikClient.class);
                    Call<object_log_mahasiswa> call = client.editLogAbsenMahasiswa("Bearer " + tokens, pilihans, nim.getText().toString(), Integer.parseInt(telat.getText().toString()));
                    call.enqueue(new Callback<object_log_mahasiswa>() {
                        @Override
                        public void onResponse(Call<object_log_mahasiswa> call, Response<object_log_mahasiswa> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(v.getContext(), "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(v.getContext(), response.raw().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<object_log_mahasiswa> call, Throwable t) {
                            Toast.makeText(v.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
//                    Toast.makeText(v.getContext(), "Fitur ini akan saya sempurnakan, saya janji!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
