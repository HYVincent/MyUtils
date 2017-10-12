package com.vincent.library.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vincent.library.R;
import com.vincent.library.adapter.CommonStrItemAdapter;
import com.vincent.library.listener.CommonItemOnClickListener;
import com.vincent.library.util.ScreenUtils;
import com.vincent.library.view.WrapContentLinearLayoutManager;

import java.util.List;

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

    private static Dialog dialog = null;

    public static void cancelDialog(){
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

    /**
     * 消息提示dialog
     * @param activity
     * @param title
     * @param content
     * @param listener
     */
    public static void hintTextDialog(Activity activity, String title, String content, boolean hasCancelButton,boolean isCancel,final CommonActionListener listener){
        View view = LayoutInflater.from(activity).inflate(R.layout.dlg_layout_hint,null,false);
        if(dialog == null){
            dialog = new Dialog(activity,R.style.MyDialogStyle);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.width = ScreenUtils.getScreenWidth(activity)/5*4;
        dialog.setContentView(view,layoutParams);
        dialog.setCanceledOnTouchOutside(isCancel);
        dialog.setCancelable(isCancel);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);//需要添加权限  SecurityException: com.hud.help was not granted  this permission: android.permission.WRITE_SETTINGS.
        TextView tvTitle = view.findViewById(R.id.dlg_title);
        TextView tvContent = view.findViewById(R.id.dlg_content);
        TextView tvCancel = view.findViewById(R.id.dlg_cancel_tv);
        TextView tvOk = view.findViewById(R.id.dlg_ok_tv);
        if(hasCancelButton){
            tvCancel.setVisibility(View.VISIBLE);
        }else {
            tvCancel.setVisibility(View.GONE);
        }
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



    /**
     * 系统默认的
     * @param title
     * @param listener
     */
    public static void hintTextDialog(Activity activity,String title,final CommonActionListener listener){
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.action();
                    }
                })
                .show();
    }

    /**
     *
     * @param data
     * @param heightRatio dialog高度占屏幕的比例
     * @param isCancel 点击外部是否消失
     * @param listener
     */
    public static void showBottomList(Activity activity,List<String> data,String title,int heightRatio,boolean isCancel, final CommonItemOnClickListener  listener){
        final Dialog dialog = new Dialog(activity, R.style.MyDialogStyle);// 创建自定义样式dialog
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.dialog_layout_bottom_list, null);// 得到加载view
        LinearLayout layout =  view.findViewById(R.id.dialog_layout_root_bottom);// 加载布局
        RelativeLayout rlTitle = view.findViewById(R.id.dialog_bottom_list_title_rl);
        TextView tvTitle = view.findViewById(R.id.dialog_bottom_list_tv_title);
        if(TextUtils.isEmpty(title)){
            rlTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setText(title);
            rlTitle.setVisibility(View.VISIBLE);
        }
        RecyclerView recyclerView = view.findViewById(R.id.dialog_bottom_list_rlv);
        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CommonStrItemAdapter adapter = new CommonStrItemAdapter(activity);
        adapter.setData(data);
        adapter.setOnItemOnClickListener(new CommonItemOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                listener.onItemClick(view,position);
                dialog.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        dialog.setCancelable(true); // 是否可以按“返回键”消失
        dialog.setCanceledOnTouchOutside(isCancel); // 点击加载框以外的区域
        dialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = ScreenUtils.getScreenHeight()/heightRatio;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        dialog.show();
    }

    public interface CommonActionListener{
        void action();
    }

}
