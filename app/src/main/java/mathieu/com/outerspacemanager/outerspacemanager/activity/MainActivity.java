package mathieu.com.outerspacemanager.outerspacemanager.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mathieu.com.outerspacemanager.outerspacemanager.R;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.User;
import mathieu.com.outerspacemanager.outerspacemanager.tools.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piou on 06/03/2017.
 */

public class MainActivity extends Activity {

    private TextView username;
    private TextView points;
    private Button btn_VueGenerale;
    private Button btn_Batiment;
    private Button btn_Flotte;
    private Button btn_Recherche;
    private Button btn_ChantierSpa;
    private Button btn_Galaxie;
    private Button btn_Logout;
    private TextView gas;
    private TextView minerals;
    private SwipeRefreshLayout swiperefresh;

    public static final String PREFS_TOKEN = "token";
    private Retrofit retrofit;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (TextView)findViewById(R.id.txt_Username);
        points = (TextView)findViewById(R.id.txt_Point);
        gas = (TextView)findViewById(R.id.txt_gas);
        minerals = (TextView)findViewById(R.id.txt_minerals);

        btn_VueGenerale =(Button)findViewById(R.id.btn_VueGenerale);
        btn_Batiment = (Button)findViewById(R.id.btn_Batiment);
        btn_Flotte = (Button)findViewById(R.id.btn_Flotte);
        btn_Recherche =(Button)findViewById(R.id.btn_Recherche);
        btn_ChantierSpa = (Button)findViewById(R.id.btn_ChantierSpa);
        btn_Galaxie = (Button)findViewById(R.id.btn_Galaxie);
        btn_Logout = (Button)findViewById(R.id.btn_Logout);
        swiperefresh = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showAll();
            }
        });

        showAll();

        //Bouton pour se déconnecter
        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences(PREFS_TOKEN, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(PREFS_TOKEN, "");
                editor.commit();
                finish();
            }
        });

        btn_Batiment.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent IntentBuildingActivity = new Intent(getApplicationContext(), BuildingActivity.class);
                startActivity(IntentBuildingActivity);
            }
        });

        btn_Galaxie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntentBuildingActivity = new Intent(getApplicationContext(), GalaxieActivity.class);
                startActivity(IntentBuildingActivity);
            }
        });
        btn_VueGenerale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntentBuildingActivity = new Intent(getApplicationContext(), GeneraleActivity.class);
                startActivity(IntentBuildingActivity);
            }
        });
        btn_Flotte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntentBuildingActivity = new Intent(getApplicationContext(), FlotteActivity.class);
                startActivity(IntentBuildingActivity);
            }
        });
        btn_ChantierSpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntentBuildingActivity = new Intent(getApplicationContext(), ChantierSpatialActivity.class);
                startActivity(IntentBuildingActivity);
            }
        });
        btn_Recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntentBuildingActivity = new Intent(getApplicationContext(), RechercheActivity.class);
                startActivity(IntentBuildingActivity);
            }
        });

        //PERMISSION
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }

        }
    }

    public void showAll(){
        //Token
        SharedPreferences settings = getSharedPreferences(PREFS_TOKEN, 0);
        String token = settings.getString(PREFS_TOKEN, new String());
        // APPEL API
        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<User> request = service.getUserAccount(token);

        request.enqueue(new Callback<User>(){

            //Réponce OK -> Récupère le username et points
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                swiperefresh.setRefreshing(false);
                if (response.isSuccessful()) {
                    username.setText(response.body().getUsername());
                    points.setText("Points : " + response.body().getPoints());
                    gas.setText("Gas : " + response.body().getGas());
                    minerals.setText("Minerals : " + response.body().getMinerals());
                }else{
                    Toast.makeText(getApplicationContext(), String.format("Erreur lors de la récupération"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Erreur de connection !!! Noob !!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });
    }
}

