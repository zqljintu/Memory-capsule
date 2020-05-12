package com.zql.comm.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zql.base.utils.ToastUtil;
import com.zql.comm.R;

/**
 * 2019-11-18 14:16
 **/
public class DialogFactory {


    public static void createUpdateDialog(Context context, String version, String desc, String path, boolean isForce, OnUpdateListener listener) {
        Dialog mDialog = new Dialog(context, R.style.common_custom_dialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = LayoutInflater.from(context).inflate(R.layout.dialog_update, null);
        TextView tvVersion = root.findViewById(R.id.update_version);
        tvVersion.setText("V" + version);
        TextView tvTitle = root.findViewById(R.id.update_title);
        tvTitle.setText(context.getString(R.string.update_title, context.getString(R.string.application_name)));
        TextView tvDesc = root.findViewById(R.id.update_desc);
        tvDesc.setText(desc);
        ImageView close = root.findViewById(R.id.update_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                if (listener != null) {
                    listener.onClose(isForce);
                }
            }
        });
        Button button = root.findViewById(R.id.update_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isForce)
                    mDialog.dismiss();
                if (listener != null) {
                    listener.onUpdate(path, isForce);
                }
            }
        });
        mDialog.setCancelable(!isForce);
        mDialog.setCanceledOnTouchOutside(!isForce);
        mDialog.setContentView(root);
        Window dialogWindow = mDialog.getWindow();
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // 设置高度和宽度
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        p.width = (int) (d.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
        mDialog.show();
    }


    public static void createLogoutDialog(Context context, OnDialogListener listener) {
        Dialog mDialog = new Dialog(context, R.style.common_custom_dialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = LayoutInflater.from(context).inflate(R.layout.dialog_logout, null);
        EditText editText = root.findViewById(R.id.edit_pass);
        TextView mConfirm = root.findViewById(R.id.text_submit);
        ImageView close = root.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                if (listener != null) {
                    listener.onCancel();
                }
            }
        });
        mConfirm.setOnClickListener(v -> {
            if (TextUtils.isEmpty(editText.getText())){
                ToastUtil.showToast(context.getString(R.string.lougout_pass));
                return;
            }
            if (listener != null){
                listener.onConfirm(editText.getText().toString());
                mDialog.dismiss();
            }
        });
        mDialog.setContentView(root);
        Window dialogWindow = mDialog.getWindow();
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // 设置高度和宽度
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        p.width = (int) (d.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
        mDialog.show();
    }

}
