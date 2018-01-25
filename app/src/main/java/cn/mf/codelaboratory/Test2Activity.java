package cn.mf.codelaboratory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import cn.mf.codelaboratory.model.WashCarBean;

public class Test2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        initView();
        processData();


    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.container_rv);

    }
    private void processData() {
        ArrayList<WashCarBean> washCarBeans = new ArrayList<>();

    }
}
