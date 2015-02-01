package io.evilolive.vicinium;

import android.content.Context;
import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class MessageHandler {

    private static final String TAG = "MessageHandler";

    Firebase chatroomRef;
    String username;
    Context context;

    public MessageHandler(Context context, int latitude, int longitude) {
        Firebase.setAndroidContext(context);
        Firebase home = new Firebase("https://dazzling-torch-5552.firebaseio.com/");
        chatroomRef = home.child(Integer.toString(latitude)).child(Integer.toString(longitude)).push();

        username = MainActivity.getUsername(context);

    }

    public void sendMessage(String message) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(username, message);
        data.put("time", ServerValue.TIMESTAMP);
        chatroomRef.updateChildren(data, new Firebase.CompletionListener() {

            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError == null) {
                    Log.d(TAG, "Successfully added data to Firebase");
                } else  {
                    Log.d(TAG, "Failed to add data to Firebase");
                }
            }
        });
    }

}
