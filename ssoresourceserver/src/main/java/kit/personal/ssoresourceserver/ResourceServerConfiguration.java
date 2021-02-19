package kit.personal.ssoresourceserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${oauth.check_token.uri}")
    private String checkTokenUri;
    protected void configure(HttpSecurity http) {
        try {
            http.authorizeRequests(
                    authorizeRequests -> authorizeRequests
                        .mvcMatchers("/user/read").hasAuthority("SCOPE_read")
                        .mvcMatchers("/user/write").hasAuthority("SCOPE_write")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(
                    oauth2 -> oauth2
                        .opaqueToken(opaqueToken -> opaqueToken
                        .introspectionUri(checkTokenUri)
                        .introspectionClientCredentials("spring-security-oauth2-read-write-client", "spring-security-oauth2-read-write-client-password1234")
                    ));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
