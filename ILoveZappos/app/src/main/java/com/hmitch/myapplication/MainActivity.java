package com.hmitch.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.hmitch.myapplication.model.Results;
import com.hmitch.myapplication.model.QueryItem;
import com.hmitch.myapplication.Services.MyService;
import com.hmitch.myapplication.utils.NetworkHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hmitch.myapplication.R.id.action_cart;

public class MainActivity extends AppCompatActivity {

    public static final String MY_GLOBAL_PREFS = "my_global_prefs";
    private static final String TAG = "MainActivity";
    private static final String JSON_URL = "";
    private static final String ENDPOINT = "https://api.zappos.com/";

    List<Results> mItemList;
    Results mItem;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    String[] mCategories;
    RecyclerView mRecyclerView;
    DataItemAdapter mItemAdapter;
    boolean networkOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

//      Code to manage sliding navigation drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mCategories = getResources().getStringArray(R.array.categories);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_list_item, mCategories));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category = mCategories[position];
                if (position == 0) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                    requestData(null);
                }
                else {
                mDrawerLayout.closeDrawer(mDrawerList);
                requestData(category);
                }
            }
        });

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean grid = settings.getBoolean(getString(R.string.pref_display_grid), false);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvItems);
        if (grid) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }

        networkOk = NetworkHelper.hasNetworkAccess(this);
        if (networkOk) {
            requestData(null);
        } else {
            Toast.makeText(this, "Network not available", Toast.LENGTH_SHORT).show();
        }
    }



    private void displayDataItems(String category) {
        if (mItemList != null) {
            mItemAdapter = new DataItemAdapter(this, mItemList);
            mRecyclerView.setAdapter(mItemAdapter);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()) );

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                requestData(query);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case action_cart:
                Toast.makeText(this, "Cart View Unavailable at this Time", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                //for back button refresh
                requestData(null);
                return true;
            case R.id.action_settings:
                // Show the settings screen
                Intent settingsIntent = new Intent(this, PrefsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_choose_category:
                //open the drawer
                mDrawerLayout.openDrawer(mDrawerList);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestData(String term) {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ItemsAPI api = adapter.create(ItemsAPI.class);
        Call<QueryItem> call = api.getFeed(term);
        call.enqueue(new Callback<QueryItem>() {
            @Override
            public void onResponse(Call<QueryItem> call, Response<QueryItem> response) {
                mItemList = response.body().getResults();
                displayDataItems(null);
            }

            @Override
            public void onFailure(Call<QueryItem> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}