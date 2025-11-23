package com.example.clipboardws;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NotificationChannel channel = new NotificationChannel(
                "server_channel",
                "Local Server",
                NotificationManager.IMPORTANCE_LOW
        );
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        Button btnStart = findViewById(R.id.btn_start_server);
        Button btnStop  = findViewById(R.id.btn_exit_server);

        // Stop server
        btnStop.setOnClickListener(v -> {

            Intent intent = new Intent(this, ServerForegroundService.class);
            stopService(intent);

            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
            Toast.makeText(this, "Shutting down localhost...", Toast.LENGTH_SHORT).show();
        });

        // Start server
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, ServerForegroundService.class);
            ContextCompat.startForegroundService(this, intent);

            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
            Toast.makeText(this, "Localhost started", Toast.LENGTH_SHORT).show();
        });
    }
}