package cn.mf.codelaboratory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class Test1Activity extends AppCompatActivity {


    @BindView(R.id.textview1)
    TextView mTextview1;
    @BindView(R.id.textview2)
    TextView mTextview2;
    private CompositeDisposable mDisposable;

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, Test1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        ButterKnife.bind(this);
        onEvent();
    }

    private void onEvent() {
        getSubscription().add(EventTranslator.onLoginEvent()
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception {
                        mTextview1.setText(loginEvent.isLogin + "");
                    }
                }));

        getSubscription().add(EventTranslator.onAttentionEvent()
        .subscribe(new Consumer<AttentionEvent>() {
            @Override
            public void accept(AttentionEvent attentionEvent) throws Exception {
                mTextview2.setText(attentionEvent.userId);
            }
        }));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    protected CompositeDisposable getSubscription() {
        if (null == mDisposable) {
            mDisposable = new CompositeDisposable();
        }
        return mDisposable;
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Test2Activity.openActivity(this);
    }
}
