package itemtouchdelete;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import itemtouchdelete.itemtouchhelperextend.ItemTouchHelper;

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
//        if (adapterInterface!=null)
//        adapterInterface.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//        if (adapterInterface!=null)
//        adapterInterface.onItemSwipe(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
       if (dY!=0 && dX==0) {
           //只有这样才回调父类方法
           super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
       }
        //为了解耦，可以把这段代码放在ItemAdapter
       if(viewHolder instanceof ItemAdapter.ItemSViewHodler){
            //这个可以实现，类似qq 侧滑那样，不是全屏那种，可以修改dx
//           if (dX<-((ItemAdapter.ItemSViewHodler) viewHolder).content_layout.getWidth()){
//               dX = -((ItemAdapter.ItemSViewHodler) viewHolder).content_layout.getWidth();
//           }
           ItemAdapter.ItemSViewHodler mViewHodler=  ((ItemAdapter.ItemSViewHodler) viewHolder);
           if (mViewHodler.content_layout.getX()<=-mViewHodler.view_action_container.getWidth()){
//               onItemClose 这里可以判断，也可以增加OnItemClose,以后增加
               mViewHodler.view_action_delete.setVisibility(View.VISIBLE);
           }else{
               mViewHodler.view_action_delete.setVisibility(View.INVISIBLE);
           }
           mViewHodler.content_layout.setTranslationX(dX);
       }

    }

    @Override
    public void onItemClose(RecyclerView.ViewHolder mPreSelected) {
        if(mPreSelected !=null){
            ItemAdapter.ItemSViewHodler mViewHodler=  ((ItemAdapter.ItemSViewHodler) mPreSelected);
            mViewHodler.view_action_delete.setVisibility(View.INVISIBLE);
        }
    }
}
