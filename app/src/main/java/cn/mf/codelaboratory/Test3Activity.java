package cn.mf.codelaboratory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class Test3Activity extends AppCompatActivity {

    @BindView(R.id.textview)
    TextView mTextview;

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, Test3Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                EventCenter.getInstance().sendEvent(new AttentionEvent("12345"));
                break;
            case R.id.button2:
                processLogin();
                break;
            default:
        }
    }

    private void processLogin() {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.isLogin = true;
        EventCenter.getInstance().sendEvent(loginEvent);
        EventTranslator.onLoginEvent()
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception {
                        Toast.makeText(Test3Activity.this, "loginEvent.isLogin:" + loginEvent.isLogin, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
