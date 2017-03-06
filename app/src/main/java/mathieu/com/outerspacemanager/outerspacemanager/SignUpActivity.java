package mathieu.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.password;

/**
 * Created by Piou on 06/03/2017.
 */

public class SignUpActivity extends Activity {

    private EditText etIdentifiant;
    private EditText etPassword;
    private Button btnValider;
    private User user;
    private Retrofit retrofit;
    private String token;
    public static final String PREFS_TOKEN = "token";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Regarde si j'ai déjà été enregistré si oui lance directement l'application
        SharedPreferences settings = getSharedPreferences(PREFS_TOKEN, 0);
        String token = settings.getString(PREFS_TOKEN,"");
        if(!token.isEmpty()){
            Intent IntentMainActivity = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(IntentMainActivity);
        }

        etIdentifiant = (EditText)findViewById(R.id.etIdentifiant);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnValider = (Button)findViewById(R.id.btnValider);


        btnValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user = new User(etIdentifiant.getText().toString(),etPassword.getText().toString());
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Service service = retrofit.create(Service.class);
                Call<User> request = service.createUserAccount(user);

                request.enqueue(new Callback<User>(){

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("plop", response.toString());

                        //Stocke le token dans la BDD
                        SharedPreferences settings = getSharedPreferences(PREFS_TOKEN, 0);
                        SharedPreferences.Editor editor = settings.edit();

                        editor.putString(PREFS_TOKEN, response.body().getToken());
                        editor.commit();

                        Intent IntentMainActivity = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(IntentMainActivity);
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
        });

    }

}
