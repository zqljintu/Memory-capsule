package com.zql.base.utils;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ReplacementSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

/**
 * Created by pandajoy on 17-4-25.
 */

public class StringStyleUtils {


    public static CharSequence formatOneStyle(String rawString,//原始字符串 例如:我100斤
                                              String replaceString,//替换字符串 例如:100
                                              float fontSize,//替换字体大小
                                              int fontColor,//颜色
                                              int flags) {
        int length = rawString.length();
        if (length > 0) {
            int absFontSize = (int) (fontSize);
            int index = rawString.indexOf(replaceString);
            SpannableStringBuilder style = new SpannableStringBuilder(rawString);
            if (index != -1) {
                style.setSpan(new AbsoluteSizeSpan(absFontSize), index, index + replaceString.length(), flags);
                style.setSpan(new ForegroundColorSpan(fontColor), index, index + replaceString.length(), flags);
            }
            return style;
        } else {
            return rawString;
        }
    }

    public static CharSequence formatTwoStyle(String rawString,
                                              String oneReplaceString,
                                              float oneFontSize,
                                              int oneFontColor,
                                              String twoReplaceString,
                                              float twoFontSize,
                                              int twoFontColor,
                                              int flags) {
        int length = rawString.length();
        if (length > 0) {
            int absOneFontSize = (int) (oneFontSize);
            int oneIndex = rawString.indexOf(oneReplaceString);
            int absTwoFontSize = (int) (twoFontSize);
            int twoIndex = rawString.indexOf(twoReplaceString);
            SpannableStringBuilder style = new SpannableStringBuilder(rawString);
            if (oneIndex != -1) {
                style.setSpan(new AbsoluteSizeSpan(absOneFontSize), oneIndex, oneIndex + oneReplaceString.length(), flags);
                style.setSpan(new ForegroundColorSpan(oneFontColor), oneIndex, oneIndex + oneReplaceString.length(), flags);
            }
            if (twoIndex != -1) {
                style.setSpan(new AbsoluteSizeSpan(absTwoFontSize), twoIndex, twoIndex + twoReplaceString.length(), flags);
                style.setSpan(new ForegroundColorSpan(twoFontColor), twoIndex, twoIndex + twoReplaceString.length(), flags);
            }
            return style;
        } else {
            return rawString;
        }
    }

    public static Spannable formatStyle(Spannable content, String replaceText, int dpsize, boolean isBold) {
        Spannable sp = new SpannableString(content);
        int[] index = getIndex(content.toString(), replaceText);
        sp.setSpan(new AbsoluteSizeSpan(dpsize, true), index[0], index[1], Spannable.SPAN_INCLUSIVE_INCLUSIVE);//改变字体
        if (isBold) {
            sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), index[0], index[1], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//加粗
        }
        return sp;
    }

    public static Spannable formatTopStyle(Spannable content, String replaceText, int dpsize, boolean isBold, boolean isTop) {
        Spannable sp = new SpannableString(content);
        int[] index = getIndex(content.toString(), replaceText);
//        sp.setSpan(new AbsoluteSizeSpan(dpsize, true), index[0], index[1], Spannable.SPAN_INCLUSIVE_INCLUSIVE);//改变字体
        if (isBold) {
            sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), index[0], index[1], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//加粗
        }
        if (isTop) {//小文字顶部对齐
            sp.setSpan(new TopSpan(DisplayUtils.dp2px(dpsize), Color.parseColor("#FFFFFF")), index[0], index[1], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return sp;
    }

    public static Spannable formatBoldStyle(Spannable content, String replaceText) {
        Spannable sp = new SpannableString(content);
        int[] index = getIndex(content.toString(), replaceText);
        sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), index[0], index[1], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }


    /**
     * 计算位置
     *
     * @param content
     * @param replaceText
     * @return
     */
    public static int[] getIndex(String content, String replaceText) {
        int[] index = new int[2];
        index[0] = content.indexOf(replaceText);
        index[1] = index[0] + replaceText.length();
        return index;
    }

    static class TopSpan extends ReplacementSpan {

        private float fontSizePx;    //px
        private int color;

        public TopSpan(float fontSizePx, int color) {
            this.fontSizePx = fontSizePx;
            this.color = color;
        }

        @Override
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            text = text.subSequence(start, end);
            Paint p = getCustomTextPaint(paint);
            return (int) p.measureText(text.toString());
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            text = text.subSequence(start, end);
            Paint p = getCustomTextPaint(paint);
            Paint.FontMetricsInt fm = p.getFontMetricsInt();
            // 此处重新计算y坐标，使字体顶部对齐
            canvas.drawText(text.toString(), x, y - ((y + fm.descent + y + fm.ascent) - (bottom + top)), p);
//        canvas.drawText(text.toString(), x, y, p);
        }

        private TextPaint getCustomTextPaint(Paint srcPaint) {
            TextPaint paint = new TextPaint(srcPaint);
            paint.setTextSize(fontSizePx);   //设定字体大小, sp转换为px
            paint.setColor(color);
            return paint;
        }
    }

    public static CharSequence formatStyle(String rawString,
                                           String replaceString,
                                           float fontSize,
                                           int fontColor,
                                           int textStyle,
                                           int flags) {
        int length = rawString.length();
        if (length > 0) {
            int absFontSize = (int) (fontSize);
            int index = rawString.indexOf(replaceString);
            SpannableStringBuilder style = new SpannableStringBuilder(rawString);
            if (index != -1) {
                style.setSpan(new AbsoluteSizeSpan(absFontSize), index, index + replaceString.length(), flags);
//                style.setSpan(new CustomVerticalCenterSpan(absFontSize), index, index + replaceString.length(), flags);
                style.setSpan(new ForegroundColorSpan(fontColor), index, index + replaceString.length(), flags);
                if (textStyle != -1) {
                    style.setSpan(new StyleSpan(textStyle), index, index + replaceString.length(), flags);
                }
            }
            return style;
        } else {
            return rawString;
        }
    }

    public static CharSequence formatStyleVertical(String rawString,
                                           String replaceString,
                                           float fontSize,
                                           int fontColor,
                                           int textStyle,
                                           int flags) {
        int length = rawString.length();
        if (length > 0) {
            int absFontSize = (int) (fontSize);
            int index = rawString.indexOf(replaceString);
            SpannableStringBuilder style = new SpannableStringBuilder(rawString);
            if (index != -1) {
                style.setSpan(new AbsoluteSizeSpan(absFontSize), index, index + replaceString.length(), flags);
                //设置 ReplacementSpan, 如果 rawString 和 replaceString 一模一样，文字将不显示！！！
                style.setSpan(new CustomVerticalCenterSpan(absFontSize), index, index + replaceString.length(), flags);
                style.setSpan(new ForegroundColorSpan(fontColor), index, index + replaceString.length(), flags);
                if (textStyle != -1) {
                    style.setSpan(new StyleSpan(textStyle), index, index + replaceString.length(), flags);
                }
            }
            return style;
        } else {
            return rawString;
        }
    }

    public static CharSequence formatStyle(CharSequence rawString,
                                           String replaceString,
                                           float fontSize,
                                           int fontColor,
                                           int textStyle,
                                           int flags) {
        int length = rawString.length();
        if (length > 0) {
            int absFontSize = (int) (fontSize);
            int index = rawString.toString().indexOf(replaceString);
            SpannableStringBuilder style = new SpannableStringBuilder(rawString);
            if (index != -1) {
                style.setSpan(new AbsoluteSizeSpan(absFontSize), index, index + replaceString.length(), flags);
//                style.setSpan(new CustomVerticalCenterSpan(absFontSize), index, index + replaceString.length(), flags);
                style.setSpan(new ForegroundColorSpan(fontColor), index, index + replaceString.length(), flags);
                if (textStyle != -1) {
                    style.setSpan(new StyleSpan(textStyle), index, index + replaceString.length(), flags);
                }
            }
            return style;
        } else {
            return rawString;
        }
    }

    /**
     * <p>一串字符串两种格式的格式化模板代码</p>
     * @param textView
     * @param strOrigin
     * @param place
     * @param textSize1
     * @param textColor1
     * @param textStyle1
     * @param flag1
     * @param textSize2
     * @param textColor2
     * @param textStyle2
     * @param flag2
     */
    public static void formatTwoString(TextView textView,
                                       int strOrigin,
                                       String place,
                                       /////////////
                                       int textSize1,
                                       String textColor1,
                                       int textStyle1,
                                       int flag1,
                                       /////////////
                                       int textSize2,
                                       String textColor2,
                                       int textStyle2,
                                       int flag2){
        if (textView == null){
            return;
        }
        Resources res = textView.getResources();
        CharSequence charSequence = StringStyleUtils.formatStyleVertical(res.getString(strOrigin, place),
                place,
                DisplayUtils.sp2px(textSize1),
                Color.parseColor(textColor1),
                textStyle1,
                flag1);

        CharSequence charSequence2 = StringStyleUtils.formatStyle(charSequence,
                res.getString(strOrigin, ""),
                DisplayUtils.sp2px(textSize2),
                Color.parseColor(textColor2),
                textStyle2,
                flag2);

        textView.setText(charSequence2);
    }

    /**
     * <p>一串字符串两种格式的格式化模板代码</p>
     * @param textView
     * @param strOrigin
     * @param place
     * @param textSize1
     * @param textColor1
     * @param textStyle1
     * @param flag1
     * @param textSize2
     * @param textColor2
     * @param textStyle2
     * @param flag2
     */
    public static void formatTwoString(TextView textView,
                                       String strOrigin,
                                       String place,
                                       /////////////
                                       int textSize1,
                                       String textColor1,
                                       int textStyle1,
                                       int flag1,
                                       /////////////
                                       int textSize2,
                                       String textColor2,
                                       int textStyle2,
                                       int flag2){
        if (textView == null){
            return;
        }
        Resources res = textView.getResources();
        CharSequence charSequence = StringStyleUtils.formatStyleVertical(strOrigin,
                place,
                DisplayUtils.sp2px(textSize1),
                Color.parseColor(textColor1),
                textStyle1,
                flag1);

        CharSequence charSequence2 = StringStyleUtils.formatStyle(charSequence,
                strOrigin,
                DisplayUtils.sp2px(textSize2),
                Color.parseColor(textColor2),
                textStyle2,
                flag2);

        textView.setText(charSequence2);
    }
}
