package kit.personal;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
// import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckTokenResponse {
    public Set<String> aud;
    public String user_name;
    public Set<String> scope;
    public boolean active;
    public BigInteger exp;
    public Set<String> authorities;
    public String client_id;
    public Map<String, Object> getMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_name", user_name);
        map.put("scope", scope);
        return map;
    }
}
