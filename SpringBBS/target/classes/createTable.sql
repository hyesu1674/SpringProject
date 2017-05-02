create table tbl_reply(
	rno int primary key auto_increment,
	bno int references tbl_board(bno),
	replyText varchar(1000) not null,
	replyer varchar(50) not null,
	regdate timestamp default now(),
	updateDate timestamp default now()
)