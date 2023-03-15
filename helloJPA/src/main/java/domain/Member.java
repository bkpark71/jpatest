package domain;
import javax.persistence.*;
//@Entity
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String username;
    public Member(String username){
        this.username = username;
    }
    public Member(){}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

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