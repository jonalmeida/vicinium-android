package io.evilolive.vicinium;

public class Message {
    private String name;
    private String message;
    private MessageType type;


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
}
