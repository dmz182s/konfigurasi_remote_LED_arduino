package com.example.aplikasiremote;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class TambahDataFragment extends Fragment {

    ImageView mImage_off,mImage_on;
    ToggleButton mToggleButton;
    Button mButton;
    Remote mRemote = new Remote();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tambah_data, container, false);
        mImage_off = v.findViewById(R.id.lamp_off);
        mImage_on = v.findViewById(R.id.lamp_on);
        mToggleButton = v.findViewById(R.id.switch_button);

        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mToggleButton.isChecked())
                {
                    mImage_off.setVisibility(View.VISIBLE);
                    mImage_on.setVisibility(View.GONE);
                    mRemote.setPower(0);
                    new SimpanData().execute();
                }else
                {
                    mImage_on.setVisibility(View.VISIBLE);
                    mImage_off.setVisibility(View.GONE);
                    mRemote.setPower(1);
                    new SimpanData().execute();
                }
            }
        });
        mButton = v.findViewById(R.id.btn_lihat_data);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(ListDataFragment.class);
            }
        });
        new BacaData().execute();
        return v;
    }

    public void setFragment(Class mFragmentClass)
    {
        Fragment mfragment = null;
        try {
            mfragment = (Fragment) mFragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, mfragment).commit();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    class SimpanData extends AsyncTask<String,String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            if(mRemote.getPower()==1){
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Lampu Menyala",
                        Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Lampu Padam",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        @Override
        protected String doInBackground(String... strings) {
            RemoteLab.get(getActivity().getApplicationContext()).addData(mRemote);
            return null;
        }
    }

    class BacaData extends AsyncTask<String,String, String> {
        Remote remote;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            if(remote.getPower()==0){
                mImage_on.setVisibility(View.GONE);
                mImage_off.setVisibility(View.VISIBLE);
                mToggleButton.setChecked(true);
            }else{
                mImage_on.setVisibility(View.VISIBLE);
                mImage_off.setVisibility(View.GONE);
                mToggleButton.setChecked(false);
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            remote = RemoteLab.get(getActivity().getApplicationContext()).getLastStatus();

            return null;
        }
    }
}
