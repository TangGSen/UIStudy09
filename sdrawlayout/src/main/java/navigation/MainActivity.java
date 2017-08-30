package navigation;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;

import sen.com.sdrawlayout.R;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        //显示图标原来的颜色
        //navigationView.setItemIconTintList(null);
    }
}
