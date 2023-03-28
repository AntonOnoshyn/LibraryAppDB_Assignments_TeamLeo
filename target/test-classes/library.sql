##US01
SELECT count(id) FROM users;

SELECT COUNT(DISTINCT id) FROM users;

SELECT *from users;

##US02
SELECT count(*)  from book_borrow
where is_returned = 0;

##US05
SELECT bc.name, count(*) FROM book_borrow bb
join books b on bb.book_id = b.id
join book_categories bc on b.book_category_id = bc.id
group by name
order by 2 desc;

