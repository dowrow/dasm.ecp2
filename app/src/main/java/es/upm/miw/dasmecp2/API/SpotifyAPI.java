package es.upm.miw.dasmecp2.api;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class SpotifyAPI {

    public static final String SPOTIFY_WEB_API_ENDPOINT = "https://api.spotify.com";

    public SpotifyAPIService getAPIService() {

        // Set the custom client when building adapter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SPOTIFY_WEB_API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        return retrofit.create(SpotifyAPIService.class);
    }
}