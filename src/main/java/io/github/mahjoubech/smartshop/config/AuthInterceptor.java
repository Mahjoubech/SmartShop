package io.github.mahjoubech.smartshop.config;

import io.github.mahjoubech.smartshop.exception.AuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AuthInterceptor implements HandlerInterceptor {
    public static final String USER_ID_KEY = "USER_ID";
    public static final String USER_ROLE_KEY = "USER_ROLE";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session == null || session.getAttribute(USER_ID_KEY)== null){
            throw new AuthorizationException("Session expirée ou non authentifié. Veuillez vous connecter.");
    }
        String sessionUserRole = session.getAttribute(USER_ROLE_KEY).toString();
        String requestPath = request.getRequestURI();
        if(requestPath.startsWith("/api/client") || requestPath.startsWith("/api/products")){
            if("CLIENT".equals(sessionUserRole) && !requestPath.startsWith("/api/products")){
                throw new AuthorizationException("Accès refusé. Seul l'ADMIN peut créer, modifier ou supprimer ces ressources.");
            }
            if ("CLIENT".equals(sessionUserRole) && requestPath.startsWith("/api/client")) {
                throw new AuthorizationException("Accès refusé. Un CLIENT ne peut pas voir la liste des autres clients.");
            }
        }
    return true;

}



}
