package com.example.aplikasiremote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RemoteAdapter extends RecyclerView.Adapter<RemoteAdapter.RemoteViewHolder> {
    List<Remote> mRemotes;
    Remote remote;
    Context mcontext;

    public RemoteAdapter(Context context, List<Remote> remotes)
    {
        mcontext = context;
        mRemotes = remotes;
    }

    @NonNull
    @Override
    public RemoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        return new RemoteViewHolder (layoutInflater.inflate(R.layout.item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RemoteViewHolder holder, int position) {
        remote = mRemotes.get(position);
        if(remote.getPower()==1){
            holder.mLamp.setImageResource(R.drawable.light_bulb_on);
            holder.mPower.setText("Lampu Menyala");
        }else{
            holder.mLamp.setImageResource(R.drawable.light_bulb);
            holder.mPower.setText("Lampu Padam");
        }
        SimpleDateFormat sdfTanggal = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat sdfWaktu = new SimpleDateFormat("HH:mm");
        Date tanggal = null;
        try {
            tanggal = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(remote.getmWaktu());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.mWaktu.setText(sdfTanggal.format(tanggal)+ " Jam "+ sdfWaktu.format(tanggal));

    }

    @Override
    public int getItemCount() {
        return mRemotes.size();
    }

    public class RemoteViewHolder extends RecyclerView.ViewHolder{
        ImageView mLamp;
        TextView mPower;
        TextView mWaktu;
        public RemoteViewHolder(@NonNull View itemView) {
            super(itemView);
            mLamp = (ImageView) itemView.findViewById(R.id.imageView);
            mPower = (TextView) itemView.findViewById(R.id.Power);
            mWaktu = (TextView) itemView.findViewById(R.id.Waktu);
        }
    }
}
