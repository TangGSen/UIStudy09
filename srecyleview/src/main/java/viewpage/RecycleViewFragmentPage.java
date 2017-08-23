package viewpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sen.com.srecyleview.DataItems;
import sen.com.srecyleview.MainRecycleAdapter;
import sen.com.srecyleview.R;
import sen.com.srecyleview.SRecyclerView;
import sen.com.srecyleview.SpaceItemDecoration;

/**
 * Created by Administrator on 2017/8/22.
 */

public class RecycleViewFragmentPage extends Fragment {

    private SRecyclerView recyclerView;
    public  List<DataItems> dataItemses;
    private MainRecycleAdapter adapter;
    public RecyclerView.RecycledViewPool mPoo;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        recyclerView = (SRecyclerView) rootView.findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        Bundle bundle = getArguments();
        dataItemses = (List<DataItems>) bundle.getParcelableArrayList("dataItemses").get(0);
        adapter = new MainRecycleAdapter(getContext(), dataItemses);
        recyclerView.addItemDecoration(new SpaceItemDecoration(16, adapter.getItemCount()));
        recyclerView.setRecycledViewPool(mPoo);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        return rootView;
    }


}
