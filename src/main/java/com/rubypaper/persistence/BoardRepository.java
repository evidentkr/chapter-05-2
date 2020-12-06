package com.rubypaper.persistence;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rubypaper.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
	
	//QueryMethodTest.java
	List<Board> findByTitle(String searchKeyword);

	//Like 연산자
	List<Board> findByContentContaining(String searchKeyword);
	
	//여러 조건 (제목/본문)
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	//정렬
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	
	//페이징과 정렬 처리
	//List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	
	
	
	//275 - QueryAnnotationTest.java 위치기반 파리미터
//	@Query("SELECT b FROM Board b " + "WHERE b.title like %?1% ORDER BY b.seq DESC")
//	List<Board> queryAnnotationTest1(String searchKeyword);
	
	//277 - 이름기반 파라미터
	@Query("SELECT b FROM Board b " + "WHERE b.title like %:searchKeyword%  " + "ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(@Param("searchKeyword") String searchKeyword);
	
	@Query("SELECT b.seq, b.title, b.writer, b.createDate " + "FROM Board b " + "WHERE b.title like %?1% " + "ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
	
	//280 네이티브 쿼리
	@Query(value = "select seq, title, writer, create_date " + "from board where title like '%'||?1||'%' " + "order by seq desc", nativeQuery = true)
	List<Object[]> queryAnnotationTest3(String searchKeyword);
	
	//282 페이징 및 정렬 처리
	@Query("SELECT b FROM Board b ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest4(Pageable paging);
}
