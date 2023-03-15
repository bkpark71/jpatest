package hellojpa;

import domain.MemberO;
import domain.MemberType;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class HelloJpa_memberO_team_v2 {
    public static void main(String[] args) {
        // 스프링이 EMF 를 만들어줌. 스프링을 안써서 직접 만듬
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 웹 - localhost:8080/members/add
        System.out.println("memberO_team 등록 트랜잭션 시작전 !!!");

        try {
            tx.begin();
            // 팀번호가 1 인 팀을 찾아온다.
            Team team = em.find(Team.class, 1L);
            System.out.println("team = " + team);
            // 1번 멤버를 찾아온다. => teamD 로 변경
//            MemberO member = em.find(MemberO.class, 1L);
//            member.setTeam(team);
            // 전체 멤버의 팀 참조를 team 으로 변경해준다.
            List<MemberO> members = em.createQuery("select m from MemberO m", MemberO.class)
                    .getResultList();

            for(MemberO m :members){
                m.setTeam(team);
            }
        //     수정 ???
            System.out.println("영속상태 =======");

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
