package mathieu.com.outerspacemanager.outerspacemanager.fragments;


import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.activity.BuildingActivity;
import mathieu.com.outerspacemanager.outerspacemanager.customAdapter.CustomArrayAdapter;
import mathieu.com.outerspacemanager.outerspacemanager.R;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Building;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Buildings;
import mathieu.com.outerspacemanager.outerspacemanager.tools.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piou on 21/03/2017.
 */

public class FragmentBuildingList extends Fragment {
    private ListView list_ships;
    private Service service;
    private SharedPreferences settings;
    private SwipeRefreshLayout swiperefresh;

    public static final String PREFS_TOKEN = "token";
    private Retrofit retrofit;
    private ArrayList<Building> myBuilding;
    public String token;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_buildinglist,container);

        settings = getActivity().getSharedPreferences("token", 0);
        list_ships = (ListView) v.findViewById(R.id.list_BuildingList);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        swiperefresh = (SwipeRefreshLayout)v.findViewById(R.id.swiperefresh);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshListShips();
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        token = settings.getString("token", new String());
        service = retrofit.create(Service.class);
        list_ships.setOnItemClickListener((BuildingActivity)getActivity());

        refreshListShips();
    }

    public void refreshListShips(){

        Call<Buildings> request = service.getBuildings(token);

        request.enqueue(new Callback<Buildings>() {
            @Override
            public void onResponse(Call<Buildings> call, Response<Buildings> response) {
                if (response.isSuccessful()) {
                    swiperefresh.setRefreshing(false);
                    myBuilding = response.body().getBuildings();
                    ((BuildingActivity) getActivity()).buildingsRecup(myBuilding);
                    list_ships.setAdapter(new CustomArrayAdapter(getContext(), myBuilding));
                }else{
                    Toast.makeText(getContext(), String.format("Erreur de récupération des buildings"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Buildings> call, Throwable t) {
                Toast.makeText(getContext(), String.format("Erreur lors de la récupération des vaisseaux"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<Building> getMyBuildings(){
        return this.myBuilding;
    }
}


