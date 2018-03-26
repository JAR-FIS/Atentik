package id.tiregdev.atentik.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.tiregdev.atentik.Activity.home_2;
import id.tiregdev.atentik.Activity.statistik_view;
import id.tiregdev.atentik.Adapter.adapter_mhsw_dosen;
import id.tiregdev.atentik.Model.object_mhsw_dosen;
import id.tiregdev.atentik.R;

/**
 * Created by HVS on 13/03/18.
 */

public class data_dosen extends Fragment {

    RecyclerView rView;
    LinearLayoutManager  lLayout;
    View v;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_data_dosen, container, false);
        setupAdaptermhsw_dosen();
        setSearch();
        dialogOpsi();
        return v;
    }

    public void setSearch(){
        searchView = v.findViewById(R.id.search_bar);
        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.putih));
        searchEditText.setHintTextColor(getResources().getColor(R.color.background));
    }

    public void dialogOpsi() {
        ImageView more = v.findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LayoutInflater factory = LayoutInflater.from(getActivity());
                final View exitDialogView = factory.inflate(R.layout.dialog_more_dsn_mhsw, null);
                final AlertDialog exitDialog = new AlertDialog.Builder(getActivity()).create();

                exitDialog.setView(exitDialogView);
                exitDialogView.findViewById(R.id.filterKelas).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        dialogFilter();
                    }
                });

                exitDialogView.findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                        Toast.makeText(getActivity(), "Unduh data sukses", Toast.LENGTH_SHORT).show();
                    }
                });
                exitDialog.show();
            }
        });
    }

    public void dialogFilter(){
        final LayoutInflater factory = LayoutInflater.from(getActivity());
        final View exitDialogView = factory.inflate(R.layout.dialog_filter_dsn_mhsw, null);
        final AlertDialog exitDialog = new AlertDialog.Builder(getActivity()).create();
        final Spinner sortJabatan = exitDialogView.findViewById(R.id.sortKelas);

        final String jabatan[] = {
                "Dosen Tetap PNS", "Dosen Tetap NON PNS", "Dosen Honorer"
        };

        final ArrayAdapter<String> AdapterJbtn = new ArrayAdapter<String>(exitDialogView.getContext(), android.R.layout.simple_spinner_item, jabatan);
        AdapterJbtn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortJabatan.setAdapter(AdapterJbtn);

        exitDialog.setView(exitDialogView);
        exitDialogView.findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
                Toast.makeText(getActivity(), "Filter Sukses", Toast.LENGTH_SHORT).show();
            }
        });
        exitDialog.show();
    }

    public void setupAdaptermhsw_dosen(){
        List<object_mhsw_dosen> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(getContext());

        rView = v.findViewById(R.id.rview);
        rView.setLayoutManager(lLayout);

        adapter_mhsw_dosen rcAdapter = new adapter_mhsw_dosen(getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
        rView.setNestedScrollingEnabled(false);
    }

    private List<object_mhsw_dosen> getAllItemList(){
        List<object_mhsw_dosen> allItems = new ArrayList<>();
        allItems.add(new object_mhsw_dosen("Fajar Hadi Putranto", "Dosen Tetap Non PNS","213390297889129", "08912789799", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Setya Putranto", "Dosen PNS","545390297889129", "08912789729", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Hadi Nugraha", "Dosen PNS","6763390297889129", "08912789739", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Putra Yusuf", "Dosen Tetap Dosen PNS","441390297889129", "08912789749", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Setya Nugraha Yusuf", "Freelance","734390297889129", "08912789759", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Nugraha Aji", "Freelance","217390297889129", "08912789796", R.drawable.ava));
        allItems.add(new object_mhsw_dosen("Al Khawarizm", "Dosen PNS","813390297889129", "08912789779", R.drawable.ava));

        return allItems;
    }
}
