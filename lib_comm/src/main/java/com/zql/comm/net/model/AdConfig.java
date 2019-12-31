package com.zql.comm.net.model;

import java.io.Serializable;

/**
 * Create by Totoro
 * 2019/11/28 2:52 PM
 **/
public class AdConfig implements Serializable {


    /**
     * admob : {"AD_Button_Light":true,"AD_Img_Light":true,"Click_Range":1}
     * fb : {"AD_Button_Light":true,"AD_Img_Light":true,"Click_Range":1}
     */

    private AdmobBean admob;
    private FbBean fb;

    public AdmobBean getAdmob() {
        return admob;
    }

    public void setAdmob(AdmobBean admob) {
        this.admob = admob;
    }

    public FbBean getFb() {
        return fb;
    }

    public void setFb(FbBean fb) {
        this.fb = fb;
    }

    public static class AdmobBean implements Serializable{
        /**
         * AD_Button_Light : true
         * AD_Img_Light : true
         * Click_Range : 1
         */

        private boolean AD_Button_Light;
        private boolean AD_Img_Light;
        private int Click_Range;

        public boolean isAD_Button_Light() {
            return AD_Button_Light;
        }

        public void setAD_Button_Light(boolean AD_Button_Light) {
            this.AD_Button_Light = AD_Button_Light;
        }

        public boolean isAD_Img_Light() {
            return AD_Img_Light;
        }

        public void setAD_Img_Light(boolean AD_Img_Light) {
            this.AD_Img_Light = AD_Img_Light;
        }

        public int getClick_Range() {
            return Click_Range;
        }

        public void setClick_Range(int Click_Range) {
            this.Click_Range = Click_Range;
        }
    }

    public static class FbBean implements Serializable{
        /**
         * AD_Button_Light : true
         * AD_Img_Light : true
         * Click_Range : 1
         */

        private boolean AD_Button_Light;
        private boolean AD_Img_Light;
        private int Click_Range;

        public boolean isAD_Button_Light() {
            return AD_Button_Light;
        }

        public void setAD_Button_Light(boolean AD_Button_Light) {
            this.AD_Button_Light = AD_Button_Light;
        }

        public boolean isAD_Img_Light() {
            return AD_Img_Light;
        }

        public void setAD_Img_Light(boolean AD_Img_Light) {
            this.AD_Img_Light = AD_Img_Light;
        }

        public int getClick_Range() {
            return Click_Range;
        }

        public void setClick_Range(int Click_Range) {
            this.Click_Range = Click_Range;
        }
    }
}
