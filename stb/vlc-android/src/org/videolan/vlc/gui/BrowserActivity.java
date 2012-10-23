/*****************************************************************************
 * BrowserActivity.java
 *****************************************************************************
 * Copyright © 2011-2012 VLC authors and VideoLAN
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston MA 02110-1301, USA.
 *****************************************************************************/

package org.videolan.vlc.gui;

import java.io.File;
import java.io.FileFilter;
import java.util.Stack;

import org.videolan.vlc.Media;
import org.videolan.vlc.R;
import org.videolan.vlc.Util;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

public class BrowserActivity extends ListActivity {
    public final static String TAG = "VLC/BrowserActivity";

    /**
     * TODO:
     */

    private BrowserAdapter mAdapter;
    private File mCurrentDir;
    private final Stack<ScrollState> mScollStates = new Stack<ScrollState>();
    private String mRoot;

    private class ScrollState {
        public ScrollState(int index, int top) {
            this.index = index;
            this.top = top;
        }

        int index;
        int top;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        mAdapter = new BrowserAdapter(this);
        setListAdapter(mAdapter);

        //get the root from the settings
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        mRoot = pref.getString("directories_root", "/");

        //Make sure the path is valid, use "/" if it is not
        File file = new File(mRoot);
        if (!file.exists())
            file = new File("/");
        mRoot = file.getPath();

        openDir(file);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mScollStates.clear();
    }

    private void openDir(File file) {
        if (!file.exists() || file.getPath() == null)
            return;

        mAdapter.clear();
        mCurrentDir = file;
        File[] files = file.listFiles(new DirFilter());
        /* If no sub-directories or I/O error don't crash */
        if(files == null || files.length < 1) {
            Util.toaster(this, R.string.nosubdirectory);
            this.finish();
            return;
        }
        for (int i = 0; i < files.length; i++) {
            mAdapter.add(files[i]);
        }
        mAdapter.sort();
        // set scroll position to top
        getListView().setSelection(0);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        File file = mAdapter.getItem(position);
        File[] files = file.listFiles(new DirFilter());
        if (files != null && files.length > 0) {
            // store scroll state
            int index = l.getFirstVisiblePosition();
            int top = l.getChildAt(0).getTop();
            mScollStates.push(new ScrollState(index, top));
            openDir(file);
        } else {
            Util.toaster(this, R.string.nosubdirectory);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mCurrentDir.getPath().equals(mRoot)) {
                return super.onKeyDown(keyCode, event);
            } else {
                openDir(mCurrentDir.getParentFile());
                // restore scroll state
                ScrollState ss = mScollStates.pop();
                getListView().setSelectionFromTop(ss.index, ss.top);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Filter: accept only directories
     */
    private class DirFilter implements FileFilter {

        @Override
        public boolean accept(File f) {
            return f.isDirectory() && !Media.FOLDER_BLACKLIST.contains(f.getPath().toLowerCase());
        }
    }

}
