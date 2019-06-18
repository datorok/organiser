package hu.icell.organiser.repository;

import hu.icell.organiser.entity.Reserved;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface ReservedRepository extends JpaRepository<Reserved, Integer> {
        List<Reserved> findAllByPortNumber(String portNumber);
        Page<Reserved> findAllByPortNumberEquals(String portNumber, Pageable pageable);

    }

