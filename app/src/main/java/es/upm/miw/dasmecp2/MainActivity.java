package es.upm.miw.dasmecp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Objects;

import es.upm.miw.dasmecp2.api.SpotifyAPI;
import es.upm.miw.dasmecp2.api.SpotifyAPIService;
import es.upm.miw.dasmecp2.api.TwitterAPI;
import es.upm.miw.dasmecp2.api.models.Albums;
import es.upm.miw.dasmecp2.api.models.AlbumsResponse;
import es.upm.miw.dasmecp2.api.models.Item;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.bar_title);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return (id == R.id.action_settings) || super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void searchAlbums(View view) {
        EditText artistEditText = (EditText) findViewById(R.id.etArtist);
        String artist = artistEditText.getText().toString();

        hideSoftKeyboard(this);

        if (!isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(), R.string.noInternet, Toast.LENGTH_SHORT).show();
            return;
        }

        SpotifyAPI spotifyAPI = new SpotifyAPI();
        SpotifyAPIService apiService = spotifyAPI.getAPIService();
        Call<AlbumsResponse> call_async = apiService.searchAlbums("artist:" + artist);

        // Asíncrona
        call_async.enqueue(new Callback<AlbumsResponse>() {

            @Override
            public void onResponse(Response<AlbumsResponse> response, Retrofit retrofit) {
                AlbumsResponse albumsResponse = response.body();
                Albums albums = albumsResponse.getAlbums();
                ArrayList<Item> items = new ArrayList<>(albums.getItems());
                showAlbums(items);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("Fail", t.toString());
            }
        });


    }

    public void showAlbums(ArrayList<Item> items) {
        ItemArrayAdapter adapter = new ItemArrayAdapter(this, items);
        ListView lvItems = (ListView) findViewById(R.id.lvAlbums);
        lvItems.setAdapter(adapter);
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFavoriteAlbums(MenuItem item) {
        Intent intent = new Intent(this, FavoriteAlbums.class);
        startActivity(intent);
    }
}
