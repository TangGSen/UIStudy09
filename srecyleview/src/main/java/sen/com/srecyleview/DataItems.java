package sen.com.srecyleview;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */

public class DataItems {
    private String text;
    private int colorId;
    private List<DataItems> dataItemses;

    public List<DataItems> getDataItemses() {
        return dataItemses;
    }

    public void setDataItemses(List<DataItems> dataItemses) {
        this.dataItemses = dataItemses;
    }

    public DataItems(String text, int colorId, List<DataItems> dataItemses) {
        this.text = text;
        this.colorId = colorId;
        this.dataItemses = dataItemses;
    }

    public DataItems(String text, int colorId) {
        this.text = text;
        this.colorId = colorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
