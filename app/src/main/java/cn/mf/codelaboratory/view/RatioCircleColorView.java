package cn.mf.codelaboratory.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * 项目名称：android-autoEasy
 *
 * @author menggod
 * @date 2018/1/4.
 */
public class RatioCircleColorView extends View {
    private float mRadius;
    private String mColorString = "#ff0000,#0000ff,#00ff00";
    private int mColor;
    private int mHight;

    public RatioCircleColorView(Context context) {
        super(context);
    }

    public RatioCircleColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RatioCircleColorView(Context context, int color, float radius) {
        super(context);
        this.mColor = color;
        this.mRadius = radius;
        // TODO Auto-generated constructor stub
        // n=Integer.parseInt(context.getResources().getString(R.string.imageviewwidth));
    }

    public RatioCircleColorView setRadius(float radius) {
        mRadius = radius;
        return this;
    }

    public RatioCircleColorView setColor(int color) {
        mColor = color;
        return this;
    }

    public RatioCircleColorView setColor(String colorString) {
        mColorString = colorString;
        return this;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String[] colorList = mColorString.split(",");


        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        int bottom = this.getBottom();
        System.out.println(bottom);
        mHight = this.getBottom() / (colorList.length);

        int startTop = 0;
        Rect rect = new Rect(0, 0, this.getRight(), mHight);


        for (String s : colorList) {
            try {
                paint.setColor(Color.parseColor(s));
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(startTop);
            rect.top = startTop;

//            canvas.drawRect(rect, paint);
            canvas.drawRect(0, startTop, this.getRight(), startTop + mHight, paint);
            startTop = startTop + mHight;
        }


    }
}
