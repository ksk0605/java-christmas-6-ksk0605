# 📑 기능명세서

## ✏️ 프로젝트 개요

사용자로부터 식당 예상 방문 날짜와 주문 메뉴를 입력받아, 해당 날짜의 이벤트 혜택과 할인 정보를 계산하여 출력하는 우테코 식당 12월 이벤트 플래너.

### 메뉴

```java
<애피타이저>
양송이수프(6,000),타파스(5,500),시저샐러드(8,000)

<메인>
티본스테이크(55,000),바비큐립(54,000),해산물파스타(35,000),크리스마스파스타(25,000)

<디저트>
초코케이크(15,000),아이스크림(5,000)

<음료>
제로콜라(3,000),레드와인(60,000),샴페인(25,000)
```

### 목표

1. 중복된 할인과 증정을 허용해서, 고객들이 혜택을 많이 받는다는 것을 체감할 수 있게 하는 것
2. 올해 12월에 지난 5년 중 최고의 판매 금액을 달성
3. 12월 이벤트 참여 고객의 5%가 내년 1월 새해 이벤트에 재참여하는 것

### 할인 정책

- 크리스마스 디데이 할인
    - 이벤트 기간: 2023.12.1 ~ 2023.12.25
    - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
    - 총주문 금액에서 해당 금액만큼 할인(e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
- 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
- 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
- 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
- 증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
- 이벤트 기간: '크리스마스 디데이 할인'을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용

## 🗂️ 폴더 구조

```
├── Application.java
├── constants
│   └── EventConstants.java
├── controller
│   └── EventPlanner.java
├── domain
│   ├── VisitDate.java
│   ├── discount
│   │   ├── ChristmasDiscount.java
│   │   ├── Discount.java
│   │   ├── Discounts.java
│   │   ├── EventDiscount.java
│   │   ├── SpecialDiscount.java
│   │   ├── SpecialDiscountDay.java
│   │   ├── WeekdayDiscount.java
│   │   └── WeekendDiscount.java
│   ├── event
│   │   ├── EventBadge.java
│   │   └── EventItem.java
│   ├── menu
│   │   ├── MenuCategory.java
│   │   └── MenuItem.java
│   └── order
│       ├── Order.java
│       ├── OrderItem.java
│       └── OrderItems.java
├── exception
│   ├── ErrorMessage.java
│   ├── IllegalOrderInputException.java
│   └── IllegalVisitDateInputException.java
├── handler
│   └── InputHandler.java
├── util
│   └── WeekdayWeekendChecker.java
└── view
    ├── InputView.java
    ├── OutputView.java
    ├── StaticHeaderView.java
    └── ViewMessages.java
```

## 📦 객체 목록

### Controller

<table>
<thead>
  <tr>
    <th>Class Name</th>
    <th>Description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>EventPlanner</td>
    <td>전체 프로그램 진행을 관리하는 컨트롤러 클래스.</td>
  </tr>
</tbody>
</table>

### Domain

<table>
<thead>
  <tr>
    <th>Class Name</th>
    <th>Description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>VisitDate</td>
    <td>방문 날짜 관련 로직을 담당하는 클래스.</td>
  </tr>
  <tr>
    <td>MenuCategory</td>
    <td>메뉴의 카테고리 정보를 저장하는 enum 클래스.</td>
  </tr>
  <tr>
    <td>MenuItem</td>
    <td>메뉴별 가격과 이름 저장하고 관련 로직을 수행하는 enum 클래스.</td>
  </tr>
  <tr>
    <td>Order</td>
    <td>주문 정보를 제공하는 클래스.</td>
  </tr>
  <tr>
    <td>OrderItems</td>
    <td>저장된 주문 목록 관련 로직을 담당하는 클래스.</td>
  </tr>
  <tr>
    <td>OrderItem</td>
    <td>주문 메뉴와 주문 개수 관련 로직을 담당하는 클래스.</td>
  </tr>
  <tr>
    <td>EventBadge</td>
    <td>이벤트로 지급하는 배지의 로직을 담당하는 enum 클래스.</td>
  </tr>
  <tr>
    <td>EventItem</td>
    <td>이벤트로 증정하는 상품 관련 로직을 담당하는 enum 클래스.</td>
  </tr>
  <tr>
    <td>Discount</td>
    <td>혜택 관련 클래스들을 추상화하는 인터페이스</td>
  </tr>
  <tr>
    <td>Discounts</td>
    <td>혜택 관련 로직들을 묶어 공통으로 처리하는 로직을 담당하는 클래스.</td>
  </tr>
  <tr>
    <td>ChristmasDiscount</td>
    <td>크리스마스 디데이 할인 관련 로직을 담당하는 클래스.</td>
  </tr>
  <tr>
    <td>WeekdayDiscount </td>
    <td>평일 할인 관련 로직을 담당하는 클래스.</td>
  </tr>
  <tr>
    <td>WeekendDiscount</td>
    <td>주말 할인 관련 로직을 담당하는 클래스.</td>
  </tr>
  <tr>
    <td>SpecialDiscount</td>
    <td>특별 할인 관련 로직을 담당하는 클래스.</td>
  </tr>
  <tr>
    <td>SpecialDiscountDay</td>
    <td>특별 할인 날짜를 저장하고 관리하는 enum 클래스.</td>
  </tr>
  <tr>
    <td>EventDiscount</td>
    <td>증정 이벤트 관련 로직을 담당하는 클래스.</td>
  </tr>
</tbody>
</table>

### Exception

<table>
<thead>
  <tr>
    <th>Class Name </th>
    <th>Description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>ErrorMessage</td>
    <td>에러 메시지들을 저장하고 관리하는 상수 클래스.</td>
  </tr>
  <tr>
    <td>IllegalOrderInputException</td>
    <td>주문 입력 관련 예외를 발생시키는 클래스.</td>
  </tr>
  <tr>
    <td>IllegalVisitDateInputException</td>
    <td>방문 날짜 입력 관련 예외를 발생시키는 클래스.</td>
  </tr>
</tbody>
</table>

### Handler

<table>
<thead>
  <tr>
    <th>Class Name</th>
    <th>Description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>InputHandler</td>
    <td>사용자 입력시 발생하는 예외를 처리하는 로직을 담당하는 클래스.</td>
  </tr>
</tbody>
</table>

### Util

<table>
<thead>
  <tr>
    <th>Class Name</th>
    <th>Description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>WeekdayWeekendChecker</td>
    <td>주말/주일 여부를 확인하는 유틸 클래스.</td>
  </tr>
</tbody>
</table>

### View

<table>
<thead>
  <tr>
    <th>Class Name</th>
    <th>Description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>InputView</td>
    <td>사용자 입력을 담당하는 View 클래스.</td>
  </tr>
  <tr>
    <td>OutputView</td>
    <td>입력에 따른 결과 출력을 담당하는 View 클래스.</td>
  </tr>
  <tr>
    <td>StaticHeaderView</td>
    <td>고정된 헤더 메시지 출력을 담당하는 View 클래스.</td>
  </tr>
  <tr>
    <td>ViewMessages</td>
    <td>View 클래스들에 사용될 메시지들을 저장하고 관리하는 상수 클래스.</td>
  </tr>
</tbody>
</table>

## 🖇️ 기능 목록

- [x] 사용자로부터 식당 예상 방문 날짜와 주문 메뉴를 입력받는다.
    - [x] 식당 예상 방문 날짜를 입력받는다.
        - [x] 날짜는 하나의 정수만 입력할 수 있다.
        - [x] 날짜는 1이상 31 이하의 숫자만 입력할 수 있다.
    - [x] 주문 메뉴를 입력받는다.
        - [x] 메뉴판에 없는 메뉴는 주문할 수 없다.
        - [x] 메뉴의 개수는 1이상의 정수만 입력할 수 있다.
        - [x] 예시와 다른 메뉴 형식은 입력할 수 없다.
            - [x] 메뉴와 개수는 '-' 로 구분되어야 한다.
            - [x] 메뉴-개수 세트는 ',' 로 구분되어야 한다.
                - [x] 연속 콤마 입력은 불가능하다.
                - [x] 콤마로 시작하거나 끝나는 입력은 불가능하다.
        - [x] 메뉴는 중복할 수 없다.
        - [x] 음료만 주문할 수 없다.
        - [x] 메뉴는 한 번에 최대 20개까지만 주문할 수 있다.
        - [x] 메뉴는 공백을 입력할 수 없다.
    - [X]  잘못된 값 입력시 에러 메시지를 출력한다.
        - [X] 에러 메시지는 [ERROR] 로 시작한다.
- [x] 해당 날짜의 이벤트 혜택과 할인 정보를 계산한다.
    - [x] 총 주문 금액을 계산한다.
    - [x] 이벤트 혜택을 계산한다.
    - [x] 할인 정보를 계산한다.
        - [x] 크리스마스 디데이 할인 금액을 계산한다.
        - [x] 평일 할인 금액을 계산한다.
        - [x] 주말 할인 금액을 계산한다.
        - [x] 특별 할인 금액을 계산한다.
    - [x] 총주문 금액 10,000원 이상부터 이벤트가 적용된다.
    - [x] 총 혜택 금액을 계산한다.
        - [x] 총 혜택 금액에 따라 다른 이벤트 배지를 부여한다.
- [x] 계산 결과를 출력한다.
    - [x] 주문 메뉴를 출력한다.
    - [x] 할인 전 총주문 금액을 출력한다.
    - [x] 증정 메뉴를 출력한다.
        - [x] 증정 받은 메뉴가 없다면 "없음"을 출력한다.
    - [x] 혜택 내역을 출력한다.
        - [x] 혜택 내역이 없다면 "없음"을 출력한다.
    - [x] 총혜택 금액을 출력한다.
    - [x] 할인 후 예상 결제 금액을 출력한다.
    - [x] 지급받은 12월 이벤트 배지를 출력한다.
        - [x] 지급받은 배지가 없다면 "없음"을 출력한다.
