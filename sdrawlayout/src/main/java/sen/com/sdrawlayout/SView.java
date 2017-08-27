package sen.com.sdrawlayout;

import android.content.Context;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/26.
 */

public class SView extends View {
    private Paint mPaint;
    private Path mPath;
    private BitmapDrawable bitmapDrawable;

    public SView(Context context) {
        this(context, null);
    }

    public SView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    public void setTouchY(float dxY, float present) {
        mPath.reset();
        float width = getWidth() * present;
        float height = getHeight();
        mPath.lineTo(width / 2, -height / 8);
        mPath.quadTo(width , dxY, width / 2, height + height / 8);
        mPath.lineTo(0, height);

        mPath.close();
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (bitmapDrawable != null) {
            BitmapShader shader = new BitmapShader(bitmapDrawable.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mPaint.setShader(shader);
        }
        canvas.drawPath(mPath, mPaint);
    }

    public void setColor(Drawable color) {
        if (color instanceof ColorDrawable) {
            mPaint.setColor(((ColorDrawable) color).getColor());
        } else if (color instanceof BitmapDrawable) {
            bitmapDrawable = (BitmapDrawable) color;
        }

    }
}
