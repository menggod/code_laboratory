package cn.mf.codelaboratory;

import io.reactivex.Observable;

/**
 * 项目名称：code_laboratory
 * Created by menggod on 2018/5/29.
 */
public class EventTranslator {

    public static Observable<LoginEvent> onLoginEvent() {
        return EventCenter.getInstance().getObservable(LoginEvent.class);
    }

    public static Observable<AttentionEvent> onAttentionEvent() {
        return EventCenter.getInstance().getObservable(AttentionEvent.class);
    }
}
