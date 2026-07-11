package crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Interaction implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private LocalDateTime date;
    private String note;
    private String type; // e.g., "Call", "Email", "Meeting"

    public Interaction(String type, String note) {
        this.date = LocalDateTime.now();
        this.type = type;
        this.note = note;
    }

    public LocalDateTime getDate() { return date; }
    public String getNote() { return note; }
    public String getType() { return type; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("[%s] %s: %s", date.format(formatter), type, note);
    }

    public String toJson() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("{\"date\":\"%s\", \"type\":\"%s\", \"note\":\"%s\"}", 
            date.format(formatter), 
            type.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", ""), 
            note.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", ""));
    }
}
Pressing key...Clicking...Stopping...

Stop Agent
