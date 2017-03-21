package mathieu.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Building;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Buildings;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Report;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Reports;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.User;
import mathieu.com.outerspacemanager.outerspacemanager.tools.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piou on 13/03/2017.
 */

public class GeneraleActivity extends Activity {

    private TextView username;
    private TextView points;
    private TextView gas;
    private TextView gasMod;
    private TextView minerals;
    private TextView mineralsMod;
    private Button btn_GeneralReport;
    private ListView list_GeneralReport;

    public static final String PREFS_TOKEN = "token";
    private Retrofit retrofit;
    private String token;
    private ArrayList<Report> myReport;
    private Report theReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generale);

        username = (TextView)findViewById(R.id.txt_GeneraleUsername);
        points = (TextView)findViewById(R.id.txt_GeneralePoints);
        gas = (TextView)findViewById(R.id.txt_GeneraleGas);
        gasMod = (TextView)findViewById(R.id.txt_GeneraleGasMod);
        minerals = (TextView)findViewById(R.id.txt_GeneraleMinerals);
        mineralsMod = (TextView)findViewById(R.id.txt_GeneraleMineralMod);
        btn_GeneralReport = (Button)findViewById(R.id.btn_GeneralRapport);
        list_GeneralReport = (ListView)findViewById(R.id.list_GeneralReport);

        btn_GeneralReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntentGeneraleActivity = new Intent(getApplicationContext(), GeneraleRapportActivity.class);
                startActivity(IntentGeneraleActivity);
            }
        });

        //Token
        SharedPreferences settings = getSharedPreferences(PREFS_TOKEN, 0);
        token = settings.getString(PREFS_TOKEN, new String());

        // APPEL API
        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<User> request = service.getUserAccount(token);

        request.enqueue(new Callback<User>(){

            //Réponce OK
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    username.setText(response.body().getUsername());
                    points.setText("Points : " + response.body().getPoints());
                    gas.setText("Gas : " + response.body().getGas());
                    gasMod.setText("Gas mod : " + response.body().getGasMod());
                    minerals.setText("Minerals : " + response.body().getMinerals());
                    mineralsMod.setText("Mineral mod : " + response.body().getMineralMod());
                }else{
                    Toast.makeText(getApplicationContext(), String.format("Erreur de récupération de l'utilisateur"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Erreur de récupération des buildings !!! Noob !!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });
        generalListView();

    }
    public void generalListView(){
        //Token
        SharedPreferences settings = getSharedPreferences(PREFS_TOKEN, 0);
        token = settings.getString(PREFS_TOKEN, new String());

        // APPEL API
        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Reports> request = service.getReports(token,0,10);

        request.enqueue(new Callback<Reports>(){

            //Réponce OK -> Peuple la liste view
            @Override
            public void onResponse(Call<Reports> call, Response<Reports> response) {
                if (response.isSuccessful()) {
                    myReport = response.body().getReports();
                    list_GeneralReport.setAdapter(new CustomAdapterReports(getApplicationContext(), response.body().getReports()));
                }else{
                    Toast.makeText(getApplicationContext(), String.format("Erreur de récupération des reports"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Reports> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Erreur de récupération des reports !!! Noob !!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });
    }
}
