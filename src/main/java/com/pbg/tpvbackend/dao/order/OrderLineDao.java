package com.pbg.tpvbackend.dao.order;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pbg.tpvbackend.model.order.OrderLine;
import com.pbg.tpvbackend.model.order.OrderLinePK;

public interface OrderLineDao extends PagingAndSortingRepository<OrderLine, OrderLinePK> {

}
