package mathieu.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piou on 07/03/2017.
 */

public class ConnectActivity extends Activity {

    private Button btnConnect;
    private EditText username;
    private EditText password;

    private User user;
    private Retrofit retrofit;
    public static final String PREFS_TOKEN = "token";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        username = (EditText)findViewById(R.id.et_ConnectUsername);
        password = (EditText)findViewById(R.id.et_ConnectPassword);
        btnConnect = (Button)findViewById(R.id.btn_Connect);


        btnConnect.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                user = new User(username.getText().toString(), password.getText().toString(), 0.00,0.00);

                retrofit = new Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Service service = retrofit.create(Service.class);
                Call<User> request = service.toConnect(user);

                request.enqueue(new Callback<User>(){
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            //Stocke le token dans la BDD
                            SharedPreferences settings = getSharedPreferences(PREFS_TOKEN, 0);
                            SharedPreferences.Editor editor = settings.edit();

                            editor.putString(PREFS_TOKEN, response.body().getToken());
                            editor.commit();

                            Intent IntentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(IntentMainActivity);
                        }else{
                            Toast.makeText(getApplicationContext(), String.format("Erreur lors la connexion"), Toast.LENGTH_SHORT).show();
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
        });

    }
}
