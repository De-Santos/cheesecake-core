package com.user.sevice.userservice.utils.additional.checker;



import com.user.sevice.userservice.utils.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.exception.ProductNotFoundException;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProductChecker {

    private final Template template;

    public boolean check(String versionId) {
        log.debug("product check by id: {}", versionId);
        boolean result = template.isRealProduct(versionId);
        if (!result) throw new ProductNotFoundException();
        return result;
    }

}
