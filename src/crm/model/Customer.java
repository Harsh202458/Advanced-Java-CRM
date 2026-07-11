package crm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    private String email;
    private String phone;
    private String status; // e.g., "Active", "Inactive", "Lead"
    private List<Interaction> interactions;

    public Customer(int id, String name, String email, String phone, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.interactions = new ArrayList<>();
    }

    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<Interaction> getInteractions() { return interactions; }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-20s | Email: %-25s | Phone: %-15s | Status: %s", 
                             id, name, email, phone, status);
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":").append(id).append(",");
        sb.append("\"name\":\"").append(name != null ? name.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "") : "").append("\",");
        sb.append("\"email\":\"").append(email != null ? email.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "") : "").append("\",");
        sb.append("\"phone\":\"").append(phone != null ? phone.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "") : "").append("\",");
        sb.append("\"status\":\"").append(status != null ? status.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "") : "").append("\",");
        sb.append("\"interactions\":[");
        for(int i=0; i<interactions.size(); i++) {
            sb.append(interactions.get(i).toJson());
            if(i < interactions.size() - 1) sb.append(",");
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }
}
Pressing key...Clicking...Stopping...

Stop Agent
