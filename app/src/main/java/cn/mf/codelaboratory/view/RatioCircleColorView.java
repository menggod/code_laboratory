package cn.mf.codelaboratory.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * 项目名称：android-autoEasy
 *
 * @author menggod
 * @date 2018/1/4.
 */
public class RatioCircleColorView extends ImageView {
    private float mRadius;
    private String mColorString = "#ff0000,#0000ff,#00ff00";
    private int mColor;
    private int mHight;

    public RatioCircleColorView(Context context) {
        super(context);
        setColor();
    }

    public RatioCircleColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setColor();
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

    public RatioCircleColorView setColor() {


        Bitmap MaskBitmap = Bitmap.createBitmap(70, 70, Bitmap.Config.ARGB_8888);
        Canvas MaskCanvas = new Canvas(MaskBitmap);

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
            MaskCanvas.drawRect(0, startTop, this.getRight(), startTop + mHight, paint);
            startTop = startTop + mHight;
        }


//        Rect rect = new Rect(0,0,width,height);
//        MaskCanvas.drawRect(rect,MaskPaint);
        Bitmap bitmap = toRoundBitmap(MaskBitmap);
        this.setImageBitmap(bitmap);
        return this;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }


    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }
}
