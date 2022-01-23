package tpjad.server.team.dto;

public class TeamDTO {
    private String name;
    private String status;

    public TeamDTO(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public TeamDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
