package com.vincent.library.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

/**
 * @name MyUtils
 * @class name：com.vincent.library.view
 * @class describe RecycleView item anim
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 13:33
 * @change
 * @chang time
 * @class describe
 */

public class RecycleViewItemAnim extends RecyclerView.ItemAnimator{

    /**
     * 当RecyclerView中的item在屏幕上由可见变为不可见时调用此方法
     * @param viewHolder
     * @param preLayoutInfo
     * @param postLayoutInfo
     * @return
     */
    @Override
    public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
        return false;
    }

    /**
     * 当RecyclerView中的item显示到屏幕上时调用此方法
     * @param viewHolder
     * @param preLayoutInfo
     * @param postLayoutInfo
     * @return
     */
    @Override
    public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        return false;
    }


    @Override
    public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        return false;
    }

    /**
     * 当RecyclerView中的item状态发生改变时调用此方法(notifyItemChanged(position))
     * @param oldHolder
     * @param newHolder
     * @param preLayoutInfo
     * @param postLayoutInfo
     * @return
     */
    @Override
    public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        return false;
    }

    /**
     * 统筹RecyclerView中所有的动画，统一启动执行
     */
    @Override
    public void runPendingAnimations() {

    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
//        item.itemView.setAnimation();
    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
