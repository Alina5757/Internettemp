package ustu.is.InternetLab.gsm.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class GSM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    @NotBlank(message = "Name can't be null or empty")
    private String name;

    public GSM(){
    }

    public GSM(String name) {
        this.name = name;
    }

    public Long getId(){
        return  id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()){ return false; }
        GSM gsm = (GSM) o;
        return Objects.equals(id, gsm.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return "GSM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}