package com.github.wenhao.ddd.presentation.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.Orders;
import com.github.wenhao.ddd.presentation.helper.OrderTestHelper;
import com.github.wenhao.ddd.presentation.mapper.OrderMapper;
import com.github.wenhao.ddd.presentation.request.OrderCreateRequest;
import com.github.wenhao.ddd.presentation.response.OrderResponse;

@ExtendWith(MockitoExtension.class)
class OrdersApiTest {

    @InjectMocks
    private OrdersApi ordersApi;
    @Spy
    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    @Mock
    private Orders orders;

    @Test
    void should_be_able_to_create_order() {
        // given
        final OrderCreateRequest request = OrderTestHelper.getOrderCreateRequest();
        Order order = OrderTestHelper.getOrder();

        OrderResponse orderResponse = OrderTestHelper.getOrderResponse();

        // when
        when(orders.create(argThat(it -> it.getCustomerId().equals(101L)))).thenReturn(202L);
        when(orders.getById(202L)).thenReturn(order);
        ResponseEntity<OrderResponse> response = ordersApi.create(request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualToComparingFieldByFieldRecursively(orderResponse);
        assertThat(response.getBody()).usingRecursiveComparison()
                .isEqualTo(orderResponse);
    }
}
