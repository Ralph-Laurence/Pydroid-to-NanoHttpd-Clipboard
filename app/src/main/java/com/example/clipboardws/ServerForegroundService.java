package com.example.clipboardws;

import android.app.Notification;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.io.IOException;

public class ServerForegroundService extends Service
{
    private LocalHost server;

    @Override
    public void onCreate()
    {
        super.onCreate();
        try
        {
            server = new LocalHost(data ->
            {
                Log.d("ME", "Received: " + data);

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", data);
                clipboard.setPrimaryClip(clip);
            });
            server.start();
        }
        catch (IOException e) {
            Log.e("ME", "Error starting server", e);
        }

        Notification notification = new NotificationCompat.Builder(this, "server_channel")
                .setContentTitle("Local Server Running")
                .setContentText("Listening on port 8080")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (server != null) server.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

