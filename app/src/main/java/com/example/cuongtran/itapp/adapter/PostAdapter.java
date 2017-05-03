package com.example.cuongtran.itapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuongtran.itapp.R;
import com.example.cuongtran.itapp.model.Post;

import java.util.List;

/**
 * Created by linh.tran1 on 26/04/2017.
 */

public class PostAdapter extends ArrayAdapter<Post> {

    Context context;
    public PostAdapter(Context context, int resourceId,
                          List<Post> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtName;
        TextView txtContent;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Post rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.title);
            holder.txtContent = (TextView) convertView.findViewById(R.id.message);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtName.setText(rowItem.getUserName());
        holder.txtContent.setText(rowItem.getContent());
        holder.imageView.setImageResource(rowItem.getImageId());

        return convertView;
    }
}
