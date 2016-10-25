package com.example.user.my;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by User on 23-10-2016.
 */
public class SmsBroadcastReceiver extends BroadcastReceiver {
    public static final String SMS_BUNDLE = "pdus";
    final SmsManager msg = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {
        Log.i("LOG'", " - onreceive called");
        WakeLocker.acquire(context);

        Bundle intentExtras = intent.getExtras();


        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getDisplayMessageBody().toString();
                String address = smsMessage.getDisplayOriginatingAddress();
                String senderNum = address;

                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";
                    Log.i("SmsReceiver", "senderNum " + senderNum + "; smsBody " + smsBody);
                    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    audioManager.setStreamVolume(AudioManager.STREAM_RING, maxVolume, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, "Sender: "+ senderNum + ", Message: " + smsBody, duration);
                    toast.show();
                    final int NOTIF_ID = 1234;
                    NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification note = new Notification(R.drawable.ic_action_name2, "New sms", System.currentTimeMillis());
                    PendingIntent intent2 = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
                    note.setLatestEventInfo(context, "New sms", "You have one unread message.", intent2);
                    notifManager.notify(NOTIF_ID, note);
                    Log.i("LOG'"," - notified");
                    // notifManager.cancel(NOTIF_ID);
                    WakeLocker.release();
                    //abortBroadcast();


                }

            Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();

            //this will update the UI with message
            MainActivity inst = MainActivity.instance();
            inst.updateList(smsMessageStr);
        }
    }



    }


