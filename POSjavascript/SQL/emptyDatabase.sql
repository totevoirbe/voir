SET foreign_key_checks = 0;
delete from cashsale where id > 0;
delete from itemproduct where id > 0;
delete from paymentmethod where id > 0;
delete from product where id > 0;
delete from cashsale_itempayment where id > 0;
delete from cashsale_itemproduct where id > 0;
SET foreign_key_checks = 1;
