package hellojpa;

import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpa_autoincrement {
    public static void main(String[] args) {
        // 스프링이 EMF 를 만들어줌. 스프링을 안써서 직접 만듬
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 웹 - localhost:8080/members/add
        System.out.println("트랜잭션 시작전 !!!");

        try {
            tx.begin();
            Member member1 = new Member("test");
            Member member2 = new Member("test");
            Member member3 = new Member("test");
            System.out.println("비영속상태 =======");

            em.persist(member1);
            System.out.println("1영속상태 =======");
            em.persist(member2);
            System.out.println("2영속상태 =======");
            em.persist(member3);
            System.out.println("3영속상태 =======");

            Member fm1 = em.find(Member.class, member1.id);
            System.out.println("1차 캐시에서 가져옴 =======");
            System.out.println("fm1 = " + fm1);

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

