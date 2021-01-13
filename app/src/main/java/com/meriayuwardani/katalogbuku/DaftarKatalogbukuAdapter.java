package com.meriayuwardani.katalogbuku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.meriayuwardani.katalogbuku.GenericUtility;
import com.meriayuwardani.katalogbuku.R;

import java.util.List;

public class DaftarKatalogbukuAdapter extends ArrayAdapter<Katalogbuku> {
    Context context;

    public DaftarKatalogbukuAdapter(@NonNull Context context, @NonNull List<Katalogbuku> objects) {
        super(context, R.layout.row_katalogbuku, objects);
        this.context = context;
    }

    class ViewHolder {
        TextView txTanggal;
        TextView txThnterbit;
        TextView txJudul;
        TextView txPengarang;
        TextView txKodeisbn;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Katalogbuku tr = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_katalogbuku,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txTanggal = convertView.findViewById(R.id.row_tx_Tanggal_Katalogbuku);
            viewHolder.txThnterbit = convertView.findViewById(R.id.row_tx_Thnterbit_Katalogbuku);
            viewHolder.txJudul = convertView.findViewById(R.id.row_tx_Judul_Katalogbuku);
            viewHolder.txPengarang = convertView.findViewById(R.id.row_tx_Pengarang_Katalogbuku);
            viewHolder.txKodeisbn = convertView.findViewById(R.id.row_tx_Kodeisbn_Katalogbuku);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txTanggal.setText(GenericUtility.DATE_FORMAT.format(tr.getTanggal()));
        viewHolder.txThnterbit.setText(tr.getThnterbit());
        viewHolder.txJudul.setText(tr.getJudul());
        viewHolder.txPengarang.setText(tr.getPengarang());
        viewHolder.txKodeisbn.setText(tr.getKodeisbn());
        return convertView;
    }
}
