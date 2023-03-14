package hellojpa;

import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        System.out.println("트랜잭션 시작전 !!!");

        tx.begin();

        try {
            Member member = new Member();
            member.username="aaa";
            Member member2 = new Member();
            member2.username="bbb";
            System.out.println("비영속상태 =======");
            em.persist(member);
            em.persist(member2);
            System.out.println("persist 이후 =======");
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
