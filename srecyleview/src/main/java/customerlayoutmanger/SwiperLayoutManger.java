package customerlayoutmanger;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/8/24.
 */

public class SwiperLayoutManger extends LayoutManager {
    //屏幕上同时显示item个数



    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {

        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        super.onLayoutChildren(recycler, state);
        //在布局之前，将所有的子View先Detach掉，放入到Scrap缓存中
        detachAndScrapAttachedViews(recycler);

        int itemCount = getItemCount();
        int initPostion ;
        if (itemCount < CardConfig.MAX_SHOW_COUNT){
            initPostion =0;

        }else{
            initPostion =itemCount -CardConfig.MAX_SHOW_COUNT;
        }

        for(int position = initPostion;position <itemCount;position++){
            //从缓存取出view
            View view = recycler.getViewForPosition(position);
            addView(view);
            measureChild(view,0,0);
            //getWidth getHeigeht 是父容器RecycleView 的宽高
            int widthSpace = getWidth() -getDecoratedMeasuredWidth(view);
            int heightSace = getHeight() -getDecoratedMeasuredHeight(view);

            //最后，将View布局,居中显示
            layoutDecorated(view,widthSpace/2,heightSace/2,
                    widthSpace/2+getDecoratedMeasuredWidth(view),
                    heightSace/2+getDecoratedMeasuredHeight(view));

            //将view 层叠，以及变形
            int level = itemCount -position -1 ;
            if (level>=0){
                view.setScaleX(1 -CardConfig.SCALE_GAP*level);
                view.setScaleY(1 -CardConfig.SCALE_GAP*level);
                if (level <CardConfig.MAX_SHOW_COUNT-1){
                    view.setTranslationY(CardConfig.TRANS_Y_GAP*(level));
                }else if(level ==CardConfig.MAX_SHOW_COUNT-1){
                    //最底层跟上一层保持一致，这样在滑动时生成的最后一层不会有太突然的感觉，被上层覆盖着
                    view.setTranslationY(CardConfig.TRANS_Y_GAP*(level-1));
                }

            }


        }

    }
}
