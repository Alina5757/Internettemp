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
    private String Name;

    public GSM(){
    }

    public GSM(String Name) {
        this.Name = Name;
    }

    public Long getId(){
        return  id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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
                ", Name='" + Name + '\'' +
                '}';
    }
}