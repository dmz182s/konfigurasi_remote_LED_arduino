package com.example.aplikasiremote;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ListDataFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    List<Remote> mRemotes =  new ArrayList<>();
    RecyclerView mRecyclerView;
    private RemoteAdapter mAdapter;
    DrawerLayout drawerLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lihat_data, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.data);
        new Loadlistdata().execute();
        return v;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }

    private class Loadlistdata extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            mRemotes = RemoteLab.get(getActivity()).getRemoteData();

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            RemoteAdapter remoteAdapter = new RemoteAdapter(getActivity().getApplicationContext(), mRemotes);
            mRecyclerView.setAdapter(remoteAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            remoteAdapter.notifyDataSetChanged();

        }

    }

}

