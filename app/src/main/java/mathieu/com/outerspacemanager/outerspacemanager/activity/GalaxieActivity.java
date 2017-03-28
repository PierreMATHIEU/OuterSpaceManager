package mathieu.com.outerspacemanager.outerspacemanager.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.R;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Galaxies;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.User;
import mathieu.com.outerspacemanager.outerspacemanager.customAdapter.CustomAdapterGalaxie;
import mathieu.com.outerspacemanager.outerspacemanager.tools.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piou on 13/03/2017.
 */

public class GalaxieActivity extends Activity {

    private ListView listGlaxie;

    public static final String PREFS_TOKEN = "token";
    private Retrofit retrofit;
    public String token;
    private ArrayList<User> theUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaxie);

        listGlaxie = (ListView)findViewById(R.id.lv_Galaxy);

        //Token
        SharedPreferences settings = getSharedPreferences(PREFS_TOKEN, 0);
        token = settings.getString(PREFS_TOKEN, new String());

        // APPEL API
        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Galaxies> request = service.getAllUsers(token);

        request.enqueue(new Callback<Galaxies>(){

            //Réponce OK -> Peuple la liste view
            @Override
            public void onResponse(Call<Galaxies> call, Response<Galaxies> response) {
                if (response.isSuccessful()) {
                    theUser = response.body().getUsers();
                    listGlaxie.setAdapter(new CustomAdapterGalaxie(getApplicationContext(), response.body().getUsers()));
                }else{
                    Toast.makeText(getApplicationContext(), String.format("Erreur de récupération des galaxies"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Galaxies> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Erreur de récupération des galaxies !!! Noob !!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });
    }
}
