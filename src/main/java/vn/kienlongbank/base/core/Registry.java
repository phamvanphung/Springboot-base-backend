package vn.kienlongbank.base.core;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class Registry implements InitializingBean {
    private static final Map<Class<? extends BaseRequestData>, RequestHandler<? extends BaseRequestData, ? extends BaseResponseData>> HANDLER_MAP = new ConcurrentHashMap<>();
    private final List<RequestHandler<? extends BaseRequestData, ? extends BaseResponseData>> handlers;

    private void initHandlerBeans() {
        for (var handlerClass : handlers) {
            var requestTypes = GenericTypeResolver.resolveTypeArguments(handlerClass.getClass(), RequestHandler.class);
            if (requestTypes != null && requestTypes.length > 0 && requestTypes[0] != null) {
                HANDLER_MAP.put((Class<? extends BaseRequestData>) requestTypes[0], handlerClass);
            }
        }
    }

    public RequestHandler<BaseRequestData, BaseResponseData> getHandler(Class<? extends BaseRequestData> requestCommandClass) {
        RequestHandler<BaseRequestData, BaseResponseData> handler = (RequestHandler<BaseRequestData, BaseResponseData>) HANDLER_MAP.get(requestCommandClass);
        return (handler == null ? UnsupportedRequestHandler.getInstance() : handler);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.initHandlerBeans();
    }
}
