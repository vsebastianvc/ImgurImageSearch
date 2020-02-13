package mum.vsebastianvc.personalapp.views.activities;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import mum.vsebastianvc.personalapp.R;
import mum.vsebastianvc.personalapp.adapters.RecyclerViewAdapterComments;
import mum.vsebastianvc.personalapp.adapters.RecyclerViewAdapterImages;
import mum.vsebastianvc.personalapp.di.component.App;
import mum.vsebastianvc.personalapp.presenters.DetailPresenter;
import mum.vsebastianvc.personalapp.views.IDetailView;


/**
 * Created by Sebastian on 02/04/2020.
 */
public class DetailActivity extends AppCompatActivity implements IDetailView, RecyclerViewAdapterImages.ItemClickListener {

    private ImageView image;
    private Toolbar toolbar;
    private EditText etComment;
    private Button btnSubmit;
    @Inject
    DetailPresenter presenter;
    private RecyclerViewAdapterComments adapter;
    private RecyclerView recyclerView;
    private String title;
    private String link;
    private List<String> listOfComments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        App.getApplicationContextReference().inject(this);

        toolbar = findViewById(R.id.toolbar);
        image = findViewById(R.id.iv_picture);
        recyclerView = findViewById(R.id.rv_Detail);
        etComment = findViewById(R.id.et_Comment);
        btnSubmit = findViewById(R.id.btn_submit);

        presenter.setView(this);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String titleExtra = extras.getString(DashboardActivity.TITLE);
            final String link = extras.getString(DashboardActivity.LINK);
            if (!titleExtra.isEmpty() && !link.isEmpty()) {
                this.title = titleExtra;
                this.link = link;
                setupTitle(titleExtra);
                setupImage(link);
                presenter.searchComments(titleExtra, link);
                setComments();
            }

        }
        this.setSupportActionBar(toolbar);
        Objects.requireNonNull(this.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etComment.getText().toString().isEmpty()) {
                    Toast.makeText(v.getContext(), R.string.comment_inserted, Toast.LENGTH_SHORT).show();
                    presenter.insertCommentOnImage(title, link, etComment.getText().toString());
                    updateRecyclerView(etComment.getText().toString());
                    etComment.setText("");
                    hideKeyboard(v);
                }
            }
        });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void updateRecyclerView(String comment) {
        int insertIndex = listOfComments.size();
        listOfComments.add(insertIndex, comment);
        adapter.notifyItemInserted(insertIndex);

    }

    public void populateListOfComments(List<String> list) {
        this.listOfComments = list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupTitle(String titleExtra) {
        String title = titleExtra.length() > 30 ? titleExtra.substring(0, 31) : titleExtra;
        toolbar.setTitle(title);
    }

    private void setupImage(String link) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;

        ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
        layoutParams.width = (int) (displayMetrics.density * 130f / (240f / dpHeight));
        layoutParams.height = (int) (displayMetrics.density * 130f / (240f / dpWidth));
        image.setLayoutParams(layoutParams);
        Glide.with(getApplicationContext()).load(link).into(image);
    }

    @Override
    public void onItemClick(View view, int position) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.selected_comment);
        builder.setMessage(adapter.getItem(position));
        builder.setNeutralButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    @Override
    public void setComments() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerViewAdapterComments(getApplicationContext(), listOfComments);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplication(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
