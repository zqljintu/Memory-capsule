package com.zql.comm.ui.anim;

import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.RecyclerView;

public interface AnimateViewHolder {

  void preAnimateAddImpl(final RecyclerView.ViewHolder holder);

  void preAnimateRemoveImpl(final RecyclerView.ViewHolder holder);

  void animateAddImpl(final RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener);

  void animateRemoveImpl(final RecyclerView.ViewHolder holder,
                         ViewPropertyAnimatorListener listener);
}
