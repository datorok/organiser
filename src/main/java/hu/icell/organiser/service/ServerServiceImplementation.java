package hu.icell.organiser.service;

import hu.icell.organiser.entity.Server;
import hu.icell.organiser.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerServiceImplementation implements ServerService{

    @Autowired
    private ServerRepository serverRepository;

    @Override
    public Server createServer(Server server) {
        return serverRepository.save(server);
    }

    @Override
    public Server getServer(Integer id) {
        return serverRepository.getOne(id);
    }

    @Override
    public Server editServer(Server server) {
        return serverRepository.save(server);
    }

    @Override
    public void deleteServer(Server server) {
        serverRepository.delete(server);

    }

    @Override
    public void deleteServer(Integer id) {
        serverRepository.deleteById(id);
    }

    @Override
    public List<Server> getAllServer() {
        return serverRepository.findAll();
    }

    @Override
    public Server getSpecificServer(String serverName) {
        return serverRepository.findAllByServerName(serverName).get(0);
    }

    @Override
    public List<Server> findAllByServerName(String serverName) {
        return serverRepository.findAllByServerName(serverName);
    }

    @Override
    public Page<Server> findAllByServerNameEquals(String serverName, Pageable pageable) {
        return serverRepository.findAllByServerNameEquals(serverName, pageable);
    }

    @Override
    public Server create(Server server) {
        return serverRepository.saveAndFlush(server);
    }

    @Override
    public Server edit(Server server) {
        return serverRepository.saveAndFlush(server);
    }

}
