package customerlayoutmanger;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import sen.com.srecyleview.DataItems;
import sen.com.srecyleview.R;

/**
 * Created by Administrator on 2017/8/23.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemSViewHodler> implements ItemTouchAdapterInterface {
    private  List<DataItems> dataItemses;
    private  Context context;

    public ItemAdapter(Context context, List<DataItems> dataItemses) {
        this.context = context;
        this.dataItemses =dataItemses;
    }


    @Override
    public ItemSViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.rv_item_layotmanger, parent, false);
        ItemSViewHodler viewHolder = new ItemSViewHodler(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemSViewHodler holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return dataItemses.size();
    }



    class ItemSViewHodler extends RecyclerView.ViewHolder{
        public TextView textView;
        public View content_layout;
        public ItemSViewHodler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_page1);
            content_layout = itemView.findViewById(R.id.content_layout);
        }

        public void bind(int position){
            DataItems dataItems =dataItemses.get(position);
            textView.setText(dataItems.getText());
            content_layout.setBackgroundDrawable(context.getResources().getDrawable(dataItems.getColorId()));
        }
    }


    @Override
    public void onItemMove(int fromPos, int toPos) {
        //将集合中交换位置
        Collections.swap(dataItemses,fromPos,toPos);
        notifyItemMoved(fromPos,toPos);
    }

    @Override
    public void onItemSwipe(int position) {

        DataItems dataItem =dataItemses.remove(position);
        dataItemses.add(0,dataItem);
        notifyItemMoved(position,0);
    }
}
