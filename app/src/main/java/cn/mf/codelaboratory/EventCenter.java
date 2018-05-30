package cn.mf.codelaboratory;

import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

/**
 * 项目名称：code_laboratory
 * Created by menggod on 2018/5/30.
 */
public class EventCenter {

    private Subject mSubject;

    public EventCenter() {
        mSubject = ReplaySubject.createWithSize(1);
    }

    private static class SingletonHolder {
        private static final EventCenter INSTANCE = new EventCenter();
    }

    public static EventCenter getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void sendEvent(Object object) {
        try {
            mSubject.onNext(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> Observable getObservable(Class<T> type) {
        return EventCenter.getInstance().mSubject.ofType(type);
    }


}
