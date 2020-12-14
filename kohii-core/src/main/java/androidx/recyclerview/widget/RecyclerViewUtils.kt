/*
 * Copyright (c) 2019 Nam Nguyen, nam@ene.im
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.recyclerview.widget

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

internal object RecyclerViewUtils {

  // Client must be careful when creating View for ViewHolder.
  // It is suggested to use 'LayoutInflater.inflate(int, ViewGroup, boolean)' with notnull ViewGroup.
  fun accepts(
    recyclerView: RecyclerView,
    params: RecyclerView.LayoutParams?
  ): Boolean {
    if (params == null) return false
    return params.mViewHolder == null || params.mViewHolder.mOwnerRecyclerView === recyclerView
  }

  fun fetchItemViewParams(target: View): RecyclerView.LayoutParams? {
    runCatching {
      var params = target.layoutParams
      var parent = target.parent
      while (params != null && params !is RecyclerView.LayoutParams) {
        params = (parent as? View)?.layoutParams
        parent = parent.parent
      }
      return params as RecyclerView.LayoutParams?
    }
    return null
  }

  fun fetchViewHolder(target: View): ViewHolder? {
    val params = fetchItemViewParams(target)
    return if (params is RecyclerView.LayoutParams) params.mViewHolder else null
  }
}
