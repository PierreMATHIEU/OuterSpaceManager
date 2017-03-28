package mathieu.com.outerspacemanager.outerspacemanager.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import mathieu.com.outerspacemanager.outerspacemanager.R;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Building;
import mathieu.com.outerspacemanager.outerspacemanager.fragments.FragmentBuildingDetails;

/**
 * Created by Piou on 21/03/2017.
 */

public class BuildingDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildingdetails);

        Building myBuilding = (Building) getIntent().getExtras().getSerializable("building");

        FragmentBuildingDetails fragment_ship_detail = (FragmentBuildingDetails)getSupportFragmentManager().findFragmentById(R.id.frag_BuildingDetails);
        fragment_ship_detail.setBuilding(myBuilding);
    }
}