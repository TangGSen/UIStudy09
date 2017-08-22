package sen.com.view3d;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/22.
 */

public class FragmentPage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String text = bundle.getString("text");
        int bg = bundle.getInt("bgres");
        View rootView = inflater.inflate(R.layout.fragment_layout, container,false);
        TextView textView = (TextView) rootView.findViewById(R.id.tv_page);
        View content_layout = (View) rootView.findViewById(R.id.content_layout);
        TextView textView1 = (TextView) rootView.findViewById(R.id.tv_page1);
        textView.setText(text);
        textView1.setText(text);
        content_layout.setBackgroundDrawable(getActivity().getResources().getDrawable(bg));
        return rootView;
    }
}
