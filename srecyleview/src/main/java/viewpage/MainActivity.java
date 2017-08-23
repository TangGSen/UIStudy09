package viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sen.com.srecyleview.DataItems;
import sen.com.srecyleview.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "sen";
    int[] colorArray = new int[]{R.drawable.bg_page1,R.drawable.bg_page2,R.drawable.bg_page3};
    private List<DataItems> dataItemses;

    RecyclerView.RecycledViewPool mPool = new RecyclerView.RecycledViewPool() {
        @Override
        public RecyclerView.ViewHolder getRecycledView(int viewType) {
            RecyclerView.ViewHolder scrap = super.getRecycledView(viewType);
            Log.i(TAG, "get holder from pool: " + scrap);
            return scrap;
        }

        @Override
        public void putRecycledView(RecyclerView.ViewHolder scrap) {
            super.putRecycledView(scrap);
            Log.i(TAG, "put holder to pool: " + scrap);
        }

        @Override
        public String toString() {
            return "ViewPool in adapter " + Integer.toHexString(hashCode());
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layot_viewpage);
        dataItemses = new ArrayList<>();
        for (int i=0 ;i<10;i++){
            dataItemses.add(new DataItems("Item"+i, colorArray[i % colorArray.length]));
        }
        for (int i=0 ;i<10;i++){
            dataItemses.get(i).setDataItemses(dataItemses);
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPager.setPageTransformer(false,new ViewPageTransformer());
        viewPager.setAdapter(new ViewPagerAdater(getSupportFragmentManager()));

    }

    class ViewPagerAdater extends FragmentPagerAdapter{

        public ViewPagerAdater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            RecycleViewFragmentPage fragmentPage = new RecycleViewFragmentPage();
            Bundle bundle = new Bundle();
            ArrayList list = new ArrayList();
            list.add(dataItemses);
            bundle.putParcelableArrayList("dataItemses", list);
            fragmentPage.setArguments(bundle);
            fragmentPage.mPoo =mPool;
            return fragmentPage;
        }

        @Override
        public int getCount() {
            return 10;
        }
    }

    class ViewPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {

        }
    }
}
