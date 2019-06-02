package com.pbg.tpvbackend.dao.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.pbg.tpvbackend.dto.statistics.StatisticsTotalsDto;
import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.order.Order;
import com.pbg.tpvbackend.model.order.OrderStatus;

public interface OrderDao extends PagingAndSortingRepository<Order, Integer> {
	
	@Query("SELECT o "
		 + "FROM orders o "
		 + "JOIN o.zone z "
		 + "JOIN z.restaurant r "
		 + "JOIN r.chainRestaurant rc "
		 + "WHERE rc = :chain")
	Page<Order> findByChain(@Param("chain") RestaurantChain chain, Pageable pageable);
	
	@Query("SELECT o "
		 + "FROM orders o "
		 + "JOIN o.zone z "
		 + "JOIN z.restaurant r "
		 + "WHERE r = :restaurant "
		 + "AND o.status = :orderStatus "
		 + "ORDER BY o.createdAt DESC")
	Page<Order> findByRestaurant(@Param("orderStatus") OrderStatus orderStatus, @Param("restaurant") Restaurant restaurant, Pageable pageable);
	
	@Query("SELECT new com.pbg.tpvbackend.dto.statistics.StatisticsTotalsDto(DATE_FORMAT(o.closedAt, '%Y/%m/%d'), round(SUM(o.total), 2)) "
		 + "FROM orders as o "
		 + "LEFT JOIN o.zone z "
		 + "LEFT JOIN z.restaurant r "
		 + "LEFT JOIN r.chainRestaurant rc "
		 + "WHERE rc = :restaurantChain "
		 + "GROUP BY DATE_FORMAT(o.closedAt, '%Y/%m/%d') "
		 + "ORDER BY DATE_FORMAT(o.closedAt, '%Y/%m/%d') ASC")
	List<StatisticsTotalsDto> totalEarningsByRestaurantChainByDay(@Param("restaurantChain") RestaurantChain restaurantChain);

	@Query("SELECT new com.pbg.tpvbackend.dto.statistics.StatisticsTotalsDto(DATE_FORMAT(o.closedAt, '%Y/%m/%d'), round(COUNT(o), 2)) "
		 + "FROM orders as o "
		 + "LEFT JOIN o.zone z "
		 + "LEFT JOIN z.restaurant r "
		 + "LEFT JOIN r.chainRestaurant rc "
		 + "WHERE rc = :restaurantChain "
		 + "AND o.status = 'CLOSED' "
		 + "GROUP BY DATE_FORMAT(o.closedAt, '%Y/%m/%d') "
		 + "ORDER BY DATE_FORMAT(o.closedAt, '%Y/%m/%d') ASC")
	List<StatisticsTotalsDto> totalOrdersByRestaurantChainByDay(@Param("restaurantChain") RestaurantChain restaurantChain);
	
}
