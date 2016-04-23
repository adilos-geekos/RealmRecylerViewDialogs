package com.elmansouriadil.realmdatabase.realmrecylerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    RecyclerView rv;
    Button B;
    Realm rm ;
    TaskAdapter TA;
    RealmResults<Task> RT;
    RealmChangeListener rc = new RealmChangeListener() {
        @Override
        public void onChange() {
            //called if db is changed
            TA.updateList(RT);
        }
    };
    public  void onStart(){
        super.onStart();
        rm.addChangeListener(rc);
    }
    public void onStop(){
        super.onStop();
        rm.removeChangeListener(rc);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        B = (Button) findViewById(R.id.btn_add);
        B.setOnClickListener(this);
        rv = (RecyclerView) findViewById(R.id.recy);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        RealmConfiguration rc = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(rc);
        rm = Realm.getDefaultInstance();
        RT = rm.where(Task.class).findAllAsync();
        TA = new TaskAdapter(this,RT);
        rv.setAdapter(TA);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add ){
                TimeDialog T = new TimeDialog();
                T.show(getSupportFragmentManager(),"Alarm");
        }
    }
}
