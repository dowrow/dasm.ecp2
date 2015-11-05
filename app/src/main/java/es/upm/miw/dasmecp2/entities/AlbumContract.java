package es.upm.miw.dasmecp2.entities;

import android.provider.BaseColumns;

/**
 * Created by dowrow on 5/11/15.
 */
public abstract class AlbumContract {

    public AlbumContract() {}

    public static class albumTable implements BaseColumns {

        public static final String TABLE_NAME = "albums";

        public static final String COL_NAME_ID = _ID;
        public static final String COL_NAME_NAME = "name";
        public static final String COL_NAME_ALBUM_TYPE = "album_type";
        public static final String COL_NAME_BIG_IMAGE_URL = "big_image_url";
        public static final String COL_NAME_SMALL_IMAGE_URL = "small_image_url";

    }
}
