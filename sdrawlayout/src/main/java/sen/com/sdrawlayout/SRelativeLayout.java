package sen.com.sdrawlayout;

import android.graphics.Color;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/8/26.
 * 主要用来画可变的边界背景的
 */

public class SRelativeLayout extends RelativeLayout {


    private SLinearLayout mLinearLayout;
    private final SView sView;

    public SRelativeLayout(SLinearLayout linearLayout) {
        super(linearLayout.getContext());
        mLinearLayout = linearLayout;
        setLayoutParams(mLinearLayout.getLayoutParams());
        //先添加背景view
        sView = new SView(linearLayout.getContext());
        //把mLinearLayout 的颜色设置给SView ,然后置为透明
        sView.setColor(mLinearLayout.getBackground());
        mLinearLayout.setBackgroundColor(Color.TRANSPARENT);
        addView(sView,new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        addView(mLinearLayout,mLinearLayout.getLayoutParams());

    }


    public void setTouchY(float dxY, float slideOffset) {
        sView.setTouchY(dxY,slideOffset);
        mLinearLayout.setTouchY(dxY,slideOffset);
    }
}
