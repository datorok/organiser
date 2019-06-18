package hu.icell.organiser.repository;

import hu.icell.organiser.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A @Repository annotáció jelzi a Spring számára, hogy ez az osztály egy repository.
 *
 * A Repository egy olyan interface, ami lehetővé teszi, hogy a kódunk kommunikálni tudjon az entity-kkel, amihez a DB-ből objektumokat fog várni. Az adatbázisból történő lekérdezéshez
 * a Repository fogja generálni az SQL utasításokat.
 *
 * A Repository-k a standard JPA (Java Persistence API) keretrendszerre épülő interfészek, melyek automatizált működésükkel jelentős mértékben képesek rövidíteni a perzisztencia réteg implementálását.
 * Az itt használt JpaRepository a normál CRUD műveleteken túlmenően támogatja a lapozást is (amit végülis nem használunk, mert nagyon körülményessé teszi a keresést). A JpaRepository (és a többi JPA-t
 * implementáló Repository) előnye, hogy a szülő interface biztosít számunkra pár előre megírt lekérdezést, így ezeket nem szükséges minden alkalommal újra megírnunk (pl: save,findAll). A JPA egy
 * plusz réteg a JDBC-n, így használata bizonyos esetekben valamelyest megnöveli a lekérdezés idejét, hiszen a JPA-ban megfogalmazott lekérdezéseket le kell fordítani SQL nyelvre, amit már meg tud
 * érteni az adatbázis. Saját szintaktikája van, ami szerencsére egyszerűbb, mint az SQL.
 */

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    Service findByServiceName(String ServiceName);
    Page<Service> findAllByServiceNameEquals(String serverName, Pageable pageable);
}
