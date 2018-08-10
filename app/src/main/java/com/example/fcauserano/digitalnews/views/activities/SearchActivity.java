package com.example.fcauserano.digitalnews.views.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.fcauserano.digitalnews.R;
import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.utils.CargadorFragment;
import com.example.fcauserano.digitalnews.views.adapters.NoticiaAdapter;
import com.example.fcauserano.digitalnews.views.fragments.SearchFragment;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private Toolbar toolbarSearch;
    private SearchFragment searchFragment;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbarSearch = findViewById(R.id.toolbat_searchActivity);
        searchFragment = new SearchFragment();
        setSupportActionBar(toolbarSearch);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String searchWord = bundle.getString(SearchFragment.KET_SEARCH_WORD);
            searchFragment.setArguments(bundle);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        CargadorFragment.cargarFragment(fragmentManager, R.id.container_fragment_search_id, searchFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuItem_search);
        searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Buscar noticias");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
                searchFragment.actualizarBusqueda(query);
                searchView.setQuery("", false);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
