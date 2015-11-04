package es.upm.miw.dasmecp2.api;

import es.upm.miw.dasmecp2.api.models.AlbumsResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SpotifyAPIService {

    @GET("/v1/search?type=album")
    Call<AlbumsResponse> searchAlbums(@Query("q") String q);



}