package com.zql.lib_capsule.view;

import com.zql.base.ui.mvp.im.IView;
import com.zql.comm.netbean.response.CapsulesResponse;

public interface CapsuleContract {

    interface view extends IView{
        void setCapsuleDataToView(CapsulesResponse data);//将网络加载到的数据展现在界面上
    }

    interface presenter {
        void loadCapsuleDataFromService();//从服务器上拉取capsule数据
    }
}
