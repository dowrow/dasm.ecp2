package es.upm.miw.dasmecp2.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class AlbumsResponse {

    @SerializedName("albums")
    @Expose
    private Albums albums;

    /**
     *
     * @return
     * The albums
     */
    public Albums getAlbums() {
        return albums;
    }

    /**
     *
     * @param albums
     * The albums
     */
    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

}