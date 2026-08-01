package br.eng.jonathan.ntoerp.config.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Auth0JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = defaultGrantedAuthoritiesConverter.convert(jwt);

        List<String> permissions = jwt.getClaimAsStringList("permissions");
        Collection<GrantedAuthority> customAuthorities = List.of();

        if (permissions != null) {
            customAuthorities = permissions.stream()
                    .map(perm -> new SimpleGrantedAuthority("ROLE_" + perm.toUpperCase()))
                    .collect(Collectors.toList());
        }

        Collection<GrantedAuthority> combinedAuthorities = Stream.concat(
                authorities.stream(),
                customAuthorities.stream()
        ).collect(Collectors.toList());

        return new JwtAuthenticationToken(jwt, combinedAuthorities, jwt.getSubject());
    }
}