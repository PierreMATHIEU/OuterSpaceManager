package mathieu.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
/**
 * Created by Piou on 13/03/2017.
 */

public class FlotteActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flotte);

        mRecyclerView = (RecyclerView) findViewById(R.id.rc_flotte);


        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CustomAdapterFlotte();
        mRecyclerView.setAdapter(mAdapter);

    }
}
