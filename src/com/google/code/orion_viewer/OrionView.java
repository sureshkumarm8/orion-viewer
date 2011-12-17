package com.google.code.orion_viewer;

/*Orion Viewer is a pdf viewer for Nook Classic based on mupdf

Copyright (C) 2011  Michael Bogdanov

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * User: mike
 * Date: 16.10.11
 * Time: 13:52
 */
public class OrionView extends View {

    public Bitmap bitmap;

    private CountDownLatch latch;

    private String path;

    private Paint pathColor = new Paint();

//    private int rotation;

    public OrionView(Context context) {
        super(context);

    }

    public OrionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OrionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null && !bitmap.isRecycled()) {
            long start = new Date().getTime();
            Common.d("Start drawing bitmap");

//                if (rotation != 0) {
//                    canvas.rotate(-rotation * 90, (getHeight()) / 2, getWidth() / 2);
//                    canvas.translate(- rotation * 80, - rotation * 80);
//                }
//                Paint paint = new Paint();
//                ColorFilter filter = new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
//                paint.setColorFilter(filter);
            canvas.drawBitmap(bitmap, 0, 0, null);

            Common.d("OrionView drawn bitmap at " + 0.001f * (new Date().getTime() - start) + " s");
        } else if (path != null) {
            //pathColor.setColor(Color.BLACK);
            pathColor.setTextSize(18);
            canvas.drawText(path, 10, 20, pathColor);
        }
        if (latch != null) {
            latch.countDown();
        }
    }

    public void setData(Bitmap bitmap, CountDownLatch latch) {
        this.bitmap = bitmap;
        this.latch = latch;
        path = null;
    }

    public void setPath(String path) {
        bitmap = null;
        this.path = path;
    }

//   public void setRotation(int rotation) {
//       synchronized (this) {
//        this.rotation = rotation;
//       }
//    }



}
