package domain;
import javax.persistence.*;
@Entity
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="team_id")
    private Long teamId;
    private String name;
    public Long getTeamId() {
        return teamId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", name='" + name + '\'' +
                '}';
    }
}
