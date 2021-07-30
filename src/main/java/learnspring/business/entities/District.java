package learnspring.business.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class District {
    @Id
    private String code;
    private String name;
    private boolean enabled=false;

    public District(){}

    public District(String code){
        this.code=code;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
}
