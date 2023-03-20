package domain;

import javax.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @ManyToOne
    @JoinColumn(name="member_id")
    private MemberO memberO;

    public Long getOrderId() {
        return orderId;
    }

    public MemberO getMemberO() {
        return memberO;
    }

    public void setMemberO(MemberO memberO) {
        this.memberO = memberO;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", memberO=" + memberO +
                '}';
    }
}
