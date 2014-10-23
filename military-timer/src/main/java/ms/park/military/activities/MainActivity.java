package ms.park.military.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import ms.park.military.R;
import ms.park.military.adapters.HumanAdapter;
import ms.park.military.models.Human;
import ms.park.military.models.departments.*;
import ms.park.military.preferences.MilitaryPreference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private RecyclerView lvHumans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(
                Html.fromHtml(
                        String.format(
                                "<font color=\"#ffffff\">%s</font>",
                                getString(R.string.app_name)
                        )
                )
        );

        lvHumans = (RecyclerView) findViewById(R.id.lv_humans);

        init();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        List<Human> humans = MilitaryPreference.getHumans(this);
        HumanAdapter adapter = new HumanAdapter(this, humans);

        lvHumans.setHasFixedSize(true);
        lvHumans.setItemAnimator(new DefaultItemAnimator());
        lvHumans.setLayoutManager(new LinearLayoutManager(this));
        lvHumans.setAdapter(adapter);
    }

    private void initEvent() {
        lvHumans.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            startActivity(new Intent(MainActivity.this, AddActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
