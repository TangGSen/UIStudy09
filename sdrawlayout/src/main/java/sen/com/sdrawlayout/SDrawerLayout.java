package sen.com.sdrawlayout;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/8/26.
 */

public class SDrawerLayout extends DrawerLayout implements DrawerLayout.DrawerListener {
    private SLinearLayout mSLinearLayout;
    private View contentView;
    private SRelativeLayout relativeLayout;
    private float dxY;
    private float slideOffset;

    public SDrawerLayout(Context context) {
        super(context);
    }

    public SDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view instanceof SLinearLayout) {
                mSLinearLayout = (SLinearLayout) view;
            } else {
                //内容布局
                contentView = view;
            }
        }

        //首先先把xml 的view移除掉，给RelateLayout使用
        removeView(mSLinearLayout);
        relativeLayout = new SRelativeLayout(mSLinearLayout);
        //这个是偷梁换柱，明明在xml 定义的是Linealayout
        addView(relativeLayout);
        addDrawerListener(this);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        dxY = ev.getY();
        if (ev.getAction() == MotionEvent.ACTION_UP) {
//            closeDrawers();
            mSLinearLayout.motionUp();
            return super.dispatchTouchEvent(ev);
        }

        //侧滑菜单没有完全打开时，是不拦截的，如果这时拦截的话，onDrawerSlide就会响应了
        if (slideOffset < 1) {
            return super.dispatchTouchEvent(ev);
        } else {
//            ==1，内容区域不在偏移
            relativeLayout.setTouchY(dxY, slideOffset);

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        this.slideOffset = slideOffset;
        relativeLayout.setTouchY(dxY, slideOffset);
        //对内容进行偏移
        float contentOffset = drawerView.getWidth() * slideOffset / 2;
        contentView.setTranslationX(contentOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
