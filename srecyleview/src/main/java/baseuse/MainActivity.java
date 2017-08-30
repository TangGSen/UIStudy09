package baseuse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import baseuse.itemdecoration.GridItemDecoration;
import itemtouchhelper.SItemTouchCallback;
import sen.com.srecyleview.DataItems;
import sen.com.srecyleview.R;
import sen.com.srecyleview.SRecyclerView;

public class MainActivity extends AppCompatActivity {

    private SRecyclerView recyclerView;
    private int[] colorArray = new int[]{R.drawable.bg_page1, R.drawable.bg_page2, R.drawable.bg_page3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_helper);

        recyclerView = (SRecyclerView) findViewById(R.id.recycleView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        List<DataItems> dataItemses = new ArrayList<>();
        for (int i=0 ;i<20;i++){
            dataItemses.add(new DataItems("SItem"+i, colorArray[i % colorArray.length]));
        }

        baseuse.ItemAdapter adapter = new baseuse.ItemAdapter(this,dataItemses,R.layout.rv_item);
        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new LineanItemDecorationV2(this,R.drawable.item_decoration));
        recyclerView.addItemDecoration(new GridItemDecoration(this,R.drawable.item_decoration));
        recyclerView.setAdapter(adapter);

       new ItemTouchHelper(new SItemTouchCallback(adapter)).attachToRecyclerView(recyclerView);

    }

}
