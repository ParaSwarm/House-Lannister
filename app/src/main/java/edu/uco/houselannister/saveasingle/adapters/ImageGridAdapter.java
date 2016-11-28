package edu.uco.houselannister.saveasingle.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.R;

public class ImageGridAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<Bitmap> imageList = new ArrayList<>();

    // Constructor
    public ImageGridAdapter(Context c, ArrayList<Bitmap> list) {
        mContext = c;
        imageList = list;
    }

    public int getCount() {
        return imageList.size();
    }

//    public void addToList(String strPath) {
//        this.imageList.add(strPath);
//        this.notifyDataSetChanged();
//    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 340));
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(imageList.get(position));
//        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    public Integer[] mThumbIds = {

            R.drawable.header
    };
}
