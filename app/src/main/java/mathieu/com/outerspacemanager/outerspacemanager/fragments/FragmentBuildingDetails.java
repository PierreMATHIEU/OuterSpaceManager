package mathieu.com.outerspacemanager.outerspacemanager.fragments;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.util.ArrayList;


import mathieu.com.outerspacemanager.outerspacemanager.R;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Building;
import mathieu.com.outerspacemanager.outerspacemanager.tools.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piou on 21/03/2017.
 */

public class FragmentBuildingDetails extends Fragment {

    private TextView textViewBuildingName, textViewBuildingTime,textViewGazCost,textViewMineralCost;
    private Button button;
    private ImageView myImage;

    private ArrayList<Building> myBuilding;
    private Building theBuilding;
    private String token;
    private Retrofit retrofit;
    private Service service;
    private SharedPreferences settings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_buildingdetails,container);

        textViewBuildingName = (TextView)v.findViewById(R.id.txt_BuildingFragName);
        textViewBuildingTime = (TextView)v.findViewById(R.id.txt_BuildingFragTime);
        textViewGazCost= (TextView)v.findViewById(R.id.txt_BuildingFragGazCost);
        textViewMineralCost = (TextView)v.findViewById(R.id.txt_BuildingFragMineralCost);
        button = (Button) v.findViewById(R.id.btn_BuildingDetailCreate);
        myImage = (ImageView)v.findViewById(R.id.imageViewBuilding);

        //Image avec glide
        Glide.with(getContext()).load("http://www.starwars-universe.com/images/encyclopedie/planetes/Balmorra2.jpg").centerCrop().crossFade().into(myImage);

        settings = getActivity().getSharedPreferences("token", 0);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        token = settings.getString("token", new String());
        service = retrofit.create(Service.class);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyBuildings();
            }
        });
    }

    public void buyBuildings(){

        Call<Building> request = service.constructBuilding(token, theBuilding.getBuildingId());

        request.enqueue(new Callback<Building>(){

            @Override
            public void onResponse(Call<Building> call, Response<Building> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), String.format("Construction en cours"), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), String.format("Erreur lors de la construction"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Building> call, Throwable t) {
                Toast.makeText(getContext(), String.format("Erreur lors de la construction !!fatal"), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void setBuilding(Building building) {
        this.theBuilding = building;
        this.textViewBuildingName.setText("Nom : " + String.valueOf(theBuilding.getName()));
        this.textViewBuildingTime.setText("Temps de contruction : " + String.valueOf(theBuilding.getBuildingTime()));
        this.textViewGazCost.setText(String.valueOf("Cout en gas : " + theBuilding.getGazCost()));
        this.textViewMineralCost.setText(String.valueOf("Cout en minéraux : " + theBuilding.getMineralCost()));
    }
}
