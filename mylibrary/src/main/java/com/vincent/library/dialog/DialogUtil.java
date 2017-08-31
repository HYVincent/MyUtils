package com.vincent.library.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vincent.library.R;
import com.vincent.library.util.ScreenUtils;

/**
 * @name MyUtils
 * @class name：com.vincent.library.dialog
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/8/31 16:00
 * @change
 * @chang time
 * @class describe
 */

public class DialogUtil {

    /**
     * 消息提示dialog
     * @param activity
     * @param title
     * @param content
     * @param listener
     */
    public static void hintTextDialog(Activity activity, String title, String content, final CommonActionListener listener){
        View view = LayoutInflater.from(activity).inflate(R.layout.dlg_layout_hint,null,false);
        final Dialog dialog = new Dialog(activity,R.style.MyDialogStyle);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.width = ScreenUtils.getScreenWidth(activity)/5*4;
        dialog.setContentView(view,layoutParams);
        dialog.setCanceledOnTouchOutside(true);
        TextView tvTitle = view.findViewById(R.id.dlg_title);
        TextView tvContent = view.findViewById(R.id.dlg_content);
        TextView tvCancel = view.findViewById(R.id.dlg_cancel_tv);
        TextView tvOk = view.findViewById(R.id.dlg_ok_tv);
        tvTitle.setText(title);
        tvContent.setText(content);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.action();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


   public interface CommonActionListener{
        void action();
    }

}
