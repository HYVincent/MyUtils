package com.vincent.library.view;

import android.support.v7.widget.RecyclerView;

/**
 * @name HUDHelp
 * @class name：com.vincent.library.view
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/27 10:26
 * @change
 * @chang time
 * @class describe
 */

public abstract class OnVerticalScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (!recyclerView.canScrollVertically(-1)) {
            onScrolledToTop();
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToBottom();
        } else if (dy < 0) {
            onScrolledUp();
        } else if (dy > 0) {
            onScrolledDown();
        }
    }

    public void onScrolledUp() {}

    public void onScrolledDown() {}

    public void onScrolledToTop() {}

    public void onScrolledToBottom() {}
}
