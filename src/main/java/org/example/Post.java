package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {

    private final int id;
    private final String text;
    private final String type;
    private final String user;
    private final int upvotes;


    public Post(@JsonProperty("id") int id, @JsonProperty("text")String text, @JsonProperty("type")String type, @JsonProperty("user")String user, @JsonProperty("upvotes") int upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    @Override
    public String toString() {
        return "Post:" +
                "\n id=" + id +
                "\n text=" + text +
                "\n type=" + type +
                "\n user=" + user +
                "\n upvotes=" + upvotes;
    }
}
