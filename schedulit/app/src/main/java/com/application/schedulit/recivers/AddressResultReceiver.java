package com.application.schedulit.recivers;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.application.schedulit.consts.Constants;



/**
 * Created by yosinoa on 05/07/2016.
 */
@SuppressLint("ParcelCreator")
public class AddressResultReceiver extends ResultReceiver {

    Context mContext;
    public AddressResultReceiver(Context context,Handler handler) {
        super(handler);
        this.mContext = context;
    }

    /**
     *  Receives data sent from FetchAddressIntentService and updates the UI in MainActivity.
     */
    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {



        //displayAddressOutput();

        // Show a toast message if an address was found.
        if (resultCode == Constants.SUCCESS_RESULT) {
            //Toast.makeText() R.string.address_found));
            String mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            Toast.makeText(mContext,
                    mAddressOutput, Toast.LENGTH_SHORT).show();
            SmsManager smsManager = SmsManager.getDefault();
           // smsManager.sendTextMessage("0533378678", null,"hi,dont worry am at: "+mAddressOutput, null, null);
            smsManager.sendTextMessage("0533402673", null,mAddressOutput, null, null);
            smsManager.sendTextMessage("0533402673", null,"היי, אני בדרך ב: "+mAddressOutput, null, null);
            smsManager.sendTextMessage("0533378678", null,"היי יפה שלי , אני בדרך ב:"+mAddressOutput, null, null);
        }

        // Reset. Enable the Fetch Address button and stop showing the progress bar.
        //mAddressRequested = false;
        //updateUIWidgets();

    }
}
