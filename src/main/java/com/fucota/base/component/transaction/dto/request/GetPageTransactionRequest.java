package com.fucota.base.component.transaction.dto.request;

import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.component.transaction.enums.TransactionStatus;
import com.fucota.base.core.BaseRequestData;
import com.fucota.base.core.constants.BaseConstants;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class GetPageTransactionRequest extends BaseRequestData implements Specification<Transaction> {

    private String keyword;
    @Hidden
    private String deviceId;
    private TransactionStatus status;
    private ZonedDateTime fromDate;
    private ZonedDateTime toDate;

    private Integer page = BaseConstants.DEFAULT_PAGE_NUMBER;
    private Integer pageSize = BaseConstants.DEFAULT_PAGE_SIZE;
    private Sort.Direction sortDirection = Sort.Direction.DESC;
    private String sortBy = BaseConstants.PAGE_SORT_BY_DATE;

    @Override
    public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();

        if (StringUtils.isNotBlank(keyword)) {
            String keywords = "%" + keyword + "%";
            predicateList.add(
                criteriaBuilder.or(
                    criteriaBuilder.like(root.get(Transaction.Fields.id), keywords),
                    criteriaBuilder.like(root.get(Transaction.Fields.deviceId), keywords),
                    criteriaBuilder.like(root.get(Transaction.Fields.tid), keywords)
                )
            );
        }

        if (Objects.nonNull(fromDate)) {
            predicateList.add(criteriaBuilder.greaterThanOrEqualTo(
                criteriaBuilder.function("date", ZonedDateTime.class, root.get(Transaction.Fields.createdAt)), fromDate));
        }

        if (Objects.nonNull(toDate)) {
            predicateList.add(criteriaBuilder.lessThanOrEqualTo(
                criteriaBuilder.function("date", ZonedDateTime.class, root.get(Transaction.Fields.createdAt)), toDate));
        }

//        if (Objects.nonNull(status)) {
//            predicateList.add(criteriaBuilder.equal(root.get(Transaction.Fields.status), status));
//        }

        if (StringUtils.isNotBlank(deviceId)) {
            predicateList.add(criteriaBuilder.equal(root.get(Transaction.Fields.deviceId), deviceId));
        }

//        predicateList.add(criteriaBuilder.equal(root.get(Transaction.Fields.status), status));
        if (Objects.nonNull(status)) {
            predicateList.add(criteriaBuilder.equal(root.get(Transaction.Fields.status), status));
        } else {
            List<TransactionStatus> statuses = Arrays.asList(TransactionStatus.FAILED, TransactionStatus.SUCCESS, TransactionStatus.CANCEL, TransactionStatus.TIMEOUT);
            predicateList.add(criteriaBuilder.in(root.get(Transaction.Fields.status)).value(statuses));
        }

        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }


    public Pageable getPageable() {
        return page != null ? PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy)) : Pageable.unpaged();
    }
}
