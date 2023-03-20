package hellojpa;

import domain.MemberO;
import domain.MemberType;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpa_memberO_team_fetch2 {
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
            team.setName("teamB");
            em.persist(team);
            System.out.println("team = " + team);
            // 멤버 정보 가져오기
            MemberO m1 = new MemberO("test1", MemberType.GENERAL);
            // 참조를 사용해서 연관관계 설정
            m1.setTeam(team);
            em.persist(m1);

            MemberO m2 = new MemberO("test1", MemberType.GENERAL);
            // 참조를 사용해서 연관관계 설정
            m2.setTeam(team);
            em.persist(m2);

            em.flush();
            em.clear();

            MemberO fm0 = em.getReference(MemberO.class, m1.getId()); //f0 == f3
            MemberO fm1 = em.find(MemberO.class, m1.getId());
            MemberO fm2 = em.getReference(MemberO.class, m2.getId());
            MemberO fm3 = em.getReference(MemberO.class, m1.getId());
            System.out.println("fm2 == fm1 " + (fm2.getClass() == fm1.getClass()));
            System.out.println("fm3 == fm1 " + (fm3.getClass() == fm1.getClass()));
            // 참조를 사용해서 연관관계 조회
//            Team ft = fm.getTeam();
//            System.out.println("ft = " + ft);
//            Team ft = em.find(Team.class, team.getTeamId());

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

//    private static void logic(MemberO m1, MemberO m2) {
//        System.out.println("m2 == m1 " + (m2 == m1));
//        System.out.println("m2 == m1 " + (m2 instanceof m1));
//    }
}
