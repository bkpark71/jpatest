package hellojpa;

import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpa_update {
    public static void main(String[] args) {
        // 스프링이 EMF 를 만들어줌. 스프링을 안써서 직접 만듬
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 웹 - localhost:8080/members/add
        System.out.println("update 트랜잭션 시작전 !!!");

        try {
            tx.begin();
            Member m1 = em.find(Member.class, 10L);
            String before = m1.username;
            System.out.println("수정전 =======" + before);
            m1.username="jpa update";
//            em.persist(m1);
//            em.merge(m1);
            System.out.println("영속상태 =======");
            System.out.println("커밋전 !!!");
            tx.commit();
            System.out.println("커밋후 !!!");
            System.out.println("m1 = " + m1);
            Member m2 = em.find(Member.class, 10L);
            System.out.println("m2 = " + m2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
