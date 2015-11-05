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

import es.upm.miw.dasmecp2.R;
import es.upm.miw.dasmecp2.api.models.Item;
import es.upm.miw.dasmecp2.entities.AlbumEntity;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> items;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, R.layout.layout_listview_items, items);
        this.items = items;
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

        final Item item = this.items.get(position);

        Log.i("ADAPTER", items.toString());

        if (item != null) {

            ImageView itemImage = (ImageView) convertView.findViewById(R.id.itemImage);
            Picasso.with(context).load(item.getImages().get(item.getImages().size() - 1).getUrl()).into(itemImage);

            TextView itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
            itemTitle.setText(item.getName());

            TextView itemSubtitle = (TextView) convertView.findViewById(R.id.itemSubtitle);
            itemSubtitle.setText(WordUtils.capitalize(item.getAlbumType()));

        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlbumDetail.class);
                intent.putExtra("albumEntity", new AlbumEntity(item));
                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
