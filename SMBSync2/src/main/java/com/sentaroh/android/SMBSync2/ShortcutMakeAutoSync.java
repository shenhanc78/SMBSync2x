package com.sentaroh.android.SMBSync2;

/*
The MIT License (MIT)
Copyright (c) 2011 Sentaroh

Permission is hereby granted, free of charge, to any person obtaining a copy of 
this software and associated documentation files (the "Software"), to deal 
in the Software without restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
and to permit persons to whom the Software is furnished to do so, subject to 
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, 
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

*/

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Bundle;

public class ShortcutMakeAutoSync extends Activity {
    private ShortcutMakeAutoSync mActivity=null;
    private Context mContext=null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(new GlobalParameters().setNewLocale(base, true));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);

        mActivity=ShortcutMakeAutoSync.this;
        mContext=mActivity;
        Intent shortcutIntent = new Intent(Intent.ACTION_VIEW);
        shortcutIntent.setClassName(mActivity, ShortcutAutoSync.class.getName());
        String shortcutName = getString(R.string.app_name_auto_sync);

        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        Icon icon = Icon.createWithResource(getApplicationContext(), R.drawable.auto_sync);
        ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(getApplicationContext(), "auto_sync")
                .setShortLabel(shortcutName)
                .setLongLabel(shortcutName)
                .setIcon(icon)
                .setIntent(shortcutIntent)
                .build();
        Intent shortcutResultIntent = shortcutManager.createShortcutResultIntent(shortcutInfo);
        setResult(RESULT_OK, shortcutResultIntent);

        finish();
    }

}
