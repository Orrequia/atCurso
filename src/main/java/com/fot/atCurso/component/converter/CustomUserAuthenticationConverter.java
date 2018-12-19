package com.fot.atCurso.component.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class CustomUserAuthenticationConverter implements UserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication userAuthentication) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put(USERNAME, userAuthentication.getName());

        if (userAuthentication.getAuthorities() != null && !userAuthentication.getAuthorities().isEmpty())
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(userAuthentication.getAuthorities()));

        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {

        log.info(map.get(USERNAME).toString());

        if (map.containsKey(USERNAME))
            return new UsernamePasswordAuthenticationToken(map.get(USERNAME).toString(), "N/A", getAuthorities(map));
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {

       log.info(map.get(AUTHORITIES).toString());

        if (!map.containsKey(AUTHORITIES))
            return Collections.EMPTY_LIST;

        Object authorities = map.get(AUTHORITIES);

        if (authorities instanceof String)
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);

        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(
                    StringUtils.collectionToCommaDelimitedString((Collection<?>) authorities));
        }

        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }
}
