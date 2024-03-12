# DummyProducts

<p>Приложение для поиска и просмотра моковых товаров на сайте https://dummyjson.com/products.
<br><br>
<img src="https://github.com/matttax/DummyProducts/assets/67387151/733dcc03-6604-4644-9157-25e578cc521b" alt="drawing" style="width:200px;"/>

Функционал
------------
<ul>
    <li>Просмотр списка всех товаров;</li>
    <li>Фильрация списка по категориям;</li>
    <li>Поиск товаров;</li>
    <li>Подгрузка новых товаров при скролле вниз (пагинация);</li>
    <li>Возобновление загрузки при подключении к сети (без действий со стороны пользователя);</li>	
    <li>Переход на страницу товара;</li>
    <li>Поддержка тёмной темы.</li>
</ul>

<p float="left">
  <img src="https://github.com/matttax/DummyProducts/assets/67387151/09f886f2-a340-4883-b744-1c9a63d64a1e" alt="drawing" style="width:200px;"/>
  <img src="https://github.com/matttax/DummyProducts/assets/67387151/2ae99088-ecd4-4b0f-90b7-073fd9dab071" alt="drawing" style="width:200px;"/>
  <img src="https://github.com/matttax/DummyProducts/assets/67387151/f7dc323a-f974-4ce4-aeb3-0b6be9ae12ef" alt="drawing" style="width:200px;"/>
  <img src="https://github.com/matttax/DummyProducts/assets/67387151/9ba4f9cb-301e-48aa-8852-77d5f5ee5a08" alt="drawing" style="width:200px;"/>
</p><br>

Стек
------------
<ul>
  <li>Compose</li>
  <li>Coil</li>
  <li>Accompanist pager*</li>
  <li>Paging library</li>
  <li>Compose Navigation</li>
  <li>Kotlin Flows</li>
  <li>Hilt</li>
  <li>Retrofit</li>
  <li>Gson</li>
 </ul>
 * - гугловская библиотека для создания пейджеров на compose (как на странице товара)

Архитектура
------------
Чистая слоистая архитектура: (data, domain и presentation).<br>
Юзкейсы не применяются, поскольку в рамках данного приложения были бы овердизайном.<br>
UI-паттерн: MVVM.

Демо
------------
Скачать приложение можно по ссылке: https://github.com/matttax/DummyProducts/blob/master/DummyProducts.apk

Разработчики
------------
Романов Максим: https://t.me/matttax

