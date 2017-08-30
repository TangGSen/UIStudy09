package baseuse.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/8/30.
 */

public class LineanItemDecoration extends  RecyclerView.ItemDecoration{

    private  int mSpace;
    private final Paint mPaint;

    public LineanItemDecoration(Context context, int space) {
        this.mSpace = px2dp(space,context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#FF5722"));
    }

    /**
     * 这个方法是留出分割线的位置
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //最后一个不需要分割线的
        int position = parent.getChildAdapterPosition(view);
        if (position!=0) {
            //不画第一个的顶部，也可以完成
            outRect.top =mSpace ;
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right =parent.getWidth()- parent.getPaddingRight();
        for (int i = 1; i < count; i++) {
            //分割线的底部是在每个child的头部
            rect.bottom =parent.getChildAt(i).getTop();
            rect.top = rect.bottom -mSpace;
            c.drawRect(rect,mPaint);
        }
    }
    public int px2dp(float px, Context context){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
