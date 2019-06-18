package hu.icell.organiser.service;

import hu.icell.organiser.entity.Server;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServerService {
    Server createServer (Server server);
    Server getServer (Integer id);
    Server editServer (Server server);
    void deleteServer (Server server);
    void deleteServer (Integer id);
    List<Server> getAllServer();
    Server getSpecificServer (String serverName);
    List<Server> findAllByServerName(String ServerName);
    Page<Server> findAllByServerNameEquals(String serverName, Pageable pageable);
    Server create (Server server);
    Server edit (Server server);

}
