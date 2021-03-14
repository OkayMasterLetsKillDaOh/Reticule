package com.example.reticule.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class Ret extends View{

    private Paint mPaint;
    private Path mPath;
    private Path mPath2;
    private int canvasWidth;
    private int canvasHeight;
    private Canvas mExtraCanvas;
    private Bitmap mExtraBitmap;

    public Ret(Context context) {
        super(context);
    }

    public Ret(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Ret(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidth = w;
        canvasHeight = h;

        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        float width = canvasWidth;
        float height = canvasHeight;

        mExtraBitmap = Bitmap.createBitmap((int)width, (int)height, Bitmap.Config.ARGB_8888);
        mExtraCanvas = new Canvas(mExtraBitmap);

        dessinerReticule(width/5, height/2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mExtraBitmap, 0, 0, null);
    }

    public void setPos(float x, float y){
        Path newPath = new Path(mPath);
        newPath.moveTo(x,y);
        mPath.reset();
        mPath = newPath;
    }

    public void dessinerReticule(float x, float y){
        mExtraCanvas.drawColor(Color.parseColor("#FFFFFF"));
        int longeurDesLignes = canvasWidth > canvasHeight ? canvasHeight/8 : canvasWidth/8;
        //Starting point
        mPath.reset();
        mPath.moveTo(x - longeurDesLignes, y);
        mPath.lineTo(x+longeurDesLignes,y);

        mPath.moveTo(x, y-longeurDesLignes);
        mPath.lineTo(x,y+longeurDesLignes);

        mPaint.setColor(Color.parseColor("#af703c"));
        mPaint.setStyle(Paint.Style.STROKE); // default: FILL
        //mPaint.setStrokeJoin(Paint.Join.ROUND); // default: MITER
        mPaint.setStrokeWidth(25); // default: Hairline-width (really thin)
        mExtraCanvas.drawCircle(x,y,longeurDesLignes/2, mPaint);
        mExtraCanvas.drawPath(mPath, mPaint);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        dessinerReticule(x, y);

        return super.onTouchEvent(event);
    }
}
