select distinct b.name, author,isbn,b.description,year,bc.name as "name_genre" from books b
inner join book_categories bc on b.book_category_id = bc.id
where b.name like '%Clean Code%'
order by isbn desc;

select name,author,isbn,description,year from books
where name ='Clean Code';


select * from book_categories;

select * from books;




