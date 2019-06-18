package hu.icell.organiser.service;

import hu.icell.organiser.entity.Reserved;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservedService {
    Reserved createReserved (Reserved reserved);
    Reserved getReserved (Integer id);
    Reserved editReserved (Reserved reserved);
    void deleteReserved (Reserved reserved);
    void deleteReserved (Integer id);
    List<Reserved> getAllReserved();
    Reserved getSpecificReserved (String portNumber);
    List<Reserved> findAllByPortNumber(String portNumber);
    Page<Reserved> findAllByPortNumberEquals(String portNumber, Pageable pageable);
    Reserved create (Reserved reserved);
    Reserved edit (Reserved reserved);
}