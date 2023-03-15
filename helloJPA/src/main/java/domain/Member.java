package domain;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
public class Member {
    @Id
    @Column(name = "member_id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String username;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    //    @Column(name = "team_id")
//    public Long teamId;
//    // Team team;
    //public Team getTeam(){
    //    return team;
   // }
    //Getter, Setter …
}