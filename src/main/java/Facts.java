import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class Facts {
    private String id;
    private String text;
    private String type;
    private String user;
    private Optional<Integer> upvotes;

    public Facts(@JsonProperty("id") String id,
                 @JsonProperty("text") String text,
                 @JsonProperty("type") String type,
                 @JsonProperty("user") String user,
                 @JsonProperty("upvotes") int upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = Optional.ofNullable(upvotes);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public Optional<Integer> getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Optional<Integer> upvotes) {
        this.upvotes = upvotes;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{id=" + id + ", text=" + text + ", type=" + type +
                ", user=" + user + ", upvotes=" + upvotes + "}";
    }
}
