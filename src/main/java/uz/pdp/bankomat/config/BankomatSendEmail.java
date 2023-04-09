package uz.pdp.bankomat.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.bankomat.entity.User;

import java.util.Optional;
import java.util.UUID;

public class BankomatSendEmail implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null
                && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")) {
            User user = (User) authentication.getPrincipal();

            return Optional.of(user.getId());
        }


        return Optional.empty();
    }
}
