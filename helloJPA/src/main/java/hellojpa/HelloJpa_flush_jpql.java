package hellojpa;

import domain.Member;

import javax.persistence.*;
import java.util.List;

public class HelloJpa_flush_jpql {
    public static void main(String[] args) {
        // 스프링이 EMF 를 만들어줌. 스프링을 안써서 직접 만듬
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 웹 - localhost:8080/members/add
        System.out.println("flush 트랜잭션 시작전 !!!");

        try {
            tx.begin();

            Member m1 = new Member("test300");
            Member m2 = new Member("test301");
            Member m3 = new Member("test302");

            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            System.out.println("영속상태 =======");

            //em.setFlushMode(FlushModeType.COMMIT);
            List<Member> members = em.createQuery("select m from Member as m where username like :username", Member.class)
                    .setParameter("username", "test%")
//                    .setFirstResult(0)
//                    .setMaxResults(5)
                    .getResultList();
            for(Member member : members) {
                System.out.println("member = " + member);
            }

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
