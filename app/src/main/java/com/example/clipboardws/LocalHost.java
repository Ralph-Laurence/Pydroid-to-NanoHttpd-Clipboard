package com.example.clipboardws;

import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class LocalHost extends NanoHTTPD
{
    private static final int PORT = 8080;

    private final Base64RecieveEvent b64listener;

    public interface Base64RecieveEvent {
        void onRecieved(String data);
    }

    public LocalHost(Base64RecieveEvent b64listener)
    {
        super("0.0.0.0", PORT);
        this.b64listener = b64listener;
    }

    @Override
    public Response serve(IHTTPSession session)
    {
        if (!Method.POST.equals(session.getMethod()))
            return newFixedLengthResponse("Oops .. Not a POST request.");

        try
        {
            Map<String, String> body = new HashMap<>();
            session.parseBody(body);

            String rawBody = body.get("postData");
            if (rawBody == null || rawBody.isEmpty()) {
                return newFixedLengthResponse("Error: Empty body");
            }

            JSONObject json = new JSONObject(rawBody);
            String data = json.optString("data", null);

            b64listener.onRecieved(data);
            return newFixedLengthResponse("Received: " + data);
        }
        catch (Exception e)
        {
            Log.d("ME", e.getMessage());
            return newFixedLengthResponse("Error: " + e.getMessage());
        }
    }
}
