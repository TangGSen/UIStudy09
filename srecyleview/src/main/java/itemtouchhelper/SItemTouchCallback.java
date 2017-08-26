package itemtouchhelper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Administrator on 2017/8/23.
 */

public class SItemTouchCallback extends ItemTouchHelper.Callback {
    private final ItemTouchAdapterInterface adapterInterface;

    public SItemTouchCallback(ItemTouchAdapterInterface adapterInterface){
        this.adapterInterface = adapterInterface;
    }
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT ;
//        swipeFlags 左右滑动的标志 dragFlags 拖拽标志
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (adapterInterface!=null)
        adapterInterface.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (adapterInterface!=null)
        adapterInterface.onItemSwipe(viewHolder.getAdapterPosition());
    }


}
