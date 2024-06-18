create database chatForum;
use chatForum;



create table user(
    id INTEGER primary key AUTO_INCREMENT,
    username varchar(200) not null ,
    nickname varchar(200),
    password varchar(50) not null ,
    avatar varchar(500),
    signature varchar(400) default '这个人还没设置个性签名',
    type varchar(300) default 'student',
    level INTEGER default 1,
    address varchar(50) not null ,
    token VARCHAR(36) not null ,
    likes INTEGER default 0,    #获赞数
    collect INTEGER default 0,  #被收藏数
    forum_number INTEGER default 0,   #发表的文章数
    homeLink varchar(50) default '/userHome?id='
);


create table sort(
    id INTEGER primary key AUTO_INCREMENT,
    uid INTEGER not null ,
    sortName varchar(100) not null ,
    intor varchar(200),
    isLook boolean default false,
    create_time datetime default current_timestamp,
    update_time datetime,
    foreign key (uid) references user(id)
);



create table forum(
    id INTEGER primary key AUTO_INCREMENT,
    uid INTEGER not null ,
    create_time datetime default current_timestamp,
    update_time datetime,
    title varchar(50) not null ,
    content text not null ,
    intor varchar(200) default '没有简介' ,
    img varchar(500),
    sortId INTEGER not null ,
    likes INTEGER default 0,
    collect INTEGER default 0,
    foreign key (sortId) references sort(id)
);


create table forum_collect(
    id INTEGER primary key AUTO_INCREMENT,
    forum_id INTEGER not null ,
    user_id INTEGER not null ,
    create_time datetime default current_timestamp,
    update_time datetime,
    foreign key (forum_id) references forum(id),
    foreign key (user_id) references user(id)
);



create table forum_like(
    id INTEGER primary key AUTO_INCREMENT,
    forum_id INTEGER not null ,
    user_id INTEGER not null ,
    create_time datetime default current_timestamp,
    update_time datetime,
    foreign key (forum_id) references forum(id),
    foreign key (user_id) references user(id)
);






create table UserMessage(
    id INTEGER primary key AUTO_INCREMENT,
    uid INTEGER not null ,
    receiver_id INTEGER not null ,
    content text not null ,
    send_time datetime default current_timestamp,
    foreign key (uid) references user(id),
    foreign key (receiver_id) references user(id)
);


create table friendsList(
    id INTEGER primary key AUTO_INCREMENT,
    uid INTEGER not null ,
    friend_id INTEGER not null ,
    appellation varchar(200),
    create_time datetime default current_timestamp,
    foreign key (uid) references user(id),
    foreign key (friend_id) references user(id)
);


create table comment(
    id INTEGER primary key AUTO_INCREMENT,
    forum_id INTEGER not null ,
    uid INTEGER not null ,
    parent_id INTEGER,
    content text,
    content_img varchar(500),
    create_time datetime default current_timestamp,
    likes INTEGER default 0,
    foreign key (uid) references user(id),
    foreign key (forum_id) references forum(id)
);



create table comment_like(
    id INTEGER primary key AUTO_INCREMENT,
    uid INTEGER,
    comment_id INTEGER,
    sent_time  datetime default current_timestamp,
    update_time datetime,
    foreign key (uid) references user(id),
    foreign key (comment_id) references comment(id)
);


create table address(
    id INTEGER primary key AUTO_INCREMENT,
    name varchar(40) not null ,
    create_time datetime default current_timestamp,
    intor varchar(300),   #简介
    address_img varchar(500),
    content varchar(2000)  #内容
);


CREATE TRIGGER insert_into_user
    BEFORE INSERT ON user
    FOR EACH ROW
BEGIN
    set new.token =UUID();
    SET new.homeLink = CONCAT('/userHome?id=',NEW.id);
END;





CREATE TRIGGER update_likes_after_insert
    AFTER INSERT ON forum_like
    FOR EACH ROW
BEGIN
    UPDATE forum
    SET likes = likes + 1
    WHERE id = NEW.forum_id;
END;


CREATE TRIGGER update_collect_after_insert
    AFTER INSERT ON forum_collect
    FOR EACH ROW
BEGIN
    UPDATE forum
    SET collect = collect + 1
    WHERE id = NEW.forum_id;
END;


CREATE TRIGGER update_likes_after_insert_comment_like
    AFTER INSERT ON comment_like
    FOR EACH ROW
BEGIN
    UPDATE comment
    SET likes = likes + 1
    WHERE id = NEW.comment_id;
END;



CREATE TRIGGER updateUserLikes1
    AFTER INSERT ON forum_like
    FOR EACH ROW
BEGIN
    UPDATE user
    SET likes = likes + 1
    WHERE id = NEW.forum_id;
END;



CREATE TRIGGER updateUserLikes2
    AFTER INSERT ON comment_like
    FOR EACH ROW
BEGIN
    UPDATE user
    SET likes = likes + 1
    WHERE id = NEW.comment_id;
END;



CREATE TRIGGER updateUserCollect
    AFTER INSERT ON forum_collect
    FOR EACH ROW
BEGIN
    UPDATE user
    SET collect = collect + 1
    WHERE id = NEW.forum_id;
END;






CREATE TRIGGER deleteByforum
    after delete ON forum
    FOR EACH ROW
BEGIN
    delete from forum_like where forum_id=OLD.id;
    delete from forum_collect where forum_id=OLD.id;
    delete from comment where forum_id=OLD.id;
END;


create trigger deleteBycomment
    after delete on comment
    for each row
begin
    delete from comment_like where comment_id=OLD.id;
end;






insert into user(username, nickname, password, avatar, signature, type, level, address,token)
    values ('xiaolei','小雷','koko','src/main/webapp/userPictures/1.jpg','不是一个人的王者','user','2','信息工程学院',UUID());
insert into user(id,username, nickname, password, avatar, signature, type, level, address,token)
    values (2,'nange','神','ioio','src/main/webapp/userPictures/2.jpg','学习永无止境','user','5','车辆工程学院',UUID());
insert into user(id,username, nickname, password, avatar, signature, type, level, address,token)
    values (3,'xiaoliang','在远方','popo','src/main/webapp/userPictures/3.jpg','好无聊啊,呜呜呜','admin','7','土木工程学院',UUID());
insert into user(id,username, nickname, password, avatar, signature, type, level, address,token)
    values (5,'xiaogao','你干嘛','toto','src/main/webapp/userPictures/4.png','好开心啊','admin','3','土木工程学院',UUID());
insert into user(id,username, nickname, password, avatar, signature, type, level, address,token)
    values (9,'xhpy','小吃货','toto','src/main/webapp/userPictures/5.png','天天开心','user','3','土木工程学院',UUID());




insert into address(name, intor, address_img, content)
       values('信息工程学院','信息工程学院的简介','src/main/webapp/addressPictures/许昌学院.jpg','信息工程学院的内容');
insert into address(name, intor, address_img, content)
       values('土木工程学院','土木工程学院的简介','src/main/webapp/addressPictures/郑州大学.jpg','土木工程学院的内容');
insert into address(name, intor, address_img, content)
       values('车辆工程学院','车辆工程学院的简介','src/main/webapp/addressPictures/浙江大学.jpg','车辆工程学院的内容');
insert into address(name, intor, address_img, content)
       values('食品科学与工程学院','食品科学与工程学院的简介','src/main/webapp/addressPictures/西安电子科技大学.jpg','食品科学与工程学院的内容');



insert into sort(sortName, intor, isLook,update_time, uid)
    values('拉力赛车','这是拉力赛车',true,'2023-10-23 18:50:05',9);
insert into sort(sortName, intor, isLook,update_time, uid)
    values('王者荣耀','这是王者荣耀',true,'2023-10-23 18:50:05',9);
insert into sort(sortName, intor, isLook,update_time, uid)
    values('宿舍','这是宿舍',false,'2023-10-23 18:50:05',9);
insert into sort(sortName, intor, isLook,update_time, uid)
    values('演讲','这是演讲',true,'2023-10-23 18:50:05',9);
insert into sort(sortName, intor, isLook,update_time, uid)
    values('通知','这是通知',false,'2023-10-23 18:50:05',9);



insert into forum(id,uid,title,content, intor, img, sortId, likes, collect,update_time)
       values (1,1,'拉力赛车','怎么我看的好多拉力赛里都是现代跟丰田，其他车厂没参加吗？','关于拉力赛车的疑问','src/main/webapp/forumPictures/1.jpg',1,9,5,NOW());
insert into forum(id,uid,title,content, intor, img, sortId, likes, collect,update_time)
       values (2,2,'王者荣耀','ELO机制不用我说了，简单来说就是强行控制胜率的游戏，不得不说想让你输，系统有的是办法，刚刚在打第五把典韦明显被恶到了第一就是匹配实力不对等的队友，队友都是人坤一样，第二就是团战制造460，这种最恶心，就是想尽办法让你输。','教大家如何单排保持高胜率低场次高评分，避免成为积分...','src/main/webapp/forumPictures/2.jpg',2,55,8,NOW());
insert into forum(id,uid,title,content, intor, img, sortId, likes, collect,update_time)
       values (3,3,'宿舍晒照比赛','大家快来晒出你们的漂亮宿舍照片吧！我们将挑选最美的宿舍进行评选哦！','宿舍晒照,有奖拿哦','src/main/webapp/forumPictures/3.jpg',3,45,99,NOW());
insert into forum(id,uid,title,content, intor, img, sortId, likes, collect,update_time)
       values (4,5,'演讲比赛','我校举办校园演讲比赛，现在开始接受报名了！有兴趣展示自己演讲才能的同学可以前往学生事务处办理报名手续','校园演讲他来喽','src/main/webapp/forumPictures/4.jpg',4,1030,103,NOW());
insert into forum(id,uid,title,content, intor, img, sortId, likes, collect,update_time)
       values (5,9,'图书馆闭馆调整','由于学校活动需求，图书馆闭馆时间自即日起调整为每周五晚上8点。请同学们合理安排学习时间，多利用图书馆资源','关于图书馆闭馆时间调整的通知','src/main/webapp/forumPictures/5.jpg',5,23,5,NOW());





insert into UserMessage(uid, receiver_id, content)
    value (1,3,'明天8点上课,别迟到了');
insert into UserMessage(uid, receiver_id, content)
    value (3,5,'在干嘛,上号打游戏');
insert into UserMessage(uid, receiver_id, content)
    value (5,9,'出来玩吗');




insert into friendsList(uid, friend_id, appellation)
    values (1,3,'小亮');
insert into friendsList(uid, friend_id, appellation)
    values (1,5,'小高');
insert into friendsList(uid, friend_id, appellation)
    values (3,1,'小北');
insert into friendsList(uid, friend_id, appellation)
    values (5,1,'小北');
insert into friendsList(uid, friend_id, appellation)
    values (3,5,'小高');
insert into friendsList(uid, friend_id, appellation)
    values (5,3,'小亮');
insert into friendsList(uid, friend_id, appellation)
    values (5,9,'小高');
insert into friendsList(uid, friend_id, appellation)
    values (9,5,'xhpy');




insert into forum_like(forum_id, user_id,update_time)
    values (1,2,NOW());
insert into forum_like(forum_id, user_id,update_time)
    values (2,3,NOW());
insert into forum_like(forum_id, user_id,update_time)
    values (3,5,NOW());
insert into forum_like(forum_id, user_id,update_time)
    values (3,9,NOW());
insert into forum_like(forum_id, user_id,update_time)
    values (4,9,NOW());


insert into forum_collect(forum_id, user_id,update_time)
values (1,2,NOW());
insert into forum_collect(forum_id, user_id,update_time)
values (1,3,NOW());
insert into forum_collect(forum_id, user_id,update_time)
values (2,5,NOW());
insert into forum_collect(forum_id, user_id,update_time)
values (5,9,NOW());


insert into comment(forum_id, uid,content)
    values (1,2,'法国的标致和雪铁龙都有参与拉力赛');
insert into comment(forum_id, uid,parent_id,content,create_time)
    values (1,3,1,'同时日本的三菱和斯巴鲁等也都有参与拉力赛','2023-10-25 09:25:36');
insert into comment(forum_id, uid,content)
    values (2,3,'我觉得队友的配合和实力也很重要');
insert into comment(forum_id, uid,content)
    values (4,5,'是时候展示我演讲的实力了');


insert into comment_like(uid, comment_id,update_time)
    values (9,1,NOW());
insert into comment_like(uid, comment_id,update_time)
    values (9,4,NOW());
insert into comment_like(uid, comment_id,update_time)
    values (9,2,NOW());
insert into comment_like(uid, comment_id,update_time)
    values (9,3,NOW());



