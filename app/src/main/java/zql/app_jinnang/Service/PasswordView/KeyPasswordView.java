package zql.app_jinnang.Service.PasswordView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class KeyPasswordView extends View{
    private int count=0;
    private float mWidth,mHeight;
    private float radius;
    private float[] cx=new float[6];
    private float cy;
    private Paint paint;
    private Integer rouldrect_integer;
    public KeyPasswordView(Context context){
        super(context);
    }
    public KeyPasswordView(Context context, AttributeSet att){
        super(context,att);
    }
    public KeyPasswordView(Context context,AttributeSet att,int defStyleAttr){
        super(context,att,defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initData();
        drawRouldRect(canvas);
        drawCircle(canvas);
        super.onDraw(canvas);
    }
    private void initData(){
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        radius=15;
        mWidth=getWidth();
        mHeight=getHeight();
        float spaceing=(mWidth-radius*2*6)/7;
        cx[0]=spaceing+radius;
        cx[1]=spaceing*2+radius*2+radius;
        cx[2]=spaceing*3+radius*4+radius;
        cx[3]=spaceing*4+radius*6+radius;
        cx[4]=spaceing*5+radius*8+radius;
        cx[5]=spaceing*6+radius*10+radius;
        cy=mHeight/2;
    }
    private void drawCircle(Canvas canvas){
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        for (int j=0;j<count;j++){
            canvas.drawCircle(cx[j],cy,radius,paint);
        }
    }
    private void drawRouldRect(Canvas canvas){
        paint.setColor(rouldrect_integer);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(new RectF(10,10,mWidth-10,mHeight-10),mHeight/2,mHeight/2,paint);
    }
    public void changeTheNum(int mnum){
        if (mnum>6){
            mnum=0;
        }
        this.count=mnum;
        invalidate();
    }
    public void setRouldRectcolor(Integer integer){
        this.rouldrect_integer=integer;
    }
}
