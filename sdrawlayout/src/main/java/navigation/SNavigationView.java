package navigation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/8/27.
 */

public class SNavigationView extends NavigationView {

    private Paint paint;
    private Path path;

    public SNavigationView(Context context) {
        this(context,null);
    }

    public SNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        path = new Path();
        path.moveTo(0,0);
        path.lineTo(getWidth(),0);
        path.lineTo(getWidth(),getHeight());
        path.lineTo(0,getHeight());
        path.close();
        paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap bitmap =((BitmapDrawable) getBackground()).getBitmap();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }
}
