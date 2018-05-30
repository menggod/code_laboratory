package cn.mf.codelaboratory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Test2Activity extends AppCompatActivity {
    private static final String TAG = "Test2Activity";

    @BindView(R.id.textview)
    TextView mTextview;
    private Disposable mSubscribe;

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, Test2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);
        mSubscribe = EventTranslator.onLoginEvent()
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception {
                        mTextview.setText(loginEvent.isLogin + "");
                        Log.e(TAG, "Test2Activity.accept---->" + loginEvent.isLogin);
                    }
                });
    }


    @OnClick({R.id.button, R.id.button1, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                Test3Activity.openActivity(this);
                break;
            case R.id.button1:
                mSubscribe.dispose();
                mTextview.setText("");
                break;
            case R.id.button2:
                mSubscribe = EventTranslator.onLoginEvent()
                        .subscribe(new Consumer<LoginEvent>() {
                            @Override
                            public void accept(LoginEvent loginEvent) throws Exception {
                                mTextview.setText(loginEvent.isLogin + "");
                                Log.e(TAG, "Test2Activity.重新订阅---->" + loginEvent.isLogin);
                            }
                        });
                break;
        }
    }
}
