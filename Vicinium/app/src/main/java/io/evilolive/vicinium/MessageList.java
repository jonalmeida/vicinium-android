package io.evilolive.vicinium;

import android.database.DataSetObserver;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;


public class MessageList extends ActionBarActivity implements ChildEventListener, View.OnClickListener, OnRoomChangeListener {
    private static final String TAG = MessageList.class.toString();
    public static long LOGIN_TIME;

    Firebase chatroomRef;
    ArrayList<Message> arrayList;
    MessageAdapter adapter;
    EditText editText;
    Location location;
    Firebase home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        createListView();

        LOGIN_TIME = System.currentTimeMillis();

        editText = (EditText) findViewById(R.id.enterText);
        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);

        location = LocationHandler.getInstance(this).getLocation();
        RoomCoordinates roomCoordinates = new RoomCoordinates(location);

        home = new Firebase("https://dazzling-torch-5552.firebaseio.com/");
        chatroomRef =  home.child(Integer.toString(roomCoordinates.latitude)).child(Integer.toString(roomCoordinates.longitude));

        chatroomRef.addChildEventListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createListView() {
        final ListView mainListView = (ListView) findViewById(R.id.listViewAllMessages);

        arrayList = new ArrayList<>();
        adapter = new MessageAdapter(this, R.layout.list_item_message, arrayList);

        mainListView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                mainListView.setSelection(adapter.getCount() - 1);
            }
        });

        mainListView.setAdapter(adapter);
    }

    @Override
    public void onChildAdded(DataSnapshot snapshot, String s) {
        DataSnapshot dataSnapshot= snapshot.getChildren().iterator().next();
        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

        if ((long)map.get("time") > MessageList.LOGIN_TIME) {
            Message message = new Message(dataSnapshot.getKey(), dataSnapshot.getValue().toString());
            adapter.add(message);
        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }

    @Override
    public void onClick(View v) {
        RoomCoordinates coordinates = new RoomCoordinates(location);
        MessageHandler messageHandler = new MessageHandler(MessageList.this, coordinates.latitude, coordinates.longitude);
        messageHandler.sendMessage(editText.getText().toString());
        editText.setText("");
    }

    @Override
    public void updateFirebaseForRead(Location location) {
        if (chatroomRef != null) {
            chatroomRef.removeEventListener(this);
        }
        RoomCoordinates roomCoordinates = new RoomCoordinates(location);

        Firebase newChatroomRef =  home.child(Integer.toString(roomCoordinates.latitude)).child(Integer.toString(roomCoordinates.longitude));

        newChatroomRef.addChildEventListener(this);
    }

    @Override
    public void updateFirebaseForWrite(Location location) {
        if (chatroomRef != null) {
            chatroomRef.removeEventListener(this);
        }
        this.location = location;
    }

}
