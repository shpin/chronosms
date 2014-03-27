package ru.shpin.chronosms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver {
        
        // Get the object of SmsManager
        final SmsManager sms = SmsManager.getDefault();
        
        public void onReceive(Context context, Intent intent) {
        
            // Retrieves a map of extended data from the intent.
            final Bundle bundle = intent.getExtras();
    
            try {
                
                if (bundle != null) {
                    
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    
                    for (int i = 0; i < pdusObj.length; i++) {
                        
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                        
                        String senderNum = phoneNumber;
                        String message = currentMessage.getMessageBody().toString();
    
                        Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
                        
    
                       // Show Alert
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, 
                                     "senderNum: "+ senderNum + ", message: " + message, duration);
                        toast.show();
                        
                        // turn wifi on
                    	WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE); 
                    	wifi.setWifiEnabled(true);
                        
                        // send data to inet
                        PostData do_post = new PostData();
                        do_post.sender = senderNum;
                        do_post.message = message;
                        do_post.execute();
                    		
                        
                    } // end for loop
                  } // bundle is null
    
            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" +e);
                
            }
        }    
    }
