package sen.com.uistudy09;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

/**
 * Created by Administrator on 2017/8/22.
 */

public class PathItem {
    private Path path;

    private int color;


    public PathItem(Path path, int color) {
        this.path = path;
        this.color = color;
    }

    //自己来画
    public void drawPath(Canvas canvas, Paint paint, boolean isSelecte) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        if (isSelecte) {
            //绘制有阴影的底部
            paint.setStrokeWidth(2);
            paint.setColor(Color.BLUE);
            paint.setShadowLayer(6, 0, 0, 0xffffff);
        } else {
            paint.clearShadowLayer();
            paint.setStrokeWidth(1);
        }
        canvas.drawPath(path, paint);
    }

    //点击的坐标是否在
    public boolean isTouch(int x, int y) {
        RectF rectF = new RectF();
        //将区域放在rectf
        path.computeBounds(rectF,true);
        Region region = new Region();
        region.setPath(path,new Region((int) rectF.left,(int)rectF.top,(int)rectF.right,(int)rectF.bottom));

        return region.contains(x,y);
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
