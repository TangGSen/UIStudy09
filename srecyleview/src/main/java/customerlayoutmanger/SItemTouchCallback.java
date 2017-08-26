package customerlayoutmanger;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import static customerlayoutmanger.CardConfig.MAX_SHOW_COUNT;

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
      //  int dragFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.UP| ItemTouchHelper.DOWN;
        int swipeFlags =  ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.UP| ItemTouchHelper.DOWN;
//        swipeFlags 左右滑动的标志 dragFlags 拖拽标志
        return makeMovementFlags(0,swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (adapterInterface!=null)
        adapterInterface.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //这里为了做循环
        if (adapterInterface!=null)
        adapterInterface.onItemSwipe(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        double maxDistance = recyclerView.getWidth()*0.5f;
        double distance = Math.sqrt(dX*dX +dY *dY);
        double fraction = distance /maxDistance;
        if (fraction >1){
            fraction =1;
        }
        int itemCount = recyclerView.getChildCount();
        for (int i = 0;i<itemCount ;i++){
            View view = recyclerView.getChildAt(i);
            int level = itemCount-i-1;
            if (level >=0){
                if (level <MAX_SHOW_COUNT-1){
                    view.setTranslationY((float) (CardConfig.TRANS_Y_GAP*level-fraction*CardConfig.TRANS_Y_GAP));
                    view.setScaleX((float) (1-CardConfig.SCALE_GAP*level +fraction*CardConfig.SCALE_GAP));
                    view.setScaleY((float) (1-CardConfig.SCALE_GAP*level +fraction*CardConfig.SCALE_GAP));
                }else if(level ==CardConfig.MAX_SHOW_COUNT -1){
                    // 控制的是最下层的Item
                    view.setTranslationY((float) (CardConfig.TRANS_Y_GAP*(level-1)));
                    view.setScaleX((float) (1-CardConfig.SCALE_GAP*(level-1) ));
                    view.setScaleY((float) (1-CardConfig.SCALE_GAP*(level-1)));

                }
            }
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
    //当划过viewHodler 百分比时，触发onswip
    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return 0.5f;
    }
}
