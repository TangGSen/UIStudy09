package sen.com.view3d;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    int[] bg = new int[]{R.drawable.bg_page1,R.drawable.bg_page2,R.drawable.bg_page3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setPageTransformer(false,new ViewPageTransformer());
        viewPager.setAdapter(new ViewPagerAdater(getSupportFragmentManager()));
    }

    class ViewPagerAdater extends FragmentPagerAdapter{

        public ViewPagerAdater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            FragmentPage fragmentPage = new FragmentPage();
            Bundle bundle = new Bundle();
            bundle.putInt("bgres",bg[position]);
            bundle.putString("text","Page-"+position);
            fragmentPage.setArguments(bundle);
            return fragmentPage;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    class ViewPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
//            page.setScaleX(1-Math.abs(position));
//            page.setScaleY(1-Math.abs(position));

//            page.setScaleX(Math.max(0.9f,1-Math.abs(position)));
//            page.setScaleY(Math.max(0.85f,1-Math.abs(position)));
            //3d
//            page.setPivotX(position<0?page.getWidth():0f);
//            page.setPivotY(page.getHeight()*0.5f);
//            page.setRotationY(position*90);
//            page.setScaleX(Math.max(0.9f,1-Math.abs(position)));
//            page.setScaleY(Math.max(0.85f,1-Math.abs(position)));

//            page.setPivotX(position<0?page.getWidth():0f);
//            page.setPivotY(page.getHeight()*0.5f);
//            page.setRotationY(position*45);
//            page.setScaleX(Math.max(0.9f,1-Math.abs(position)));
//            page.setScaleY(Math.max(0.85f,1-Math.abs(position)));


//            page.setPivotX(position<0?page.getWidth():0f);
//            page.setPivotY(page.getHeight()*0.5f);
//            page.setRotationY(-position*45);
//            page.setScaleX(Math.max(0.9f,1-Math.abs(position)));
//            page.setScaleY(Math.max(0.85f,1-Math.abs(position)));

//            左右都按照各自的一半作为中心轴
//            page.setPivotX(page.getWidth()*0.5f);
//            page.setPivotY(page.getHeight()*0.5f);
//            page.setRotationY(-position*45);


//            page.setPivotX(position<0?page.getWidth():0f);
//            page.setPivotY(page.getHeight()*0.5f);
//            page.setRotationY(-position*45);
//            page.setScaleX(Math.max(0.5f,1-Math.abs(position)));
//            page.setScaleY(Math.max(0.5f,1-Math.abs(position)));

//            视觉差

            ViewGroup viewGroup = (ViewGroup) page.findViewById(R.id.content_layout);
            int viewSize = viewGroup.getChildCount();
            float fector =0.1f;
            for (int i = 0; i < viewSize; i++) {
                View childView = viewGroup.getChildAt(i);
                if (childView.getTag() ==null){
                    fector = (float) Math.random()+2;
                    childView.setTag(fector);
                }else{
                    fector = (float) childView.getTag();
                }
                childView.setTranslationX(fector*childView.getWidth()*position);
            }

        }
    }
}
