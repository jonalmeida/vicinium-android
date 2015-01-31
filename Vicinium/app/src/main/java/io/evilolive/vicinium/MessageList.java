package io.evilolive.vicinium;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MessageList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        createListView();
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
        ListView mainListView = (ListView) findViewById(R.id.listViewAllMessages);

        ArrayList<Message> arrayList = new ArrayList<>();
        arrayList.add(new Message("John Doe", "Hello"));
        arrayList.add(new Message("Jane Doe", "Hi!", MessageType.Personal));
        arrayList.add(new Message("Steve Doe", "Fuck off you bitches", MessageType.Default));

        MessageAdapter adapter = new MessageAdapter(this, R.layout.list_item_message, arrayList);

        mainListView.setAdapter(adapter);
    }
}
