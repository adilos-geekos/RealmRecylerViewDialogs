package com.elmansouriadil.realmdatabase.realmrecylerview;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TimeDialog extends DialogFragment {
    Button B2;
    Realm rm;
    EditText E1,E2;
    View.OnClickListener L =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.add_frg_alam){
                Toast.makeText(getActivity(),"clicked",Toast.LENGTH_LONG).show();
                RealmConfiguration rc = new RealmConfiguration.Builder(getActivity()).build();
                Realm.setDefaultConfiguration(rc);
                rm = Realm.getDefaultInstance();
                rm.beginTransaction();
                rm.copyToRealm(new Task(E1.getText().toString(), E2.getText().toString()));
                rm.commitTransaction();
                rm.close();
                Toast.makeText(getActivity(),"added to db",Toast.LENGTH_LONG).show();
            }
        }
    };
    public TimeDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle bundle){
        super.onViewCreated(view,bundle);
        B2 = (Button) view.findViewById(R.id.add_frg_alam);
        B2.setOnClickListener(L);
        E1 = (EditText)  view.findViewById(R.id.tmname);
        E2 = (EditText)  view.findViewById(R.id.tmtime);
    }


}
