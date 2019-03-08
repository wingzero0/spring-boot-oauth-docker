package kit.personal.ssoserver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class AppUser {
    @Id
    private Integer id;
    private String username;
    private String password;
    private String email;
    
    /**
     * Get id.
     *
     * @return id as Integer.
     */
    public Integer getId()
    {
        return id;
    }
    
    /**
     * Set id.
     *
     * @param id the value to set.
     */
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    /**
     * Get username.
     *
     * @return username as String.
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * Set username.
     *
     * @param username the value to set.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * Get password.
     *
     * @return password as String.
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * Set password.
     *
     * @param password the value to set.
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    /**
     * Get email.
     *
     * @return email as String.
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * Set email.
     *
     * @param email the value to set.
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
}
