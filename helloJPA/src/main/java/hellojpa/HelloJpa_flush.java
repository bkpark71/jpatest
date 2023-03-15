package hellojpa;

import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpa_flush {
    public static void main(String[] args) {
        // 스프링이 EMF 를 만들어줌. 스프링을 안써서 직접 만듬
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 웹 - localhost:8080/members/add
        System.out.println("flush 트랜잭션 시작전 !!!");

        try {
            tx.begin();

            Member m1 = new Member("test2");
            em.persist(m1);
            System.out.println("영속상태 =======");
            em.flush();
            System.out.println("커밋전 !!!");
            tx.commit();
            System.out.println("커밋후 !!!");
            System.out.println("m1 = " + m1);
            Member m2 = em.find(Member.class, 200L);
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
