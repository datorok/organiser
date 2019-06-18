package hu.icell.organiser.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reserved")
public class Reserved implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserved_id")
    private Integer reservedId;

    @Column(name = "port_number")
    private String portNumber;

    public Reserved() {
    }

    public Integer getReservedId() {
        return reservedId;
    }

    public void setReservedId(Integer reservedId) {
        this.reservedId = reservedId;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

}
