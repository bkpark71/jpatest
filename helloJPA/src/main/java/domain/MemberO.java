package domain;
import javax.persistence.*;
@Entity
public class MemberO {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Enumerated
    private MemberType memberType;
    public MemberO(String username, MemberType memberType){
        this.username = username;
        this.memberType = memberType;
    }
    public MemberO(){ }
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    @Override
    public String toString() {
        return "MemberO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", memberType=" + memberType +
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