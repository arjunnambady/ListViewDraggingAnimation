/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.listviewdragginganimation;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class StableArrayAdapter<T> extends ArrayAdapter<T> implements DynamicListView.SwappableListAdapter {

    final int INVALID_ID = -1;

    List<T> mObjects;
    HashMap<T, Integer> mIdMap = new HashMap<T, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId, List<T> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
        mObjects = objects;
    }

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size()) {
            return INVALID_ID;
        }
        T item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public void swap(int index1, int index2) {
        Collections.swap(mObjects, index1, index2);
        notifyDataSetChanged();
    }
}
