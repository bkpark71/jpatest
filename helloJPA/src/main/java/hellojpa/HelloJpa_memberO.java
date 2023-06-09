package hellojpa;

import domain.MemberO;
import domain.MemberType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpa_memberO {
    public static void main(String[] args) {
        // 스프링이 EMF 를 만들어줌. 스프링을 안써서 직접 만듬
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 웹 - localhost:8080/members/add
        System.out.println("memberO 등록 트랜잭션 시작전 !!!");

        try {
            tx.begin();
            MemberO member = new MemberO("test4", MemberType.EXECUTIVE);
            MemberO member1 = new MemberO("test5", MemberType.MANAGER);
            MemberO member2 = new MemberO("test6", MemberType.GENERAL);

            System.out.println("비영속상태 =======");
            em.persist(member);
            em.persist(member1);
            em.persist(member2);
            System.out.println("영속상태 =======");

            MemberO fm = em.find(MemberO.class, member.getId());
            MemberO fm1 = em.find(MemberO.class, member1.getId());
            MemberO fm2 = em.find(MemberO.class, member2.getId());
            System.out.println("1차 캐시에서 가져옴 =======");
            System.out.println("fm = " + fm);
            System.out.println("fm1 = " + fm1);
            System.out.println("fm2 = " + fm2);

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
