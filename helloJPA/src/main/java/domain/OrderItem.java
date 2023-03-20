package domain;

import javax.persistence.*;

@Entity(name="order_item")
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @Column(name="order_price")
    private Integer orderPrice;

    @Column(name="order_quantity")
    private Integer orderQuantity;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", item=" + item +
                ", orderPrice=" + orderPrice +
                ", orderQuantity=" + orderQuantity +
                '}';
    }
}
