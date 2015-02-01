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

        Message previousMessage = null, currentMessage = null;
        if (position-1 >= 0)
            previousMessage = messageList.get(position-1);

        currentMessage = messageList.get(position);

        View rowView;

        if (currentMessage.getType() == MessageType.Personal ||
                currentMessage.getName().equals(MainActivity.getUsername(context))) {

            if (previousMessage != null && previousMessage.getName().equals(MainActivity.getUsername(context))) {
                rowView = inflater.inflate(R.layout.list_item_personal_only, parent, false);
            } else {
                rowView = inflater.inflate(R.layout.list_item_personal_message, parent, false);
            }
        } else {
            if (previousMessage != null && currentMessage.getName().equals(previousMessage.getName())) {
                rowView = inflater.inflate(R.layout.list_item_message_only, parent, false);
            } else {
                rowView = inflater.inflate(R.layout.list_item_message, parent, false);
            }
        }

        // 3. Get the two text view from the rowView
//        if(rowView. )
        TextView labelView = (TextView) rowView.findViewById(R.id.messageName);
        if (labelView != null)
            labelView.setText(messageList.get(position).getName());


        TextView valueView = (TextView) rowView.findViewById(R.id.messageText);
        if (valueView != null)
            valueView.setText(messageList.get(position).getMessage());

        // 5. return rowView
        return rowView;
    }
}
