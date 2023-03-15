package hellojpa;

import domain.MemberO;
import domain.MemberType;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpa_memberO_team {
    public static void main(String[] args) {
        // 스프링이 EMF 를 만들어줌. 스프링을 안써서 직접 만듬
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 웹 - localhost:8080/members/add
        System.out.println("memberO_team 등록 트랜잭션 시작전 !!!");

        try {
            tx.begin();
            // 팀 생성
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);
            System.out.println("team = " + team);
            // 멤버 생성
            MemberO member = new MemberO("test1", MemberType.EXECUTIVE);
            // 참조를 사용해서 연관관계 설정
            member.setTeam(team);
            em.persist(member);
            System.out.println("영속상태 =======");

            MemberO fm = em.find(MemberO.class, member.getId());
            System.out.println("1차 캐시에서 가져옴 =======");
            System.out.println("fm = " + fm);
            // 참조를 사용해서 연관관계 조회
            Team ft = fm.getTeam();
            //Team ft = em.find(Team.class, team.getTeamId());
            System.out.println("ft = " + ft);

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
