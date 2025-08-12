package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")  // XML ��δ� ���� ��ġ�� �°� ����
public class OrderSpringTest {

    @Autowired
    @Qualifier("shoppingCart")
    private ShoppingCart shoppingCart;

    @Autowired
    private OrderService orderService;

    @Test
    public void testShoppingCartBean() {
    	assertNotNull(orderService, "OrderService가 null이면 안됩니다.");
        assertEquals(2, shoppingCart.getProducts().size());
        assertEquals("노트북", shoppingCart.getProducts().get(0).getName());
        assertEquals("스마트폰", shoppingCart.getProducts().get(1).getName());
    }

    @Test
    public void testOrderServiceBean() {
    	assertNotNull(orderService, "OrderService Bean이 주입되지 않았습니다.");
        assertEquals(950000, orderService.calculateOrderTotal(), 0.01);
    }
}
