package com.example.fcauserano.digitalnews.views.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fcauserano.digitalnews.controller.NoticiaController;
import com.example.fcauserano.digitalnews.utils.CargadorFragment;
import com.example.fcauserano.digitalnews.utils.ResultListener;
import com.example.fcauserano.digitalnews.views.fragments.CategoriaNoticiasFragment;
import com.example.fcauserano.digitalnews.views.fragments.DetalleNoticiaFragment;
import com.example.fcauserano.digitalnews.R;
import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.views.fragments.HomeFragment;
import com.example.fcauserano.digitalnews.views.fragments.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeFragment.NotificadorNoticia, NavigationView.OnNavigationItemSelectedListener {


    private TextView textViewMarquee;
    private HomeFragment homeFragment;
    private List<Noticia> noticias;
    private NavigationView menuCategorias;
    private DrawerLayout drawerActivityMain;
    //private ImageView imageSearch;
    //private TextView txtSearchWord;
    private Toolbar toolbarMainSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textViewMarquee = findViewById(R.id.marquee);
        menuCategorias = findViewById(R.id.menu_categorias);
        menuCategorias.setNavigationItemSelectedListener(this);
        drawerActivityMain = findViewById(R.id.drawer_activity_main);
        //imageSearch = findViewById(R.id.buscar);
        //txtSearchWord = findViewById(R.id.textViewSearchWord);
        toolbarMainSearch = findViewById(R.id.toolbat_searchActivity);
        setSupportActionBar(toolbarMainSearch);

        noticias = new ArrayList<>();

/*        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                String searchWord = txtSearchWord.getText().toString();
                if (!searchWord.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putString(SearchFragment.KET_SEARCH_WORD, searchWord);
                    intent.putExtras(bundle);
                }
                startActivity(intent);
            }
        });
        */

        listadoNoticias();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerActivityMain, toolbarMainSearch, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerActivityMain.addDrawerListener(toggle);
        toggle.syncState();

        homeFragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        CargadorFragment.cargarFragment(fragmentManager, R.id.container_de_recycler_portada_id, homeFragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item1 = menu.findItem(R.id.menuItem_search);
        MenuItem item2 = menu.findItem(R.id.menuItem_chat);
        final SearchView searchView = (SearchView) item1.getActionView();
        //final ActionMenuItemView chat = (ActionMenuItemView) item2.getActionView();

        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intentChat = new Intent(HomeActivity.this, LoginActivity .class);
                startActivity(intentChat);
                return false;
            }
        });
        //onMenuItemSelected(int posicion, MenuItem);  quiero decir si clickea el icono de chat
        // se pregunta si no esta logueado sino se lo deriva a la pantalla de autenticacion de usuario
        // sino se abre el chat.

        //si toca el icono de lupa realiza la accion planteada actualmente. no se cuales son
        // los metodos predeterminados
/*        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Test", Toast.LENGTH_SHORT).show();
            }
        });*/

        searchView.setQueryHint("Buscar noticias");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                if (!query.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putString(SearchFragment.KET_SEARCH_WORD, query);
                    intent.putExtras(bundle);
                }
                startActivity(intent);
                searchView.setQuery("",false);
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

    public void marquee(String texto) {
        textViewMarquee.setText(texto);
        textViewMarquee.setSelected(true);
        //Toast.makeText(HomeActivity.this,texto,Toast.LENGTH_LONG).show();
    }


    @Override
    public void notificar(Noticia noticia) {
        //Toast.makeText(HomeActivity.this,noticia.getTitulo(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DetalleNoticiaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleNoticiaFragment.KEY_NOTICIA, noticia);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /*
    @Override
    public void notificarCategoria(int categoria) {
        //Toast.makeText(HomeActivity.this,noticia.getTitulo(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CategoriaNoticiasActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(CategoriaNoticiasFragment.KEY_CATEGORIA, categoria);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    */

    @Override
    public void notificarCategoria(String categoria) {
        //Toast.makeText(HomeActivity.this,noticia.getTitulo(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CategoriaNoticiasActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(CategoriaNoticiasFragment.KEY_CATEGORIA, categoria);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void notificarFuente(String fuente) {
        Intent intent = new Intent(this, CategoriaNoticiasActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(CategoriaNoticiasFragment.KEY_FUENTE, fuente);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void listadoNoticias() {
        final NoticiaController noticiaController = new NoticiaController(this);
        noticiaController.obtenerNoticiasController(new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> result) {
                noticias = result;
                String texto = "";
                for (Noticia unanoticia : noticias) {
                    texto = texto + " // " + unanoticia.getTitulo();
                }
                marquee(texto);
            }
        });

        /*
        noticias.add(new Noticia(R.drawable.moda, R.string.titulo_de_cat_moda, R.string.texto_de_cat_moda));
        noticias.add(new Noticia(R.drawable.deportes, R.string.titulo_de_cat_deportes, R.string.texto_de_cat_deportes));
        noticias.add(new Noticia(R.drawable.politica, R.string.titulo_de_cat_politica, R.string.texto_de_cat_politica));
        noticias.add(new Noticia(R.drawable.policiales, R.string.titulo_de_cat_policiales, R.string.texto_de_cat_policiales));
        noticias.add(new Noticia(R.drawable.economia, R.string.titulo_de_cat_economia, R.string.texto_de_cat_economia));
        */
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //notificarCategoria(obtenerIdCategoria(item.getTitle().toString()));
        notificarCategoria(item.getTitle().toString());
        //Toast.makeText(this, item.getTitle().toString() + " " + Integer.toString( item.getItemId()), Toast.LENGTH_SHORT).show();
        drawerActivityMain.closeDrawers();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //txtSearchWord.setText("");
        int size = menuCategorias.getMenu().size();
        for (int i = 0; i < size; i++) {
            menuCategorias.getMenu().getItem(i).setChecked(false);
        }
    }

    /*
    public int obtenerIdCategoria(String categoriaSeleccionada) {
        int categoria=R.string.categoria_deportes;
        switch (categoriaSeleccionada) {
            case "Politica":
                categoria = R.string.categoria_politica;
                break;
            case "Economia":
                categoria = R.string.categoria_economia;
                break;
            case "Policiales":
                categoria = R.string.categoria_policiales;
                break;
            case "Espectaculos":
                categoria = R.string.categoria_espectaculos;
                break;
            case "Tecnologia":
                categoria = R.string.categoria_tecnologia;
                break;
            case "Internacionales":
                categoria = R.string.categoria_internacionales;
                break;
            case "Moda":
                categoria = R.string.categoria_moda;
                break;
            case "Deportes":
                categoria = R.string.categoria_deportes;
                break;
        }
        return categoria;
    }
    */
}


