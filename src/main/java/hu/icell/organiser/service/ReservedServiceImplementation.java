package hu.icell.organiser.service;

import hu.icell.organiser.entity.Reserved;
import hu.icell.organiser.repository.ReservedRepository;
import hu.icell.organiser.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public class ReservedServiceImplementation implements ReservedService {

    @Autowired
    private ReservedRepository reservedRepository;
    private ServiceRepository serviceRepository;

    @Override
    public Reserved createReserved(Reserved reserved) {
        return reservedRepository.save(reserved);
    }

    @Override
    public Reserved getReserved(Integer id) {
        return reservedRepository.getOne(id);
    }

    @Override
    public Reserved editReserved(Reserved reserved) {
        return reservedRepository.save(reserved);
    }

    @Override
    public void deleteReserved(Reserved reserved) {
        reservedRepository.delete(reserved);
    }

    @Override
    public void deleteReserved(Integer id) {
        reservedRepository.deleteById(id);
    }

    @Override
    public List<Reserved> getAllReserved() {
        return reservedRepository.findAll();
    }

    @Override
    public Reserved getSpecificReserved(String portNumber) {
        return reservedRepository.findAllByPortNumber(portNumber).get(0);
    }

    @Override
    public List<Reserved> findAllByPortNumber(String portNumber) {
        return reservedRepository.findAllByPortNumber(portNumber);
    }

    @Override
    public Page<Reserved> findAllByPortNumberEquals(String portNumber, Pageable pageable) {
        return findAllByPortNumberEquals(portNumber, pageable);
    }

    @Override
    public Reserved create(Reserved reserved) {
        return reservedRepository.saveAndFlush(reserved);
    }

    @Override
    public Reserved edit(Reserved reserved) {
        return reservedRepository.saveAndFlush(reserved);
    }


}
