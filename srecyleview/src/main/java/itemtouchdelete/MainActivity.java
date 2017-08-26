package itemtouchdelete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import itemtouchdelete.itemtouchhelperextend.ItemTouchHelper;
import sen.com.srecyleview.DataItems;
import sen.com.srecyleview.R;
import sen.com.srecyleview.SRecyclerView;
import sen.com.srecyleview.SpaceItemDecoration;

public class MainActivity extends AppCompatActivity {

    private SRecyclerView recyclerView;
    private int[] colorArray = new int[]{R.drawable.bg_page1, R.drawable.bg_page2, R.drawable.bg_page3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_helper);

        recyclerView = (SRecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<DataItems> dataItemses = new ArrayList<>();
        for (int i=0 ;i<20;i++){
            dataItemses.add(new DataItems("SItem"+i, colorArray[i % colorArray.length]));
        }

        ItemAdapter adapter = new ItemAdapter(this,dataItemses);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpaceItemDecoration(16,adapter.getItemCount()));
        recyclerView.setAdapter(adapter);

       new ItemTouchHelper(new SItemTouchCallback(adapter)).attachToRecyclerView(recyclerView);

    }
}
