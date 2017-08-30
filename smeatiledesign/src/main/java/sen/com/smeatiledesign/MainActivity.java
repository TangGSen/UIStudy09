package sen.com.smeatiledesign;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private TabLayout tablayou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT>21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.test));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.test));

        }
        setContentView(R.layout.activity_tablayout);
        tablayou = (TabLayout) findViewById(R.id.tablayou);
        tablayou.getTabAt(0).setIcon(R.mipmap.ic_launcher_round);
        tablayou.getTabAt(1).setIcon(R.mipmap.ic_launcher_round);
    }
}
