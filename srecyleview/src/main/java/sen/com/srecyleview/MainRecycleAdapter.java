package sen.com.srecyleview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */

public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.SViewHodler> {
    private  List<DataItems> dataItemses;
    private  Context context;

    public MainRecycleAdapter(Context context, List<DataItems> dataItemses) {
        this.context = context;
        this.dataItemses =dataItemses;
    }


    @Override
    public SViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_main, parent, false);
        SViewHodler viewHolder = new SViewHodler(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SViewHodler holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return dataItemses.size();
    }

    class SViewHodler extends RecyclerView.ViewHolder{
        LinearLayoutManager linearLayoutManager;
        SRecyclerView recyclerView;
        ItemAdapter itemAdapter;
        public SViewHodler(View itemView) {
            super(itemView);
            recyclerView  = (SRecyclerView)itemView. findViewById(R.id.recycleView);
            linearLayoutManager= new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);

        }

        public void bind(int position) {
            if (itemAdapter ==null) {
                itemAdapter = new ItemAdapter(context,dataItemses.get(position).getDataItemses());
                recyclerView.setAdapter(itemAdapter);
            }
            linearLayoutManager.scrollToPosition(0);
        }
    }
}
