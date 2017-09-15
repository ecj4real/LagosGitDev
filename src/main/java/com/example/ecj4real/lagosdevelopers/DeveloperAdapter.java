package com.example.ecj4real.lagosdevelopers;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DeveloperAdapter extends ArrayAdapter<Developer> {
    private static class ViewHolder {
        public ImageView ivAvatar;
        public TextView tvUsername;
    }
    public DeveloperAdapter(Context context, ArrayList<Developer> aDevelopers) {
        super(context, 0, aDevelopers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Developer developer = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.developer_detail, parent, false);
            viewHolder.ivAvatar = (ImageView)convertView.findViewById(R.id.ivAvatar);
            viewHolder.tvUsername = (TextView)convertView.findViewById(R.id.tvUsername);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvUsername.setText(developer.getLogin());

        Picasso.with(getContext()).load(Uri.parse(developer.getAvatar())).error(R.drawable.dev1).into(viewHolder.ivAvatar);

        return convertView;
    }
}
