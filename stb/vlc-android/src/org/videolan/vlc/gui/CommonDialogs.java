/*****************************************************************************
 * CommonDialogs.java
 *****************************************************************************
 * Copyright © 2012 VLC authors and VideoLAN
 * Copyright © 2012 Edward Wang
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
import java.net.URI;
import java.net.URISyntaxException;

import org.videolan.vlc.R;
import org.videolan.vlc.VlcRunnable;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class CommonDialogs {
    public final static String TAG = "VLC/CommonDialogs";

    public static AlertDialog deleteMedia(final Context context,
                                          final String addressMedia,
                                          final VlcRunnable runnable) {
        URI adressMediaUri = null;
        try {
            adressMediaUri = new URI (addressMedia);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        final File fileMedia = new File(adressMediaUri);

        AlertDialog alertDialog = new AlertDialog.Builder(context)
        .setTitle(R.string.validation)
        .setMessage(context.getResources().getString(R.string.confirm_delete, fileMedia.getName()))
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                fileMedia.delete();
                if(runnable != null)
                    runnable.run();
            }
        })
        .setNegativeButton(android.R.string.cancel, null).create();

        return alertDialog;
    }
}
