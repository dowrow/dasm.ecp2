package es.upm.miw.dasmecp2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;

import es.upm.miw.dasmecp2.entities.AlbumEntity;

public class AlbumEntityArrayAdapter extends ArrayAdapter<AlbumEntity> {

    private Context context;
    private ArrayList<AlbumEntity> albumEntities;

    public AlbumEntityArrayAdapter(Context context, ArrayList<AlbumEntity> albumEntitys) {
        super(context, R.layout.layout_listview_items, albumEntitys);
        this.albumEntities = albumEntitys;
        this.context = context;
        setNotifyOnChange(true);
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_listview_items, null);
        }

        final AlbumEntity albumEntity = this.albumEntities.get(position);

        Log.i("ADAPTER", albumEntities.toString());

        if (albumEntity != null) {

            ImageView albumEntityImage = (ImageView) convertView.findViewById(R.id.itemImage);
            Picasso.with(context).load(albumEntity.getSmallImageURL()).into(albumEntityImage);

            TextView albumEntityTitle = (TextView) convertView.findViewById(R.id.itemTitle);
            albumEntityTitle.setText(albumEntity.getName());

            TextView albumEntitySubtitle = (TextView) convertView.findViewById(R.id.itemSubtitle);
            albumEntitySubtitle.setText(WordUtils.capitalize(albumEntity.getAlbumType()));

        }

        return convertView;
    }

}
