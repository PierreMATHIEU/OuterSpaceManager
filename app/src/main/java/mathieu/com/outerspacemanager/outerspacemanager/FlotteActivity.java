package mathieu.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Fleet;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Ship;
import mathieu.com.outerspacemanager.outerspacemanager.tools.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piou on 13/03/2017.
 */

public class FlotteActivity extends AppCompatActivity {

    public static final String PREFS_TOKEN = "token";
    private Retrofit retrofit;
    public String token;
    private ArrayList<Ship> theShip;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flotte);

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
                    theShip = response.body().getShips();
                    mRecyclerView.setAdapter(new CustomAdapterFlotte(response.body().getShips(), FlotteActivity.this));

                    if(response.body().getSize() != 0){

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

    }
}
