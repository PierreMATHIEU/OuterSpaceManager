package mathieu.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.User;


/**
 * Created by Piou on 13/03/2017.
 */

public class CustomAdapterGalaxie extends ArrayAdapter<User> {

    private final Context context;
    private final ArrayList<User> values;
    private User theUser;

    public CustomAdapterGalaxie(Context context, ArrayList<User> values) {
        super(context, R.layout.custom_arrayadapter, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_adaptergalaxie, parent, false);

        theUser = values.get(position);

        TextView building_name = (TextView) rowView.findViewById(R.id.txt_GalaxieName);
        TextView textViewLvl = (TextView) rowView.findViewById(R.id.txt_GalaxiePoints);

        building_name.setText("Name : " + theUser.getUsername());
        textViewLvl.setText("Level : " + theUser.getPoints().toString());

        return rowView;
    }
}
