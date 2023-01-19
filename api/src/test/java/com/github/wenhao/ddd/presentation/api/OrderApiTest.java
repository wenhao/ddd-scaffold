package com.github.wenhao.ddd.presentation.api;

import java.util.List;
import java.util.Optional;

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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.Orders;
import com.github.wenhao.ddd.presentation.helper.OrderTestHelper;
import com.github.wenhao.ddd.presentation.mapper.CommentMapper;
import com.github.wenhao.ddd.presentation.mapper.OrderMapper;
import com.github.wenhao.ddd.presentation.request.CommentCreateRequest;
import com.github.wenhao.ddd.presentation.response.CommentResponse;
import com.github.wenhao.ddd.presentation.response.OrderResponse;
import com.google.common.collect.ImmutableList;

@ExtendWith(MockitoExtension.class)
class OrderApiTest {

    @InjectMocks
    private OrderApi orderApi;
    @Spy
    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    @Spy
    private CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);
    @Mock
    private Orders orders;

    @Test
    void should_be_able_to_find_order_by_id() {
        // given
        long orderId = 101L;

        // when
        when(orders.findById(orderId)).thenReturn(Optional.of(OrderTestHelper.getOrder()));
        ResponseEntity<OrderResponse> response = orderApi.findById(orderId);

        // then
        assertThat(response.getBody()).isEqualToComparingFieldByFieldRecursively(OrderTestHelper.getOrderResponse());
    }

    @Test
    void should_be_able_to_cancel_order() {
        // given
        long id = 202L;

        // when
        doNothing().when(orders).cancel(id);
        ResponseEntity<Void> response = orderApi.cancel(id);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void should_be_able_to_pay_order() {
        // given
        final Long id = 202L;
        String payType = "WEI_CHAT";

        // when
        doNothing().when(orders).pay(id, payType);
        ResponseEntity<Void> response = orderApi.pay(id, payType);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void should_be_able_to_create_order_comments() {
        // given
        long id = 202L;
        final CommentCreateRequest request = OrderTestHelper.getCommentCreateRequest();
        Order.Comments mockedComments = mock(Order.Comments.class);

        // when
        doNothing().when(mockedComments).create(argThat(it -> it.getOrderId().equals(202L)));
        when(orders.of(id)).thenReturn(OrderTestHelper.getOrderWithComments(mockedComments));
        ResponseEntity<Void> response = orderApi.createComment(id, request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void should_be_able_to_get_order_comments() {
        // given
        final Long id = 202L;
        final Order.Comments mockedComments = mock(Order.Comments.class);

        // when
        when(mockedComments.findByIdentity(id)).thenReturn(ImmutableList.of(OrderTestHelper.getOrderComment()));
        when(orders.of(id)).thenReturn(OrderTestHelper.getOrderWithComments(mockedComments));
        ResponseEntity<List<CommentResponse>> response = orderApi.comments(id);

        // then
        assertThat(response.getBody().get(0)).usingRecursiveComparison()
                .isEqualTo(OrderTestHelper.getCommentResponse());
    }
}
