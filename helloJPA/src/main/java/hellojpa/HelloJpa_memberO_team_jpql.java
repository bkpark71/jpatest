package hellojpa;

import domain.MemberO;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class HelloJpa_memberO_team_jpql {
    public static void main(String[] args) {
        // 스프링이 EMF 를 만들어줌. 스프링을 안써서 직접 만듬
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 웹 - localhost:8080/members/add
        System.out.println("memberO_team 등록 트랜잭션 시작전 !!!");

        try {
            tx.begin();
            Team team2 = new Team();
            team2.setName("newTeam");
            em.persist(team2);

            MemberO m1=new MemberO();
            m1.setTeam(team2);
            em.persist(m1);

            // 멤버 정보 가져오기
            System.out.println("============eager select ");
            List resultList = em.createQuery("select m from Member m")
                    .getResultList();
            System.out.println("resultList = " + resultList);

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
