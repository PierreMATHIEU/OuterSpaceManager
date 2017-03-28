package mathieu.com.outerspacemanager.outerspacemanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.R;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.AttackResponse;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Fleet;
import mathieu.com.outerspacemanager.outerspacemanager.customAdapter.CustomAdapterGeneralRapport;
import mathieu.com.outerspacemanager.outerspacemanager.database.AttackDAO;

/**
 * Created by Piou on 21/03/2017.
 */

public class GeneraleRapportActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<AttackResponse> contentBDD = new ArrayList<AttackResponse>();
    private Fleet shipsAAttack = new Fleet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generalrapport);

        //Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.rc_GeneraleRapport);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        AttackDAO attackDB = new AttackDAO(getApplicationContext());

        //JSON Sérialisation
        Gson gson = new Gson();
        String json = gson.toJson(shipsAAttack);

        //DATABASE
        attackDB.open();
        contentBDD = attackDB.getAllAttacks();

        //Delete database
        for (int i=0; i< contentBDD.size(); i++){
           if( contentBDD.get(i).getAttackTime() - System.currentTimeMillis() < 0){
               attackDB.deleteAttack(contentBDD.get(i));
           }
       }

        contentBDD = attackDB.getAllAttacks();
        attackDB.close();

        CustomAdapterGeneralRapport myCustomAdapterGeneralRapport = new CustomAdapterGeneralRapport(contentBDD, GeneraleRapportActivity.this);

        mRecyclerView.setAdapter(myCustomAdapterGeneralRapport);



    }
}
