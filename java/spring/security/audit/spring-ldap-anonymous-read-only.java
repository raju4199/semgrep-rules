import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
public class LdapConfig {

    @Bean
    public LdapContextSource anonymousContextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://ldap.example.com:389");
        contextSource.setBase("dc=example,dc=com");
        // ruleid: spring-ldap-anonymous-read-only
        contextSource.setAnonymousReadOnly(true);
        return contextSource;
    }

    @Bean
    public DefaultSpringSecurityContextSource securityContextSource() {
        DefaultSpringSecurityContextSource source =
            new DefaultSpringSecurityContextSource("ldap://ldap.example.com:389/dc=example,dc=com");
        // ruleid: spring-ldap-anonymous-read-only
        source.setAnonymousReadOnly(true);
        return source;
    }

    @Bean
    public LdapContextSource authenticatedContextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://ldap.example.com:389");
        contextSource.setUserDn("cn=app,dc=example,dc=com");
        contextSource.setPassword(System.getenv("LDAP_PASSWORD"));
        // ok: spring-ldap-anonymous-read-only
        contextSource.setAnonymousReadOnly(false);
        return contextSource;
    }
}
