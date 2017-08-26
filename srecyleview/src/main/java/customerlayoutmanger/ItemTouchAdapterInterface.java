package customerlayoutmanger;

/**
 * Created by Administrator on 2017/8/23.
 */

public interface ItemTouchAdapterInterface {
    void onItemMove(int fromPos, int toPos);

    void onItemSwipe(int position);
}
