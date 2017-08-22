package sen.com.uistudy09;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Administrator on 2017/8/21.
 */

public class SenMapView extends View {
    private static final int DRAW_MAP = 1;
    private Context context;
    private List<PathItem> pathItemList;
    private float scale = 1.3f;
    private float minWidth;
    private float minHeight;
    //    private int[] colorArray = new int[]{Color.parseColor("#FF7373"),
//            Color.parseColor("#FF9640"),
//            Color.parseColor("#992667"),
//            Color.parseColor("#00CC00")};
    private int[] colorArray = new int[]{0xFF239BD7, 0XFF30A9E5, 0XFF80CBF1, 0XEEEEEEFF};
    private PathItem selecteItem;
    private Paint paint;
    private GestureDetectorCompat gestureDetectorCompat;

    public SenMapView(Context context) {
        this(context, null);
    }

    public SenMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        pathItemList = new ArrayList<>();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        minWidth = context.getResources().getDimension(R.dimen.min_width);
        minHeight = context.getResources().getDimension(R.dimen.min_heigth);
        thread.start();
        gestureDetectorCompat = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                handleTouch(e.getX(), e.getY());
                return true;
            }
        });
    }

    private void handleTouch(float x, float y) {
        if (pathItemList != null) {
            selecteItem = null;
            for (PathItem item : pathItemList) {
                //除scacle 才正确
                if (item.isTouch((int) (x / scale), (int) (y / scale))) {
                    selecteItem = item;
                    break;
                }
                ;
            }
            if (selecteItem != null) {
                postInvalidate();
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DRAW_MAP:
                    if (pathItemList != null && pathItemList.size() > 0) {


                        postInvalidate();
                    }
                    break;
            }


        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        float viewWidth = widthSize;
        float viewHeight = heightSize;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                viewWidth = widthSize > minWidth ? widthSize : minWidth;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                viewWidth = minWidth;
                break;
        }
        //按照比例 最后宽度/minWidth = 参考高度/minHeigth; 根据最后宽度来计算参考的高度
        int cHeight = (int) (minHeight* viewWidth/minWidth);

        switch (heightSize) {
            case MeasureSpec.EXACTLY:
                viewHeight = heightSize > cHeight ? cHeight : heightSize;
                break;
            //match_content wrap_content
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                viewHeight = minHeight > cHeight ?cHeight  : minHeight;

                break;
        }
        scale = heightSize/cHeight;
        setMeasuredDimension(MeasureSpec.makeMeasureSpec((int) viewWidth, widthMode),
                MeasureSpec.makeMeasureSpec((int) viewHeight, heightMode));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (pathItemList != null) {

            canvas.save();
            canvas.scale(scale, scale);

            for (PathItem pathItem : pathItemList) {
                //绘制未被选择的item
                if (pathItem != selecteItem) {
                    pathItem.drawPath(canvas, paint, false);
                }
            }

            //绘制被选择的item
            if (selecteItem != null) {
                selecteItem.drawPath(canvas, paint, true);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetectorCompat.onTouchEvent(event);
    }

    //线程解析
    Thread thread = new Thread() {
        @Override
        public void run() {

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            try {
                builder = builderFactory.newDocumentBuilder();
                InputStream inputStream = context.getResources().openRawResource(R.raw.chinahigh);
                Document document = builder.parse(inputStream);
                Element rootElement = document.getDocumentElement();
                NodeList pathList = rootElement.getElementsByTagName("path");
                int size = 0;
                if (pathList != null) {
                    size = pathList.getLength();
                }
                for (int i = 0; i < size; i++) {
                    Element item = (Element) pathList.item(i);
                    String androidPath = item.getAttribute("android:pathData");
                    Path path = PathParser.createPathFromPathData(androidPath);
                    pathItemList.add(new PathItem(path, colorArray[i % colorArray.length]));
                }
                handler.sendEmptyMessage(DRAW_MAP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
