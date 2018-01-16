//package cn.mf.codelaboratory.view;
//
//import android.animation.ValueAnimator;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Point;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.mf.codelaboratory.R;
//
////import org.beyondimagine.neutron.R;
////import org.beyondimagine.neutron.data.events.ChatDetailAfterSceneClickEvent;
////import org.beyondimagine.neutron.data.events.ChatDetailSketchClickEvent;
////import org.beyondimagine.neutron.data.models.ChatDetailQuestion;
////import org.beyondimagine.neutron.data.models.Good;
////import org.beyondimagine.neutron.data.models.ScenePoints;
////import org.beyondimagine.neutron.utils.LogUtils;
////import org.greenrobot.eventbus.EventBus;
//
///**
// * Created by shaohui on 16/6/28.
// */
//public class SketchView extends View {
//
//    private static final String TAG = "SketchView";
//
//    /**
//     * 水波纹动画时间
//     */
//    private static final int DURING_TIME = 2000;
//    /**
//     * 水波纹外围最大圆半径
//     */
//    private static final int HOLLOW_MAX_RADIUS = 50;
//    /**
//     * 水波纹内层圆半径
//     */
//    private static final int SOLID_RADIUS = 10;
//    /**
//     * 水波纹点击的响应范围（px）
//     */
//    private static final int ACCURACY = 30;
//
//
//    private int mWidth;
//    private int mHeight;
//
//    private EntranceType mEntranceType;
//
//    /**
//     * 外部倒入的原始数据
//     */
//    private List<ScenePoints> mSceneOriginData;
//    private List<ChatDetailQuestion.ResultsBean.DiyBean.AccessoriesBean> mChatOriginData;
//    /**
//     * 确定水波纹位置所需的数据集
//     */
//    private List<Point> mLocationPoints;
//    /**
//     * 内层圆画笔
//     */
//    private Paint whitePaint;
//    /**
//     * 外层水波纹画笔
//     */
//    private Paint orangePaint;
//    /**
//     * 水波纹动画
//     */
//    private ValueAnimator mAnimator;
//    /**
//     * 水波纹的点击监听
//     */
//    private OnClickListener mClickListener;
//    /**
//     * 水波纹动画播放因子，用来划分水波纹动画播放阶段的标识
//     */
//    private float mRadio;
//    private String mAfterSceneUrlInChatDetail;
//
//
//    public SketchView(Context context) {
//        this(context, null);
//    }
//
//    public SketchView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public SketchView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//        whitePaint = new Paint();
//        whitePaint.setAntiAlias(true);
//        whitePaint.setColor(getResources().getColor(R.color.white));
//        whitePaint.setStyle(Paint.Style.FILL);
//
//        orangePaint = new Paint();
//        orangePaint.setAntiAlias(true);
//        orangePaint.setColor(getResources().getColor(R.color.black));
//        orangePaint.setStyle(Paint.Style.FILL);
//
//        mAnimator = ValueAnimator.ofFloat(0, 1);
//        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mRadio = (float) animation.getAnimatedValue();
//                postInvalidate();
//            }
//        });
//        mAnimator.setDuration(DURING_TIME);
//        mAnimator.setRepeatCount(-1);
//
//        mLocationPoints = new ArrayList<>();
//    }
//
//    /**
//     * 启动水波纹动画
//     *
//     * @param pointList
//     * @param listener
//     */
//    public void setScenePoint(List<ScenePoints> pointList, OnClickListener listener) {
//        this.mEntranceType = EntranceType.SCENE_DETAIL;
//        this.mClickListener = listener;
//
//        if (mSceneOriginData == null) {
//            mSceneOriginData = new ArrayList<>();
//        } else {
//            if (mSceneOriginData.size() > 0) {
//                mSceneOriginData.clear();
//            }
//        }
//        mSceneOriginData.addAll(pointList);
//
//        generateLocationPointList(mEntranceType);
//        if (getWidth() > 0 && getHeight() > 0) {
//            startAnimation();
//        }
//    }
//
//    /**
//     * 面向Chat Detail里的水波纹
//     *
//     * @param diyBean
//     */
//    public void setScenePoint(ChatDetailQuestion.ResultsBean.DiyBean diyBean) {
//        if (diyBean == null) {
//            return;
//        }
//        if (diyBean.getAccessories() == null) {
//            return;
//        }
//
//        this.mEntranceType = EntranceType.CHAT_DETAIL;
//
//        if (mChatOriginData == null) {
//            mChatOriginData = new ArrayList<>();
//        } else {
//            if (mChatOriginData.size() > 0) {
//                mChatOriginData.clear();
//            }
//        }
//
//        mAfterSceneUrlInChatDetail = diyBean.getPreview().getPhone();
//        mChatOriginData.addAll(diyBean.getAccessories());
//
//        generateLocationPointList(mEntranceType);
//        if (getWidth() > 0 && getHeight() > 0) {
//            startAnimation();
//        }
//    }
//
//    private void generateLocationPointList(EntranceType entranceType) {
//        if (mWidth == 0 || mHeight == 0) {
//            return;
//        }
//
//        if (entranceType == null) {
//            return;
//        }
//
//        switch (entranceType) {
//            case SCENE_DETAIL:
//                if (mSceneOriginData == null) {
//                    return;
//                }
//
//                //防止onSizeChanged重新执行(比如横竖屏切换)，水波纹点重复添加
//                if (mLocationPoints.size() >= mSceneOriginData.size()) {
//                    return;
//                }
//
//                for (ScenePoints scenePoint : mSceneOriginData) {
//                    Point point = new Point((int) (scenePoint.getX() * mWidth), (int) (scenePoint.getY() * mHeight));
//                    mLocationPoints.add(point);
//                }
//                break;
//            case CHAT_DETAIL:
//                if (mChatOriginData == null) {
//                    return;
//                }
//                // 防止onSizeChanged重新执行(比如横竖屏切换)，水波纹重复添加
//                if (mLocationPoints.size() >= mChatOriginData.size()) {
//                    return;
//                }
//                for (ChatDetailQuestion.ResultsBean.DiyBean.AccessoriesBean accessory : mChatOriginData) {
//                    int accessoryX = (int) (accessory.getX() * mWidth);
//                    int accessoryY = (int) (accessory.getY() * mHeight);
//                    Point point = new Point(accessoryX, accessoryY);
//                    mLocationPoints.add(point);
//                }
//                break;
//        }
//    }
//
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        mWidth = w;
//        mHeight = h;
//
//        generateLocationPointList(mEntranceType);
//        startAnimation();
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        for (Point point : mLocationPoints) {
//            if (0 <= mRadio && mRadio <= 0.25) {
//                animation1(canvas, point, mRadio);
//            } else if (mRadio <= 0.5) {
//                animation2(canvas, point, mRadio);
//            } else if (mRadio <= 0.75) {
//                animation3(canvas, point, mRadio);
//            } else if (mRadio <= 1) {
//                animation4(canvas, point, mRadio);
//            }
//        }
//    }
//
//    // 0 - 0.25
//    private void animation1(Canvas canvas, Point point, float radio) {
//        canvas.drawCircle(point.x, point.y, SOLID_RADIUS * radio * 4 + SOLID_RADIUS, whitePaint);
//    }
//
//    // 0.25 - 0.5
//    private void animation2(Canvas canvas, Point point, float radio) {
//        canvas.drawCircle(point.x, point.y, (0.5f - radio) * SOLID_RADIUS * 4 + SOLID_RADIUS, whitePaint);
//    }
//
//    // 0.5 - 0.75
//    private void animation3(Canvas canvas, Point point, float radio) {
//        orangePaint.setAlpha((int) (125 * (1.0f - radio) * 2));
//        animation2(canvas, point, 0.5f);
//        canvas.drawCircle(point.x, point.y, (radio - 0.5f) * HOLLOW_MAX_RADIUS * 2, orangePaint);
//    }
//
//    // 0.75 - 1
//    private void animation4(Canvas canvas, Point point, float radio) {
//        animation3(canvas, point, radio);
//        canvas.drawCircle(point.x, point.y, (radio - 0.75f) * HOLLOW_MAX_RADIUS * 4, orangePaint);
//    }
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                break;
//            case MotionEvent.ACTION_UP:
//                boolean enable = true;
//                actionUpFor:
//                for (Point point : mLocationPoints) {
//                    if (Math.abs(event.getX() - point.x) < ACCURACY
//                            && (Math.abs(event.getY() - point.y)) < ACCURACY) {
//                        if (mEntranceType != null) {
//                            int indexOf = mLocationPoints.indexOf(point);
//                            switch (mEntranceType) {
//                                case SCENE_DETAIL:
//                                    if (indexOf >= 0 && indexOf < mSceneOriginData.size()) {
//                                        mClickListener.onClick(mSceneOriginData.get(indexOf).getGoods());
//                                        enable = false;
//                                    }
//                                    break actionUpFor;
//                                case CHAT_DETAIL:
//                                    if (indexOf >= 0 && indexOf < mChatOriginData.size()) {
//                                        int goodId = mChatOriginData.get(indexOf).getCorner().getGoods();
//                                        EventBus.getDefault().post(new ChatDetailSketchClickEvent(goodId));
//                                        enable = false;
//                                    }
//                                    break actionUpFor;
//                            }
//                        }
//                    }
//                }
//
//                if (mEntranceType != null && enable) {
//                    switch (mEntranceType) {
//                        case SCENE_DETAIL:
//                            EventBus.getDefault().post(new ChatDetailAfterSceneClickEvent(""));
//                            break;
//                        case CHAT_DETAIL:
//                            EventBus.getDefault().post(new ChatDetailAfterSceneClickEvent(mAfterSceneUrlInChatDetail));
//                            break;
//                    }
//                }
//                break;
//            case MotionEvent.ACTION_CANCEL:
//
//                break;
//        }
//        return true;
//    }
//
//    @Override
//    protected void onWindowVisibilityChanged(int visibility) {
//        super.onWindowVisibilityChanged(visibility);
//        switch (visibility) {
//            case VISIBLE:
//                LogUtils.logd(TAG, "window visible");
//                startAnimation();
//                break;
//            default:
//                LogUtils.logd(TAG, "window not visible");
//                stopAnimation();
//        }
//    }
//
//    public void startAnimation() {
//        if (mLocationPoints.size() > 0 && mAnimator != null) {
//            mAnimator.start();
//        }
//    }
//
//    public void stopAnimation() {
//        if (mAnimator != null && mAnimator.isRunning()) {
//            mAnimator.end();
//        }
//    }
//
//
//    public interface OnClickListener {
//        void onClick(Good good);
//    }
//
//
//    /**
//     * 调用入口分类
//     */
//    public enum EntranceType {
//        SCENE_DETAIL,
//        CHAT_DETAIL
//    }
//
//
//}
