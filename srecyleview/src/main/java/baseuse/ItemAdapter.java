package baseuse;

import android.content.Context;
import android.view.View;

import java.util.Collections;
import java.util.List;

import baseuse.commadapter.RecycleCommonAdapter;
import baseuse.commadapter.SViewHolder;
import itemtouchhelper.ItemTouchAdapterInterface;
import sen.com.srecyleview.DataItems;
import sen.com.srecyleview.R;

/**
 * Created by Administrator on 2017/8/23.
 */

public class ItemAdapter extends RecycleCommonAdapter<DataItems> implements ItemTouchAdapterInterface {
    private  List<DataItems> dataItemses;
    private  Context context;

    public ItemAdapter(Context context, List<DataItems> dataItemses, int layoutId) {
        super(context, dataItemses, layoutId);
        this.context = context;
        this.dataItemses = dataItemses;
    }


    @Override
    public void bindItemData(SViewHolder holder, DataItems itemData, int position) {
        holder.setText(R.id.tv_page1,itemData.getText());
        View content_layout = holder.getView(R.id.content_layout);
        content_layout.setBackgroundDrawable(context.getResources().getDrawable(itemData.getColorId()));
        String path = "http://img0.imgtn.bdimg.com/it/u=2868202968,2822162266&fm=26&gp=0.jpg";
        holder.setImage(R.id.imageView,new ImageLoader(path));
    }

    @Override
    public void onItemMove(int fromPos, int toPos) {
        //将集合中交换位置
        Collections.swap(dataItemses, fromPos, toPos);
        notifyItemMoved(fromPos, toPos);
    }

    @Override
    public void onItemSwipe(int position) {
        dataItemses.remove(position);
        notifyItemRemoved(position);
    }
}
