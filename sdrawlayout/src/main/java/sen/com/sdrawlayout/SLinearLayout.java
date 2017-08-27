package sen.com.sdrawlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/8/26.
 */

public class SLinearLayout extends LinearLayout {
    private boolean isOpen;
    private float maxTraletionX;

    public SLinearLayout(Context context) {
        super(context);
    }

    public SLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SLinearLayoutX);
            maxTraletionX = typedArray.getDimension(R.styleable.SLinearLayoutX_maxTraletionX, 0);
            typedArray.recycle();
        }
    }

    //遍历子控件进行偏移
    public void setTouchY(float dxY, float slideOffset) {
        isOpen = slideOffset == 1;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            //要判断手指坐落那个控件，手指up ,要响应点击事件
            //只要判断上下方向，不需要判断左右了
            //回调响应事件不在这里，以为这个是move 事件传来的，由上层
            boolean isHove = isOpen && dxY > view.getTop() && dxY < view.getBottom() && view.getVisibility() == View.VISIBLE;
            view.setSelected(isHove);
            //偏移view
            traletionView(getParent(), view, dxY, slideOffset);
        }
    }

    private void traletionView(ViewParent parent, View view, float dxY, float slideOffset) {
        //控件中心点
        int centerY = view.getTop() + view.getHeight() / 2;
        float distance = Math.abs(dxY - centerY);
        float fection = distance *3/ getHeight(); //偏移比例
        float distanceX = maxTraletionX * (1 - fection);
        view.setTranslationX(distanceX);

    }

    public void motionUp() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            boolean isClicke = isOpen && view.isSelected();
            view.performClick();
        }
    }
}
