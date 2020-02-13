package mum.vsebastianvc.personalapp.views.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import mum.vsebastianvc.personalapp.R;
import mum.vsebastianvc.personalapp.adapters.RecyclerViewAdapterImages;
import mum.vsebastianvc.personalapp.di.component.App;
import mum.vsebastianvc.personalapp.presenters.DashboardPresenter;
import mum.vsebastianvc.personalapp.views.IDashboardView;


/**
 * Created by Sebastian on 02/04/2020.
 */
public class DashboardActivity extends AppCompatActivity implements IDashboardView, RecyclerViewAdapterImages.ItemClickListener {

    @Inject
    DashboardPresenter presenter;
    private RecyclerViewAdapterImages adapter;
    private RecyclerView recyclerView;
    private SearchView searchView;
    final String DEFAULT_PAGE = "vanilla";
    final String QUERY_HINT = "Type your keyword here";
    public static final String TITLE = "title";
    public static final String LINK = "link";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApplicationContextReference().inject(this);
        setContentView(R.layout.activity_dashboard);

        presenter.setView(this);

        presenter.loadImgurDataWithQuery(DEFAULT_PAGE);

        searchView = findViewById(R.id.svImages);

        searchView.setActivated(true);
        searchView.setQueryHint(QUERY_HINT);
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                presenter.loadImgurDataWithQuery(searchView.getQuery().toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // set up the RecyclerView
        recyclerView = findViewById(R.id.rvDashboard);
    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra(TITLE, adapter.getItem(position).first);
        intent.putExtra(LINK, adapter.getItem(position).second);
        startActivity(intent);

    }

    @Override
    public void onShowListOfImagesWithQuery(List<Pair<String, String>> data) {
        if (!data.isEmpty()) {
            RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 4);
            recyclerView.setLayoutManager(manager);
            adapter = new RecyclerViewAdapterImages(getApplicationContext(), data);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        } else {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.sorry);
            builder.setMessage(R.string.try_again);
            builder.setNeutralButton(R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

