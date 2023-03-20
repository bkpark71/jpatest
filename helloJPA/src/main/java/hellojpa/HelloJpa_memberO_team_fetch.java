package hellojpa;

import domain.MemberO;
import domain.MemberType;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpa_memberO_team_fetch {
    public static void main(String[] args) {
        // 스프링이 EMF 를 만들어줌. 스프링을 안써서 직접 만듬
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 웹 - localhost:8080/members/add
        System.out.println("memberO_team 등록 트랜잭션 시작전 !!!");

        try {
            tx.begin();
            Team team = em.find(Team.class, 1L);
            // 멤버 정보 가져오기
            MemberO m1 = new MemberO();
            m1.setUsername("test200");
            m1.setTeam(team);
            em.persist(m1);

            MemberO m2 = new MemberO();
            m1.setUsername("test300");
            m1.setTeam(team);
            em.persist(m2);

            em.flush();
            em.clear();

            MemberO fm = em.find(MemberO.class, m1.getId()); // class , em 영속성 상태
//            MemberO fm1 = em.getReference(MemberO.class,m2.getId()); // proxy
//            MemberO fm2 = em.getReference(MemberO.class, m1.getId()); // proxy ? class ?

            MemberO fm2 = em.find(MemberO.class, m2.getId());

            System.out.println("fm.getUsername() = " + fm.getUsername());
            System.out.println("============");
            System.out.println("fm.id = " + fm.getId());
            System.out.println("============eager select ");
//            System.out.println("fm.team = " + fm.getTeam());
//            System.out.println("team.name = " + fm.getTeam().getName());

            System.out.println("fm2.getUsername() = " + fm2.getUsername());
            System.out.println("============");
            System.out.println("fm.id = " + fm2.getId());
            System.out.println("============eager select ");
//            System.out.println("fm.team = " + fm2.getTeam());
//            System.out.println("team.name = " + fm2.getTeam().getName());
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
}
