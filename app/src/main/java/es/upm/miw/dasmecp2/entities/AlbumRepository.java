package es.upm.miw.dasmecp2.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static es.upm.miw.dasmecp2.entities.AlbumContract.albumTable;
public class AlbumRepository extends SQLiteOpenHelper {

    private static final String DATABASE_FILENAME = "albums.db";

    private static final int DATABASE_VERSION = 1;

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     */
    public AlbumRepository(Context context) {
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sentenciaSQL = "CREATE TABLE " + albumTable.TABLE_NAME + "("
                + albumTable.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + albumTable.COL_NAME_NAME + " TEXT, "
                + albumTable.COL_NAME_ALBUM_TYPE + " TEXT, "
                + albumTable.COL_NAME_SMALL_IMAGE_URL+ " TEXT"
                + albumTable.COL_NAME_BIG_IMAGE_URL + " TEXT, "
                + ")";
        db.execSQL(sentenciaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sentenciaSQL = "DROP TABLE IF EXISTS " + albumTable.TABLE_NAME;
        db.execSQL(sentenciaSQL);
        onCreate(db);
    }

    public long add(AlbumEntity albumEntity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(albumTable.COL_NAME_NAME, albumEntity.getName());
        valores.put(albumTable.COL_NAME_ALBUM_TYPE, albumEntity.getAlbumType());
        valores.put(albumTable.COL_NAME_SMALL_IMAGE_URL, albumEntity.getSmallImageURL());
        valores.put(albumTable.COL_NAME_BIG_IMAGE_URL, albumEntity.getBigImageURL());
        return db.insert(albumTable.TABLE_NAME, null, valores);
    }

    /**
     * Recupera todos los futbolistas de la tabla ordenados por ID
     * @see #getAll(String , boolean)
     * @return Array de futbolistas ordenados por ID
     */
    public ArrayList<AlbumEntity> getAll() {
        return getAll(albumTable.COL_NAME_ID, true);
    }

    /**
     * Recupera todos los futbolistas de la tabla ordenados por la columna proporcionada
     * @return Array de futbolistas ordenados
     */
    public ArrayList<AlbumEntity> getAll(String columna, boolean ordenAscendente) {
        ArrayList<AlbumEntity> resultado = new ArrayList<>();
        String consultaSQL = "SELECT * FROM " + albumTable.TABLE_NAME
                + " ORDER BY " + columna + (ordenAscendente ? " ASC" : " DESC");

        // Accedo a la DB en modo lectura
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                AlbumEntity albumEntity = new AlbumEntity(
                        cursor.getInt(cursor.getColumnIndex(albumTable.COL_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex(albumTable.COL_NAME_NAME)),
                        cursor.getString(cursor.getColumnIndex(albumTable.COL_NAME_ALBUM_TYPE)),
                        cursor.getString(cursor.getColumnIndex(albumTable.COL_NAME_SMALL_IMAGE_URL)),
                        cursor.getString(cursor.getColumnIndex(albumTable.COL_NAME_BIG_IMAGE_URL))
                );
                resultado.add(albumEntity);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return resultado;
    }

    /**
     * Devuelve el número de futbolistas de la tabla
     * @return Número de futbolistas
     */
    public long count() {
        String consultaSQL = "SELECT COUNT(*) FROM " + albumTable.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);
        cursor.moveToFirst();
        long numero = cursor.getLong(0);
        cursor.close();
        return numero;
    }

    /**
     * Elimina todos los futbolistas
     * @return long Número de filas eliminadas
     */
    public long deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(albumTable.TABLE_NAME, "1", null);
    }

    /**
     * Recupera un futbolista por ID
     * @param id Identificador del futbolista
     * @return  futbolistas
     */
    public AlbumEntity getAlbumEntityByID(int id) {
        String consultaSQL = "SELECT * FROM " + albumTable.TABLE_NAME
                + " WHERE " + albumTable.COL_NAME_ID + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        AlbumEntity albumEntity = null;
        Cursor cursor = db.rawQuery(
                consultaSQL,
                new String[]{ String.valueOf(id) }
        );

        if (cursor.moveToFirst()) {
            albumEntity = new AlbumEntity(
                    cursor.getInt(cursor.getColumnIndex(albumTable.COL_NAME_ID)),
                    cursor.getString(cursor.getColumnIndex(albumTable.COL_NAME_NAME)),
                    cursor.getString(cursor.getColumnIndex(albumTable.COL_NAME_ALBUM_TYPE)),
                    cursor.getString(cursor.getColumnIndex(albumTable.COL_NAME_SMALL_IMAGE_URL)),
                    cursor.getString(cursor.getColumnIndex(albumTable.COL_NAME_BIG_IMAGE_URL))
                    );
            cursor.close();
        }

        return albumEntity;
    }

}




