package mathieu.com.outerspacemanager.outerspacemanager.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.R;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Building;
import mathieu.com.outerspacemanager.outerspacemanager.fragments.FragmentBuildingDetails;
import mathieu.com.outerspacemanager.outerspacemanager.fragments.FragmentBuildingList;
import retrofit2.Retrofit;

/**
 * Created by Piou on 07/03/2017.
 */

public class BuildingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String PREFS_TOKEN = "token";
    private Retrofit retrofit;
    private ArrayList<Building> myBuilding;
    private Building theBuilding;
    public String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentBuildingList fragBuildingList = (FragmentBuildingList)getSupportFragmentManager().findFragmentById(R.id.frag_BuildingList);
        FragmentBuildingDetails fragBuildingDetails = (FragmentBuildingDetails)getSupportFragmentManager().findFragmentById(R.id.frag_BuildingDetails);
        if(fragBuildingDetails == null|| !fragBuildingDetails.isInLayout()){
            Intent myIntent = new Intent(getApplicationContext(),BuildingDetailsActivity.class);
            myIntent.putExtra("building", fragBuildingList.getMyBuildings().get(position));
            startActivity(myIntent);
        } else {
            fragBuildingDetails.setBuilding(fragBuildingList.getMyBuildings().get(position));
        }
    }


    public void buildingsRecup(ArrayList<Building> myBuildings) {
        FragmentBuildingDetails fragBuildingDetails = (FragmentBuildingDetails) getSupportFragmentManager().findFragmentById(R.id.frag_BuildingDetails);
        if (fragBuildingDetails != null && fragBuildingDetails.isInLayout()) {
            fragBuildingDetails.setBuilding(myBuildings.get(0));
        }
    }

}
