package mylab.book.control;

import mylab.book.entity.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Publication> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Publication item) {
        items.add(item);
        System.out.println(item.getTitle() + "이(가) 장바구니에 추가되었습니다.");
    }

    public boolean removeItem(String title) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().equals(title)) {
                Publication removed = items.remove(i);
                System.out.println(removed.getTitle() + "이(가) 장바구니에서 제거되었습니다.");
                return true;
            }
        }
        System.out.println("해당 제목의 출판물을 찾을 수 없습니다.");
        return false;
    }

    public void displayCart() {
        DecimalFormat money = new DecimalFormat("#,###");
        System.out.println("====== 장바구니 내용 ======");
        for (int i = 0; i < items.size(); i++) {
            Publication p = items.get(i);
            System.out.println((i + 1) + ". " + p.getTitle() + " - " + money.format(p.getPrice()) + "원");
        }
        int total = calculateTotalPrice();
        int discounted = calculateDiscountedPrice();
        System.out.println("총 가격: " + money.format(total) + "원");
        System.out.println("할인 적용 가격: " + money.format(discounted) + "원");
    }

    public int calculateTotalPrice() {
        int total = 0;
        for (Publication p : items) total += p.getPrice();
        return total;
    }

    public int calculateDiscountedPrice() {
        int total = 0;
        for (Publication item : items) {
            if (item instanceof Magazine) {
                total += (int)Math.round(item.getPrice() * 0.9);   // 10% 할인
            } else if (item instanceof Novel) {
                total += (int)Math.round(item.getPrice() * 0.85);  // 15% 할인
            } else if (item instanceof ReferenceBook) {
                total += (int)Math.round(item.getPrice() * 0.8);   // 20% 할인
            } else {
                total += item.getPrice();
            }
        }
        return total;
    }

    public void printStatistics() {
        int magazine = 0, novel = 0, ref = 0;
        for (Publication item : items) {
            if (item instanceof Magazine) magazine++;
            else if (item instanceof Novel) novel++;
            else if (item instanceof ReferenceBook) ref++;
        }
        System.out.println("====== 장바구니 통계 ======");
        System.out.println("잡지: " + magazine + "권");
        System.out.println("소설: " + novel + "권");
        System.out.println("참고서: " + ref + "권");
        System.out.println("총 출판물: " + items.size() + "권");
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Magazine("마이크로소프트", "2007-10-01", 328, 9900, "매월"));
        cart.addItem(new Magazine("경영과컴퓨터", "2007-10-03", 316, 9000, "매월"));
        cart.addItem(new Novel("빠삐용", "2007-07-01", 396, 9800, "베르나르베르베르", "현대소설"));
        cart.addItem(new Novel("남한산성", "2007-04-14", 383, 11000, "김훈", "대하소설"));
        cart.addItem(new ReferenceBook("실용주의프로그래머", "2007-01-14", 496, 25000, "소프트웨어공학"));

        cart.displayCart();
        cart.printStatistics();

        cart.removeItem("빠삐용");
        cart.displayCart();
    }
}
