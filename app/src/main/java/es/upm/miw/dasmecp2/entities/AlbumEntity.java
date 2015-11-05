package es.upm.miw.dasmecp2.entities;


import android.os.Parcel;
import android.os.Parcelable;

import es.upm.miw.dasmecp2.api.models.Item;

public class AlbumEntity implements Parcelable {

    private int id;

    private String name;

    private String albumType;

    private String smallImageURL;

    private String bigImageURL;

    public AlbumEntity(int id, String name, String albumType, String smallImageURL, String bigImageURL) {
        this.id = id;
        this.name = name;
        this.albumType = albumType;
        this.smallImageURL = smallImageURL;
        this.bigImageURL = bigImageURL;
    }

    public AlbumEntity (Item item) {
        this(item.getId().hashCode(),
                item.getName(),
                item.getAlbumType(),
                item.getImages().get(item.getImages().size()).getUrl(),
                item.getImages().get(0).getUrl());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getBigImageURL() {
        return bigImageURL;
    }

    public void setBigImageURL(String bigImageURL) {
        this.bigImageURL = bigImageURL;
    }

    @Override
    public String toString() {
        return "AlbumEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", albumType='" + albumType + '\'' +
                ", smallImageURL='" + smallImageURL + '\'' +
                ", bigImageURL='" + bigImageURL + '\'' +
                '}';
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(albumType);
        dest.writeString(smallImageURL);
        dest.writeString(bigImageURL);
    }

    public static final Parcelable.Creator<AlbumEntity> CREATOR
            = new Parcelable.Creator<AlbumEntity>() {
        public AlbumEntity createFromParcel(Parcel in) {
            return new AlbumEntity(in);
        }

        public AlbumEntity[] newArray(int size) {
            return new AlbumEntity[size];
        }
    };

    private AlbumEntity(Parcel origen) {
        this.id = origen.readInt();
        this.name = origen.readString();
        this.albumType = origen.readString();
        this.smallImageURL = origen.readString();
        this.bigImageURL = origen.readString();
    }
}
