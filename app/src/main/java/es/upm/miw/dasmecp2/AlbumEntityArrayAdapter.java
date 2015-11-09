package es.upm.miw.dasmecp2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;

import es.upm.miw.dasmecp2.api.TwitterAPI;
import es.upm.miw.dasmecp2.entities.AlbumEntity;
import es.upm.miw.dasmecp2.entities.AlbumRepository;

public class AlbumEntityArrayAdapter extends ArrayAdapter<AlbumEntity> {

    private Context context;
    private ArrayList<AlbumEntity> albumEntities;
    private AlbumRepository repository;

    public AlbumEntityArrayAdapter(Context context, ArrayList<AlbumEntity> albumEntitys) {
        super(context, R.layout.layout_listview_items, albumEntitys);
        this.albumEntities = albumEntitys;
        this.context = context;
        this.repository = new AlbumRepository(getContext());
        setNotifyOnChange(true);
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_listview_favorites, null);
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

            ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.actionDelete);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Show toast
                    Toast.makeText(getContext(), R.string.favoriteDeleted, Toast.LENGTH_SHORT).show();

                    // Delete from repository
                    repository.deleteAlbumEntityByID(albumEntities.get(position).getId());

                    // Delete from array
                    albumEntities.remove(position);

                    // Update view
                    notifyDataSetChanged();
                }
            });

            Button shareButton = (Button) convertView.findViewById(R.id.actionShare);
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Show toast
                    Toast.makeText(getContext(), R.string.favoriteShared, Toast.LENGTH_LONG).show();
                    TwitterAPI twitterAPI = new TwitterAPI();
                    twitterAPI.updateStatus("You should listen to: " + albumEntity.getName() + " (" + albumEntity.getAlbumType() + ") - " + albumEntity.getBigImageURL());
                }
            });
        }

        return convertView;
    }



}
