package com.example.fang.baiduface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Fkz on 2017/4/18.
 */

public class FaceView extends View {
    private Paint paint;
    private Rect rect;
    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.rect!=null){
            canvas.drawRect(rect,paint);
        }
    }

    public void drawFace(Rect rect){
        this.rect=rect;
        invalidate();
    }

}
