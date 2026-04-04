package com.sentaroh.android.Utilities;

import android.content.Context;
import java.util.EventListener;

public class NotifyEvent {

    public interface NotifyEventListener extends EventListener {
        public void positiveResponse(Context c, Object[] o);
        public void negativeResponse(Context c, Object[] o);
    }

    private NotifyEventListener listener = null;
    private Context context;

    public NotifyEvent(Context c) {
        context = c;
    }

    public void notifyToListener(boolean isPositive, Object[] o) {
        if (listener != null) {
            if (isPositive) listener.positiveResponse(context, o);
            else listener.negativeResponse(context, o);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setListener(NotifyEventListener listener) {
        this.listener = listener;
    }

    public void removeListener() {
        this.listener = null;
    }
}
