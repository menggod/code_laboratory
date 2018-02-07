package cn.mf.codelaboratory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.mf.codelaboratory.adapter.Test2Adapter;
import cn.mf.codelaboratory.model.WashCarBean;

public class Test2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<WashCarBean> mList;
    private Test2Adapter mTest2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        initView();
        processData();
        mTest2Adapter.setList(mList);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.container_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTest2Adapter = new Test2Adapter();
        mTest2Adapter.setListener(new Test2Adapter.OnWashCarItemClickListener() {
            @Override
            public void onRGCheckedChanged(Test2Adapter adapter, int position, int itemPosition) {
                List<WashCarBean> washCarBeans = adapter.getList().subList(0, itemPosition + 1);
                switch (position) {
                    case 0:
                        processData1(washCarBeans);
                        mTest2Adapter.setNewList(washCarBeans, itemPosition + 1);
                        break;
                    case 1:
                        processData2(washCarBeans);
                        mTest2Adapter.setNewList(washCarBeans, itemPosition+ 1);
                        break;
                }
            }
        });
        mRecyclerView.setAdapter(mTest2Adapter);
    }

    private void processData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            WashCarBean washCarBean = new WashCarBean();
            washCarBean.setAdapterType(WashCarBean.wash_car_tpye1);
            mList.add(washCarBean);
        }
        {
            WashCarBean washCarBean = new WashCarBean();
            washCarBean.setAdapterType(WashCarBean.wash_car_tab);
            mList.add(washCarBean);
        }
        for (int i = 0; i < 10; i++) {
            WashCarBean washCarBean = new WashCarBean();
            washCarBean.setAdapterType(WashCarBean.wash_car_tpye3)
                    .setCenterText(i + "条目");
            mList.add(washCarBean);
        }

    }


    private void processData1(List<WashCarBean> washCarBeans) {
        for (int i = 0; i < 20; i++) {
            WashCarBean washCarBean = new WashCarBean();
            washCarBean.setAdapterType(WashCarBean.wash_car_tpye3)
                    .setCenterText(i + "条目---");
            washCarBeans.add(washCarBean);
        }
    }

    private void processData2(List<WashCarBean> washCarBeans) {
        for (int i = 0; i < 10; i++) {
            WashCarBean washCarBean = new WashCarBean();
            washCarBean.setAdapterType(WashCarBean.wash_car_tpye3)
                    .setCenterText(i + "条   目---");
            washCarBeans.add(washCarBean);
        }
    }

}


