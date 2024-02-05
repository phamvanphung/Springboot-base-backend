package com.fucota.base.component.device.dto.request;

import com.fucota.base.component.device.entity.Device;
import com.fucota.base.component.transaction.entity.Transaction;
import com.fucota.base.core.BaseRequestData;
import com.fucota.base.core.constants.BaseConstants;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class GetPageDeviceRequest extends BaseRequestData implements Specification<Device> {

    private String keyword;

    private Integer page = BaseConstants.DEFAULT_PAGE_NUMBER;
    private Integer pageSize = BaseConstants.DEFAULT_PAGE_SIZE;
    private Sort.Direction sortDirection = Sort.Direction.DESC;
    private String sortBy = BaseConstants.PAGE_SORT_BY_DATE;

    @Override
    public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        if (StringUtils.isNotBlank(keyword)) {
            String keywords = "%" + keyword + "%";
            predicateList.add(
                criteriaBuilder.or(
                    criteriaBuilder.like(root.get(Device.Fields.id), keywords),
                    criteriaBuilder.like(root.get(Device.Fields.deviceId), keywords),
                    criteriaBuilder.like(root.get(Device.Fields.terminalId), keywords),
                    criteriaBuilder.like(root.get(Device.Fields.actualAccount), keywords)
                )
            );
        }


        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }

    public Pageable getPageable() {
        return page != null ? PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy)) : Pageable.unpaged();
    }
}
