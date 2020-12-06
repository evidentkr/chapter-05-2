package com.rubypaper;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryAnnotationTest {
	@Autowired
	private BoardRepository boardRepo;

	@Before
	public void dataPrepare() {
		for (int i = 1; i <= 200; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 " + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
	}
	
	//276 - 위치 기반 파라미터  /  이름 기반 파라미터
//	@Test
//	public void testQueryAnnotationTest1() {
//	    List<Board> boardList = boardRepo.queryAnnotationTest1("테스트 제목 10");
//	    
//	    System.out.println("검색 결과");
//	    for (Board board : boardList) {
//		System.out.println("---> " + board.toString());
//	    }
//	}
	
	// 278 - 특정변수만 조회
//	@Test
//	public void testQueryAnnotationTest2() {
//		List<Object[]> boardList = boardRepo.queryAnnotationTest2("테스트 제목 10");
//
//		System.out.println("검색 결과");
//		for (Object[] row : boardList) {
//			System.out.println("---> " + Arrays.toString(row));
//		}
//	}
	
	//280 네이티브 쿼리
//	@Test
//	public void testQueryAnnotationTest3() {
//		List<Object[]> boardList = boardRepo.queryAnnotationTest3("테스트 제목 10");
//
//		System.out.println("검색 결과");
//		for (Object[] row : boardList) {
//			System.out.println("---> " + Arrays.toString(row));
//		}
//	}
	
	//282 페이징 및 정렬 처리
	@Test
	public void testQueryAnnotationTest4() {
		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq");
		List<Board> boardList = boardRepo.queryAnnotationTest4(paging);
				
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
}