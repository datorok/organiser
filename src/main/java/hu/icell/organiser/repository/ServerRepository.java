package hu.icell.organiser.repository;

import hu.icell.organiser.entity.Server;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Integer> {
    List<Server> findAllByServerName(String serverName);
    Page<Server> findAllByServerNameEquals(String serverName, Pageable pageable);
}