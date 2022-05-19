package ustu.is.InternetLab.GSMWorker.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class GSMWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private Long IdGSM;
    private Long IdWorker;

    public GSMWorker(){
    }

    public GSMWorker(Long IdGSM, Long IdWorker) {
        this.IdGSM = IdGSM;
        this.IdWorker = IdWorker;
    }

    public Long getId(){
        return  id;
    }

    public Long getIdGSM() {
        return IdGSM;
    }

    public void setIdGSM(Long IdGSM) {
        this.IdGSM = IdGSM;
    }

    public Long getIdWorker() {
        return IdWorker;
    }

    public void setIdWorker(Long IdWorker) {
        this.IdWorker = IdWorker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()){ return false; }
        GSMWorker gsmWorker = (GSMWorker) o;
        return Objects.equals(id, gsmWorker.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return "GSMWorker{" +
                "id=" + id +
                ", IdGSM='" + IdGSM + '\'' +
                ", IdWorker='" + IdWorker + '\'' +
                '}';
    }
}