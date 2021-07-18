package holenote.business.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name="users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToOne
    private District district;
    @ManyToOne
    private Organization organization;
    private String realname;
    private String telephone;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="authorities",joinColumns = @JoinColumn(name="username",referencedColumnName = "username"))
    @Column(name="authority")
    private Collection<String> authorities=new ArrayList<String>();
    private boolean enabled;

    public Long getId() {
        return id;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> auth=new ArrayList<>();
        for(String authority:authorities){
            auth.add(new SimpleGrantedAuthority(authority));
        }
        if(auth.isEmpty()){
            auth.add(new SimpleGrantedAuthority("user"));
        }
        return auth;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnable(boolean enable) {
        this.enabled = enable;
    }
   
}
