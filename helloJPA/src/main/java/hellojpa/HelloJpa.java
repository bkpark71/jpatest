package hellojpa;

import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpa {
    public static void main(String[] args) {
        // 스프링이 EMF 를 만들어줌. 스프링을 안써서 직접 만듬
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 웹 - localhost:8080/members/add
        System.out.println("트랜잭션 시작전 !!!");

        try {
            tx.begin();
            Member member = new Member("test");

            System.out.println("비영속상태 =======");
            em.persist(member);
            System.out.println("영속상태 =======");
            Member fm1 = em.find(Member.class, 10L);
            System.out.println("1차 캐시에서 가져옴 =======");
            Member findMember = em.find(Member.class, 1L);

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
