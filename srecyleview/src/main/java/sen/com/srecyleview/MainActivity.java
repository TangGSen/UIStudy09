package sen.com.srecyleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SRecyclerView recyclerView;
    private int[] colorArray = new int[]{R.drawable.bg_page1, R.drawable.bg_page2, R.drawable.bg_page3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (SRecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<DataItems> dataItemses = new ArrayList<>();
        for (int i=0 ;i<20;i++){
            dataItemses.add(new DataItems("Item"+i, colorArray[i % colorArray.length]));
        }
        for (int i=0 ;i<20;i++){
            dataItemses.get(i).setDataItemses(dataItemses);
        }

        MainRecycleAdapter adapter = new MainRecycleAdapter(this,dataItemses);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpaceItemDecoration(16,adapter.getItemCount()));
        recyclerView.setAdapter(adapter);


    }
}
