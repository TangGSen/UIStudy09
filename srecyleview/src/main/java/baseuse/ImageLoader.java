package baseuse;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import baseuse.commadapter.SViewHolder;

/**
 * Author : 唐家森
 * Version: 1.0
 * On     : 2017/8/30 22:22
 * Des    :
 */

public class ImageLoader extends SViewHolder.HolderImageLoader {
    public ImageLoader(String path) {
        super(path);
    }

    @Override
    public void loadImage(ImageView imageView, String path) {
        Glide.with(imageView.getContext()).load(path).into(imageView);
    }
}
