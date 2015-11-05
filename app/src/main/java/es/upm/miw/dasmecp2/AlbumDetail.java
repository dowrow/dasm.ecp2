package es.upm.miw.dasmecp2;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import es.upm.miw.dasmecp2.entities.AlbumEntity;
import es.upm.miw.dasmecp2.entities.AlbumRepository;

import static org.apache.commons.lang3.text.WordUtils.capitalize;

public class AlbumDetail extends AppCompatActivity {

    private AlbumEntity albumEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.albumDetailsTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        albumEntity = (AlbumEntity) extras.get("albumEntity");

        final ImageView imageView = (ImageView) findViewById(R.id.itemDetailImage);
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                Picasso.with(getApplicationContext())
                        .load(albumEntity.getBigImageURL())
                        .centerCrop()
                        .resize(imageView.getMeasuredWidth(), imageView.getMeasuredHeight())
                        .into(imageView);
                return true;
            }
        });

        TextView tvTitle = (TextView) findViewById(R.id.itemDetailTitle);
        tvTitle.setText(albumEntity.getName());

        TextView tvSubtitle = (TextView) findViewById(R.id.itemDetailSubtitle);
        tvSubtitle.setText(capitalize(albumEntity.getAlbumType()));
    }

    public void addToFavorites(View view) {
        AlbumRepository repository = new AlbumRepository(getApplicationContext());
        repository.add(albumEntity);
        Toast.makeText(getApplicationContext(), R.string.addedToFavorites, Toast.LENGTH_SHORT).show();
        finish();
    }
}
