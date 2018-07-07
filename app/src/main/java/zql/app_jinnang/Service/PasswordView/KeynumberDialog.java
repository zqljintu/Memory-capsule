package zql.app_jinnang.Service.PasswordView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class KeynumberDialog extends View{
    private Context mcontext;
    private Paint paint;
    private float mWidth,mHeight;
    private float mRectWidth,mRectHeigt;
    private OnNumberClickListener onNumberClickListener;
    private float x1,y1,x2,y2;
    private float[] xs=new float[3];
    private float[] ys=new float[4];
    private float cx,cy;
    private String showNumber;
    private String[] password=new String[6];
    private int type=1;
    private int sum=0;


    public KeynumberDialog(Context context){
        super(context);
    }
    public KeynumberDialog(Context context, AttributeSet att){
        super(context,att);
    }
    public KeynumberDialog(Context context,AttributeSet att,int defStyleAttr){
        super(context,att,defStyleAttr);
    }

    public OnNumberClickListener getOnNumberClickListener() {
        return onNumberClickListener;
    }

    public void setOnNumberClickListener(OnNumberClickListener onNumberClickListener) {
        this.onNumberClickListener = onNumberClickListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initData();
        drawKeyboard(canvas);
        if (type==0){
            paint.setColor(Color.GRAY);
            canvas.drawRoundRect(new RectF(x1,y1,x2,y2),10,10,paint);
            paint.setColor(Color.BLACK);
            canvas.drawText(showNumber,cx,cy,paint);
        }
        if (type==1){
            paint.setColor(Color.WHITE);
            canvas.drawRoundRect(new RectF(x1,y1,x2,y2),0,0,paint);
        }
        super.onDraw(canvas);
    }
    private void initData(){
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mWidth=getWidth();
        mHeight=getHeight();
        mRectWidth=(mWidth-60)/3;
        mRectHeigt=(mHeight-80)/4;
        xs[0]=mRectWidth/2+3;
        xs[1]=mRectWidth+23+mRectWidth/2;
        xs[2]=mRectWidth*2+43+mRectWidth/2;
        ys[0]=mRectHeigt/2+15;
        ys[1]=mRectHeigt+35+mRectHeigt/2;
        ys[2]=mRectHeigt*2+55+mRectHeigt/2;
        ys[3]=mRectHeigt*3+75+mRectHeigt/2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                handleDown(x,y);
                return true;
            case MotionEvent.ACTION_UP:
                handleup();
                if (showNumber.equals("<")){
                    onNumberClickListener.onNumberDelete();
                }else {
                    onNumberClickListener.onNumberReturn(showNumber);
                }
                return true;
            case MotionEvent.ACTION_CANCEL:
                return true;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void drawKeyboard(Canvas canvas){
        paint.setColor(Color.WHITE);
        //第一列
        canvas.drawRoundRect(new RectF(10,10,mRectWidth+10,mRectHeigt+10),10,10,paint);
        canvas.drawRoundRect(new RectF(30+mRectWidth,10,mRectWidth*2+30,mRectHeigt+10),10,10,paint);
        canvas.drawRoundRect(new RectF(50+2*mRectWidth,10,mRectWidth*3+50,mRectHeigt+10),10,10,paint);
        //第2列
        canvas.drawRoundRect(new RectF(10,30+mRectHeigt,mRectWidth+10,mRectHeigt*2+30),10,10,paint);
        canvas.drawRoundRect(new RectF(30+mRectWidth,30+mRectHeigt,mRectWidth*2+30,mRectHeigt*2+30),10,10,paint);
        canvas.drawRoundRect(new RectF(50+2*mRectWidth,30+mRectHeigt,mRectWidth*3+50,mRectHeigt*2+30),10,10,paint);
        //第3列
        canvas.drawRoundRect(new RectF(10,50+mRectHeigt*2,mRectWidth+10,mRectHeigt*3+50),10,10,paint);
        canvas.drawRoundRect(new RectF(30+mRectWidth,50+mRectHeigt*2,mRectWidth*2+30,mRectHeigt*3+50),10,10,paint);
        canvas.drawRoundRect(new RectF(50+2*mRectWidth,50+mRectHeigt*2,mRectWidth*3+50,mRectHeigt*3+50),10,10,paint);
        //第4列
        canvas.drawRoundRect(new RectF(10,70+mRectHeigt*3,mRectWidth+10,mRectHeigt*4+70),10,10,paint);
        canvas.drawRoundRect(new RectF(30+mRectWidth,70+mRectHeigt*3,mRectWidth*2+30,mRectHeigt*4+70),10,10,paint);
        canvas.drawRoundRect(new RectF(50+2*mRectWidth,70+mRectHeigt*3,mRectWidth*3+50,mRectHeigt*4+70),10,10,paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        paint.setStrokeWidth(2);
        //第一排
        canvas.drawText("1",xs[0],ys[0],paint);
        canvas.drawText("2",xs[1],ys[0],paint);
        canvas.drawText("3",xs[2],ys[0],paint);
        //第2排
        canvas.drawText("4",xs[0],ys[1],paint);
        canvas.drawText("5",xs[1],ys[1],paint);
        canvas.drawText("6",xs[2],ys[1],paint);
        //第一排
        canvas.drawText("7",xs[0],ys[2],paint);
        canvas.drawText("8",xs[1],ys[2],paint);
        canvas.drawText("9",xs[2],ys[2],paint);
        //第一排
        canvas.drawText("/",xs[0],ys[3],paint);
        canvas.drawText("0",xs[1],ys[3],paint);
        canvas.drawText("<",xs[2],ys[3],paint);
    }
    private void handleDown(float x,float y){
        if (x>=10&&x<=mRectHeigt+20){
            if (y>10&&y<mRectHeigt+20){//选中1
                x1=10;
                y1=10;
                x2=mRectWidth+10;
                y2=mRectHeigt+10;
                cx=xs[0];
                cy=ys[0];
                showNumber="1";
            }else if (y>20+mRectHeigt&&y<mRectHeigt*2+40){//选中4
                x1=10;
                y1=30+mRectHeigt;
                x2=mRectWidth+10;
                y2=mRectHeigt*2+30;
                cx=xs[0];
                cy=ys[1];
                showNumber="4";
            }else if (y>mRectHeigt*2+40&&y<mRectHeigt*3+60){//选中7
                x1=10;
                y1=50+mRectHeigt*2;
                x2=mRectWidth+10;
                y2=mRectHeigt*3+50;
                cx=xs[0];
                cy=ys[2];
                showNumber="7";
            }else if (y>mRectHeigt*3+60&&y<mRectHeigt*4+80){//选中/
                x1=10;
                y1=70+mRectHeigt*3;
                x2=mRectWidth+10;
                y2=mRectHeigt*4+70;
                cx=xs[0];
                cy=ys[3];
                showNumber="/";
            }
        }else if (x>mRectWidth+20&&x<mRectWidth*2+40){
            if (y>10&&y<mRectHeigt+20){//选中2
                x1=30+mRectWidth;
                y1=10;
                x2=mRectWidth*2+30;
                y2=mRectHeigt+10;
                cx=xs[1];
                cy=ys[0];
                showNumber="2";
            }else if (y>20+mRectHeigt&&y<mRectHeigt*2+40){//选中5
                x1=30+mRectWidth;
                y1=30+mRectHeigt;
                x2=mRectWidth*2+30;
                y2=mRectHeigt*2+30;
                cx=xs[1];
                cy=ys[1];
                showNumber="5";
            }else if (y>mRectHeigt*2+40&&y<mRectHeigt*3+60){//选中8
                x1=30+mRectWidth;
                y1=50+mRectHeigt*2;
                x2=mRectWidth*2+30;
                y2=mRectHeigt*3+50;
                cx=xs[1];
                cy=ys[2];
                showNumber="8";
            }else if (y>mRectHeigt*3+60&&y<mRectHeigt*4+80){//选中0
                x1=30+mRectWidth;
                y1=70+mRectHeigt*3;
                x2=mRectWidth*2+30;
                y2=mRectHeigt*4+70;
                cx=xs[1];
                cy=ys[3];
                showNumber="0";
            }

        }else if (x>mRectWidth*2+40&&x<mRectWidth*3+60){
            if (y>10&&y<mRectHeigt+20){//选中3
                x1=50+2*mRectWidth;
                y1=10;
                x2=mRectWidth*3+50;
                y2=mRectHeigt+10;
                cx=xs[2];
                cy=ys[0];
                showNumber="3";
            }else if (y>20+mRectHeigt&&y<mRectHeigt*2+40){//选中6
                x1=50+2*mRectWidth;
                y1=30+mRectHeigt;
                x2=mRectWidth*3+50;
                y2=mRectHeigt*2+30;
                cx=xs[2];
                cy=ys[1];
                showNumber="6";
            }else if (y>mRectHeigt*2+40&&y<mRectHeigt*3+60){//选中9
                x1=50+2*mRectWidth;
                y1=50+mRectHeigt*2;
                x2=mRectWidth*3+50;
                y2=mRectHeigt*3+50;
                cx=xs[2];
                cy=ys[2];
                showNumber="9";
            }else if (y>mRectHeigt*3+60&&y<mRectHeigt*4+80){//选中<
                x1=50+2*mRectWidth;
                y1=70+mRectHeigt*3;
                x2=mRectWidth*3+50;
                y2=mRectHeigt*4+70;
                cx=xs[2];
                cy=ys[3];
                showNumber="<";
            }
        }
        type=0;
        invalidate();
    }
    private void handleup(){
        type=1;
        x1=0;
        x2=0;
        y1=0;
        y2=0;
        invalidate();
    }


    public interface OnNumberClickListener{
        public void onNumberReturn(String number);
        public void onNumberDelete();
    }
}
