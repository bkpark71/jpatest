package domain;
import javax.persistence.*;
@Entity
public class MemberO {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne   // 1,2,3번 회원이 모두 1번 팀 소속
    @JoinColumn(name = "team_id")
    private Team team;
    private String username;
    @Enumerated(EnumType.STRING)
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
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
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
                ", team=" + team +
                ", username='" + username + '\'' +
                ", memberType=" + memberType +
                '}';
    }
}