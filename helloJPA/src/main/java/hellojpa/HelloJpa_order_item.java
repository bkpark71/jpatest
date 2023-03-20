package hellojpa;

import domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpa_order_item {
    public static void main(String[] args) {
        // 스프링이 EMF 를 만들어줌. 스프링을 안써서 직접 만듬
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 웹 - localhost:8080/members/add
        System.out.println("item_orderItem 등록 트랜잭션 시작전 !!!");

        try {
            tx.begin();
            // 신규 item 등록
//            Item item = new Item();
//            item.setItemName("newItem");
//            item.setPrice(10_000);
//            item.setQuantity(100);


            System.out.println("비영속상태 =======");

            System.out.println("영속상태 =======");

            Item item = em.find(Item.class, 1L);

            System.out.println("1차 캐시에서 가져옴 =======");
            System.out.println("findItem = " + item);

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setOrderPrice(7000);
            orderItem.setOrderQuantity(1);
            em.persist(orderItem);

            OrderItem orderItem1 = new OrderItem();
            orderItem1.setItem(item);
            orderItem1.setOrderPrice(8000);
            orderItem1.setOrderQuantity(2);
            em.persist(orderItem1);

            System.out.println("커밋전 !!!");
            tx.commit();
            System.out.println("커밋후 !!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
