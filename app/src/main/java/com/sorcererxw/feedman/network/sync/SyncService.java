package com.sorcererxw.feedman.network.sync;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.sorcererxw.feedman.FeedManApp;
import com.sorcererxw.feedman.models.FeedAccount;

import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/9/15
 */
public class SyncService extends IntentService {
    private static final String KEY_ACCOUNT_ID = "key_account_id";

    public static void sync(Context context, FeedAccount account) {
        Intent intent = new Intent(context, SyncService.class);
        intent.putExtra(KEY_ACCOUNT_ID, account.id());
        context.startActivity(intent);
    }

    public SyncService() {
        super("SyncService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

    }

    private void sync(FeedAccount... accounts) {
        for (FeedAccount account : accounts) {
            if (!isEnableSync(account)) {
                continue;
            }
            long start = SystemClock.currentThreadTimeMillis();
        }
    }

    private static final int SYNC_THRESHOLD_IN_SECONDS = 30;

    private boolean isEnableSync(FeedAccount account) {
        Date date = FeedManApp.getPrefs(this).getLastSync(account.id()).getValue();
        if (date == null) {
            return true;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -SYNC_THRESHOLD_IN_SECONDS);
        return calendar.getTime().compareTo(date) > 0;
    }
}
