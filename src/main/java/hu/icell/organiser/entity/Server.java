package hu.icell.organiser.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "server")
public class Server implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "server_id")
    private Integer serverId;

    @Column(name = "server_name")
    private String serverName;

    @Column(name = "op_sys")
    private String opSys;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "ram")
    private int ram;

    @Column(name = "ip")
    private String ip;

    @Column(name = "db")
    private String db;

    @Column(name = "file_db")
    private String fileDb;

    @Column(name = "ifleet_web")
    private String ifleetWeb;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "external_ip")
    private boolean externalIP;

    @ManyToMany
    @JoinTable(
            name = "server_service",
            inverseJoinColumns = {@JoinColumn(name = "service_id")},
            joinColumns = {@JoinColumn(name = "server_id")}
    )
    private List<Service> serviceList;

    public Server() {
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getOpSys() {
        return opSys;
    }

    public void setOpSys(String opSys) {
        this.opSys = opSys;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getFileDb() {
        return fileDb;
    }

    public void setFileDb(String fileDb) {
        this.fileDb = fileDb;
    }

    public String getIfleetWeb() {
        return ifleetWeb;
    }

    public void setIfleetWeb(String ifleetWeb) {
        this.ifleetWeb = ifleetWeb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isExternalIP() {
        return externalIP;
    }

    public void setExternalIP(boolean externalIP) {
        this.externalIP = externalIP;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @Override
    public String toString() {
        return serverName;
    }
}
