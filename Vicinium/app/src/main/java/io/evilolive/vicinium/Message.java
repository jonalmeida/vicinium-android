package io.evilolive.vicinium;

public class Message {
    private String name;
    private String message;
    private MessageType type;
    private long timestamp;


    public Message(String name, String message) {
        super();
        this.name = name;
        this.message = message;
        this.type = MessageType.Default;
    }

    public Message(String name, String message, MessageType type) {
        super();
        this.name = name;
        this.message = message;
        this.type = type;
    }

    public Message(String name, String message, MessageType type, long timestamp) {
        super();
        this.name = name;
        this.message = message;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
