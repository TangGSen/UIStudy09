package itemtouchdelete;

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
                R.layout.item_delete_layout, parent, false);
        ItemSViewHodler viewHolder = new ItemSViewHodler(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemSViewHodler holder,  int position) {
       // holder.bind(position);
        DataItems dataItems =dataItemses.get(position);
        holder.textView.setText(dataItems.getText());
        holder.content_layout.setBackgroundDrawable(context.getResources().getDrawable(dataItems.getColorId()));
        holder.view_action_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSwipe(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItemses.size();
    }



    class ItemSViewHodler extends RecyclerView.ViewHolder{
        public TextView textView ,view_action_delete;
        public View content_layout,view_action_container;
        public ItemSViewHodler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_page1);
            content_layout = itemView.findViewById(R.id.content_layout);
            view_action_container = itemView.findViewById(R.id.view_action_container);
            view_action_delete = (TextView) itemView.findViewById(R.id.view_action_delete);
            //目前设置为gone 为了内容展示在前方不可点击,在itemTouchHelperCallback 设置可见
            view_action_delete.setVisibility(View.INVISIBLE);
        }

//        public void bind(final int position){
//            DataItems dataItems =dataItemses.get(position);
//            textView.setText(dataItems.getText());
//            content_layout.setBackgroundDrawable(context.getResources().getDrawable(dataItems.getColorId()));
//            view_action_delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemSwipe(position);
//                }
//            });
//        }
    }


    @Override
    public void onItemMove(int fromPos, int toPos) {
        //将集合中交换位置
        Collections.swap(dataItemses,fromPos,toPos);
        notifyItemMoved(fromPos,toPos);
    }

    @Override
    public void onItemSwipe(int position) {
        dataItemses.remove(position);
        notifyItemRemoved(position);
    }
}
