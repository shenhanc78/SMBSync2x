package com.sentaroh.android.Utilities;

import android.content.Context;
import java.util.EventListener;

public interface CallBackListener extends EventListener {
    public boolean onCallBack(Context c, boolean positive, Object[] o);
}
