package mathieu.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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

    public static final String PREFS_TOKEN = "token";
    private Retrofit retrofit;
    private String token;

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


    }
}
