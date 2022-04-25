package kr.rimu.rimboard.domain.repository;

import kr.rimu.rimboard.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
