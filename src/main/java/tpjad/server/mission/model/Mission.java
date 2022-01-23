package tpjad.server.mission.model;

import org.hibernate.annotations.GenericGenerator;
import tpjad.server.team.model.Team;

import javax.persistence.*;

@Entity
@Table(name = "mission")
public class Mission {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = true)
    private Team team;

    public Mission() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", team=" + team +
                '}';
    }
}
