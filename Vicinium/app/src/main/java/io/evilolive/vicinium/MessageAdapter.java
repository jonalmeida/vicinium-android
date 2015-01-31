package io.evilolive.vicinium;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {
    private Context context;
    private List<Message> messageList;


    public MessageAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);

        this.context = context;
        this.messageList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.list_item_message, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.messageName);
        TextView valueView = (TextView) rowView.findViewById(R.id.messageText);

        // 4. Set the text for textView
        labelView.setText(messageList.get(position).getName());
        valueView.setText(messageList.get(position).getMessage());

        if (messageList.get(position).getType() == MessageType.Personal ||
                messageList.get(position).getName().equals(MainActivity.getUsername(context))) {
            labelView.setBackgroundColor(Color.parseColor("#673AB7"));
            valueView.setBackgroundColor(Color.parseColor("#673AB7"));
        }

        // 5. return rowView
        return rowView;
    }
}
