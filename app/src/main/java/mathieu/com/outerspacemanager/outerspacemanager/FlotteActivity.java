package mathieu.com.outerspacemanager.outerspacemanager;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.AttackResponse;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Fleet;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Ship;
import mathieu.com.outerspacemanager.outerspacemanager.database.AttackDAO;
import mathieu.com.outerspacemanager.outerspacemanager.tools.OnAttackClickListener;
import mathieu.com.outerspacemanager.outerspacemanager.tools.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piou on 13/03/2017.
 */

public class FlotteActivity extends AppCompatActivity implements OnAttackClickListener{

    public static final String PREFS_TOKEN = "token";
    private Retrofit retrofit;
    public String token;
    private ArrayList<Ship> myShip;
    private Ship theShip;
    private Integer theAmount;
    private Integer theNumberOfItem;
    private Integer thePosition;
    private Fleet shipsAAttack = new Fleet();
    private String userName;
    private ArrayList<Ship> listShip;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button btn_FlotteAtack;
    private EditText edit_FlotteUser;

    private AttackDAO myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flotte);
        btn_FlotteAtack =(Button)findViewById(R.id.btn_FlotteAttack);
        edit_FlotteUser = (EditText)findViewById(R.id.edit_FlotteUser);



        btn_FlotteAtack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = edit_FlotteUser.getText().toString();
                attackShipsManager();
            }
        });


        //Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.rc_flotte);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Token
        SharedPreferences settings = getSharedPreferences(PREFS_TOKEN, 0);
        token = settings.getString(PREFS_TOKEN, new String());

        // APPEL API
        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Fleet> request = service.getFleet(token);

        request.enqueue(new Callback<Fleet>(){

            //Réponce OK -> Peuple la ReciclerView
            @Override
            public void onResponse(Call<Fleet> call, Response<Fleet> response) {
                if (response.isSuccessful()) {
                    myShip = response.body().getShips();
                    if(response.body().getNumberOfShip() > 0){

                        CustomAdapterFlotte myCustomAdapterFlotte = new CustomAdapterFlotte(response.body().getShips(), FlotteActivity.this);

                        //Envoie l'activité au listener
                        myCustomAdapterFlotte.setMyAttackListener(FlotteActivity.this);
                        mRecyclerView.setAdapter(myCustomAdapterFlotte);

                    }else{
                        Toast.makeText(getApplicationContext(), String.format("Vous n'avez pas de vaisseaux"), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), String.format("Erreur de récupération des vaisseaux"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Fleet> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Erreur de récupération des vaisseaux !!! Noob !!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }

        }
    }

    @Override
    public void onAttackClickListener(int id, Integer amount) {
        Ship newShip = new Ship(id,amount);
        shipsAAttack.addShip(newShip);
        theAmount = amount;

    }

    public void attackShipsManager(){
        new AlertDialog.Builder(this)
                .setTitle("Attaquer")
                .setMessage("Voulez vous attaquer ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        retrofit = new Retrofit.Builder()
                                .baseUrl("https://outer-space-manager.herokuapp.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        Service service = retrofit.create(Service.class);
                        Call<AttackResponse> request = service.attackUser(token, userName, shipsAAttack);

                        request.enqueue(new Callback<AttackResponse>(){

                            @Override
                            public void onResponse(Call<AttackResponse> call, Response<AttackResponse> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), String.format("Attaque en cours"), Toast.LENGTH_SHORT).show();

                                    //DATABASE
                                    AttackDAO attackDB = new AttackDAO(getApplicationContext());
                                    attackDB.open();
                                    attackDB.createAttack(response.body().getAttackTime(),"");
                                    attackDB.close();

                                }else{
                                    Toast.makeText(getApplicationContext(), String.format("Erreur lors de l'attaque"), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<AttackResponse> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), String.format("Erreur lors de l'attaque !!fatal"), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}
