package com.carshop.mycarshop.jpa;


import com.carshop.mycarshop.config.JpaAuditingConfiguration;
import com.carshop.mycarshop.domain.test.Book;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db를 사용하기 위해

@TestPropertySource(locations = "classpath:application-test.properties")
@Log4j2
@Import(JpaAuditingConfiguration.class)
public class EntityManager2Tests {

    @PersistenceUnit
    EntityManagerFactory emf;

    //    @PersistenceContext
    //    EntityManager em;     // 순수 jpa를 사용할때
    @Test
    public void save() {

        // 책 : 자바 퍼시스턴스 프로그래밍 p332
        ///////////////////////////////////////////////////////////////////////
        //2. 순수 hibernate 사용
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Book book = em.find(Book.class, 1L);// 영속성 데이터 조회
        log.error("book : " + book);
        em.flush();
        em.clear();

        // em.getReference - 데이터베이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회
        Book book1 = em.getReference(Book.class, 1L); // proxy 생성, 이때 select 하지 않는다

        // 초기화 되지 않은 프락시를 사용 중인지 감지
        PersistenceUnitUtil persistenceUnitUtil = emf.getPersistenceUnitUtil();
        assertFalse(persistenceUnitUtil.isLoaded(book1));

        log.error("=======================");
//        log.error("book1 : " + book1);  // 실제 get할때 select 문 실행
//        assertEquals(book1.getBookId() , 1L);

        Hibernate.initialize(book1);       // 프락시 데이터를 로드 한다
        log.error("=======================");
        log.error("book1 : " + book1);

        assertTrue(persistenceUnitUtil.isLoaded(book1));

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void delete() {

        // 책 : 자바 퍼시스턴스 프로그래밍 p334
        ///////////////////////////////////////////////////////////////////////
        //2. 순수 hibernate 사용
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Book book = em.find(Book.class, 5L);
        em.remove(book);

        assertFalse(em.contains(book));  // 더이상 영속성 켄텍스트에 포함되지 않는다

        //em.persist(book); // 제거된 인스턴스를 다시 영속화
        log.error("========================================");

        em.getTransaction().commit();
        em.close();

        log.error(book);
    }

    @Test
    public void refresh() throws InterruptedException {

        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();

        Book book = em.find(Book.class, 6L);

        book.setBookNumber("5345345");

        log.error("==========================================");
        Thread.sleep(10000); //5초 대기
        // db상에서 bookNumber를 업데이트
        log.error("==================refresh========================");
        em.refresh(book);

        // 트랜잭션 처리를 하지 않아야 db의 경신된 값이 설정 된다
        log.error("bookNumber 1 : " + book.getBookNumber());
       // em.getTransaction().commit();
        em.close();


    }
}
