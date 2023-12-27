package vn.kienlongbank.base.core;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vn.kienlongbank.base.core.enums.CommonResponseCode;
import vn.kienlongbank.base.core.exception.BusinessException;


@Component
@Log4j2
public class SpringBus implements CqrsBus {
    private final Registry registry;

    public SpringBus(Registry registry) {
        this.registry = registry;
    }

    public <T extends RequestData, I extends ResponseData> I execute(T requestData) {
        Handler handler = this.getHandler(requestData);
        if (handler == null || handler instanceof UnsupportedRequestHandler) {
            throw new BusinessException(CommonResponseCode.UNHANDLED_REQUEST);
        }
        return (I) handler.handle(requestData);
    }

    private <T extends RequestData> Handler getHandler(T requestData) {
        if (requestData instanceof BaseRequestData) {
            return this.registry.getHandler(((BaseRequestData) requestData).getClass());
        } else {
            return null;
        }
    }
}

