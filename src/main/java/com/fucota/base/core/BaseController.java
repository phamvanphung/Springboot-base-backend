package com.fucota.base.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BaseController {

    @Autowired
    protected SpringBus springBus;

//    @Autowired
//    private RedisService redisService;


    protected <T extends BaseRequestData, I extends ResponseData> ResponseEntity<ResponseBase<I>> execute(T request) {
//        String subjectId = getCurrentSubjectId();
//        if (subjectId != null && !subjectId.equals("anonymous") && !subjectId.equals("anonymousUser")) {
//            request.setSubjectId(getCurrentSubjectId());
//            String value = redisService.getValue(getCurrentSubjectId(), String.class);
//            if (StringUtils.isBlank(value)) {
////                throw new InternalException(ResponseCode.AUTHORIZATION_FAILED);
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            }
//        }
        return ResponseEntity.ok(new ResponseBase<>(this.springBus.execute(request)));
    }

//    protected String getCurrentSubjectId() {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        if (securityContext == null ||
//            securityContext.getAuthentication() == null ||
//            StringUtils.isBlank(securityContext.getAuthentication().getName())) {
//            throw new InternalException(ResponseCode.AUTHORIZATION_FAILED);
//        }
//        return securityContext.getAuthentication().getName();
//    }
//
//    protected ResponseEntity<ResponseBase<String>> buildResponse(PackedMessage packedMessage) {
//        //Set header
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set(HeaderBase.CLIENT, packedMessage.getClientId());
//        responseHeaders.set(HeaderBase.TIMESTAMP, String.valueOf(packedMessage.getTimestamp()));
//        responseHeaders.set(HeaderBase.SIGNATURE, packedMessage.getSignature());
//        return ResponseEntity.ok()
//            .headers(responseHeaders)
//            .body(new ResponseBase<>(packedMessage.getEncryptedData()));
//    }
}
