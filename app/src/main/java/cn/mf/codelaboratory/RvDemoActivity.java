package cn.mf.codelaboratory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sudi on 2018/1/11.
 * Email：sudi@yiche.com
 */

public class RvDemoActivity extends Activity {

    private RecyclerView rv;
    private MyAdapter mAdapter;
    GridLayoutManager layoutManager;
    private List<DataWrapper> allData;


    public static void open(AppCompatActivity activity) {
        Intent intent = new Intent(activity, RvDemoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvdemo);
        rv = (RecyclerView) findViewById(R.id.rv);
        mAdapter = new MyAdapter();
        layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setSpanSizeLookup(new MySpanSizeLookup(mAdapter));

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(mAdapter);
        prepareData();
        mAdapter.setData(allData);
    }

    private void prepareData() {
        allData = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            allData.add(new DataWrapper(DataWrapper.SECTION));
            for (int j = 0; j < 20; j++) {
                DataWrapper data = new DataWrapper(DataWrapper.GRID);
                data.data = new Object();
                allData.add(data);
            }
        }
    }

    public class MySpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        private MyAdapter mAdapter;

        public MySpanSizeLookup(MyAdapter adapter) {
            mAdapter = adapter;
        }

        @Override
        public int getSpanSize(int position) {
            return mAdapter.isSection(position) ? layoutManager.getSpanCount() : 1;
        }
    }

    public static class DataWrapper {
        public static final int SECTION = 0;
        public static final int LINEAR = 1;
        public static final int GRID = 2;
        public static final int STAGGERED = 3;
        public int viewType;
        public Object data;

        public DataWrapper(int grid) {
            this.viewType = grid;
        }
    }

    public static class MyAdapter extends RecyclerView.Adapter {
        List<DataWrapper> allData;

        public MyAdapter() {
            allData = new ArrayList<>();
        }


        public void setData(List<DataWrapper> allData) {
            this.allData = allData;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rvdemo_item, parent, false);
            return new MyHolder(inflate);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (allData.get(position).viewType == DataWrapper.GRID) {
                ((MyHolder) holder).bindData();
            } else {

            }
        }

        @Override
        public int getItemCount() {
            return allData.size();
        }

        public boolean isSection(int position) {
            return allData.get(position).viewType == DataWrapper.SECTION;
        }
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        View view;

        public MyHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
        }

        public void bindData() {
            view.setBackgroundColor(Color.BLUE);
        }
    }
}
