package cn.mf.codelaboratory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Test1Activity extends AppCompatActivity {

    @BindView(R.id.test1_btn)
    Button mTest1Btn;

    public static void openActivity(Context context) {
        Intent starter = new Intent(context, Test1Activity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.test1_btn)
    public void onViewClicked() {
        Toast.makeText(this, "hahah", Toast.LENGTH_SHORT).show();
        mTest1Btn.setText("更换"+ new Random(100));
    }
}
