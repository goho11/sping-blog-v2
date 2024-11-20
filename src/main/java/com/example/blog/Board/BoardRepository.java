package com.example.blog.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    // JPA는 EntityManager로 DB에 접근 (자바에서 DBConnection)
    private final EntityManager em;

    // 1118 글삭제
    public void delete(int id) {
        Query q = em.createNativeQuery("delete from board_tb where id=?");
        q.setParameter(1, id);
        q.executeUpdate(); // insert, update, delete SQL쿼리문 실행
    }


    public void save(Board board) {
        em.persist(board);
    }

    // 글전체보기
    public List<Board> findAll() {
        Query q = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        return q.getResultList(); // 통신코드
    }

    // 1118 글상세보기
    public Board findById(int id) {
        Query q = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        q.setParameter(1, id); // 물음표 완성하기 (물음표 순서, 물음표에 바인딩될 변수값)
        // (Board) : 다운캐스팅. getSingleResult 타입은 object
        return (Board) q.getSingleResult(); // 통신코드. 한개만 반환
    }

    // 1119 글수정
    public void update(int id, String title, String content) {
        Query q = em.createNativeQuery("update board_tb set title =?, content =? where id =?");
        q.setParameter(1, title);
        q.setParameter(2, content);
        q.setParameter(3, id);
        q.executeUpdate(); // insert, update, delete SQL쿼리문 실행 -안적으면 수정 적용 안됨
    }
}