package com.application.schedulit.services;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.TaskParams;
import com.google.gson.Gson;

import com.application.schedulit.ui.SchedulitContactItem;
import com.application.schedulit.ui.SchedulitItem;

import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;


public class GcmSchedulitService extends GcmTaskService {

    private static final String TAG = GcmSchedulitService.class.getSimpleName();

    public static final String GCM_ONEOFF_TAG = "oneoff|[0,0]";
    public static final String GCM_REPEAT_TAG = "repeat|[7200,1800]";
    public static final String SCHEDULIT_ITEM_KEY = "SchedulitItem";

    public GcmSchedulitService() {
    }

    @Override
    public int onRunTask(TaskParams taskParams) {
        try {
            /*Toast.makeText(getBaseContext(),
                    "in On run task", Toast.LENGTH_SHORT).show();*/


            Bundle extras = taskParams.getExtras();
            String jsonSchedulitItem = extras.getString(SCHEDULIT_ITEM_KEY);
            SchedulitItem schedulitItem = new Gson().fromJson(jsonSchedulitItem, SchedulitItem.class);

            SendSms(schedulitItem);

            return GcmNetworkManager.RESULT_SUCCESS;
        }
        catch (Exception ex)
        {
            return GcmNetworkManager.RESULT_FAILURE;
        }
    }

    private void SendSms(SchedulitItem schedulitItem) {
        try
        {

            SmsManager smsManager = SmsManager.getDefault();
            for (SchedulitContactItem contact : schedulitItem.getContacts()) {
                smsManager.sendTextMessage(contact.getPhone(), null, schedulitItem.getMsgText(), null, null);
            }


        }
        catch (android.content.ActivityNotFoundException ex) {
            /*Toast.makeText(getBaseContext(),
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();*/
        }
    }

    public static void scheduleRepeat(Context context,SchedulitItem item) {
        //in this method, single Repeating task is scheduled (the target service that will be called is MyTaskService.class)
        try {

            Toast.makeText(context,
                    "In schedulie repeat", Toast.LENGTH_SHORT).show();
            Bundle data = new Bundle();
            data.putString(SCHEDULIT_ITEM_KEY, new Gson().toJson(item));
            PeriodicTask periodic = new PeriodicTask.Builder()
                    //specify target service - must extend GcmTaskService
                    .setService(GcmSchedulitService.class)
                            //repeat every 60 seconds
                    .setPeriod(item.getInterval())
                            //specify how much earlier the task can be executed (in seconds)
                    .setFlex(10)
                            //tag that is unique to this task (can be used to cancel task)
                    .setTag(GCM_REPEAT_TAG)
                            //whether the task persists after device reboot
                    //.setPersisted(true)
                            //if another task with same tag is already scheduled, replace it with this task
                    .setUpdateCurrent(true)
                            //set required network state, this line is optional
                    //.setRequiredNetwork(Task.NETWORK_STATE_ANY)
                            //request that charging must be connected, this line is optional
                    .setRequiresCharging(false)

                    .setExtras(data)
                    .build();
            GcmNetworkManager.getInstance(context).schedule(periodic);

        } catch (Exception e) {
           // Log.e(TAG, "scheduling failed");
            e.printStackTrace();
        }
    }

    public static void cancelRepeat(Context context) {
        GcmNetworkManager
                .getInstance(context)
                .cancelTask(GCM_REPEAT_TAG, GcmSchedulitService.class);
    }

    public static void cancelAll(Context context) {
        GcmNetworkManager
                .getInstance(context)
                .cancelAllTasks(GcmSchedulitService.class);
    }

}
