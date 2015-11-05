package es.upm.miw.dasmecp2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.upm.miw.dasmecp2.R;
import es.upm.miw.dasmecp2.entities.AlbumEntity;
import es.upm.miw.dasmecp2.entities.AlbumRepository;

public class FavoriteAlbums extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_albums);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AlbumRepository repository = new AlbumRepository(getApplicationContext());
        ArrayList<AlbumEntity> all = repository.getAll();
        AlbumEntityArrayAdapter adapter = new AlbumEntityArrayAdapter(getApplicationContext(), all);
        ListView lvFavoriteAlbums = (ListView) findViewById(R.id.lvFavoriteAlbums);
        lvFavoriteAlbums.setAdapter(adapter);
    }

}
