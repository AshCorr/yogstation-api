package net.yogstation.api.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class AuthorizedSession {
    private List<String> permissions = new ArrayList<>();
    private String activeProfile;

    public AuthorizedSession(@Value("${spring.profiles.active}") String activeProfile) {
        this.activeProfile = activeProfile;
    }

    public boolean hasPermission(String permission) {
        if ("development".equalsIgnoreCase(activeProfile)) {
            return true;
        }

        return permissions.contains(permission);
    }
}
