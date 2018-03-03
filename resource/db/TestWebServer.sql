
#CREATE DATABASE IF NOT EXISTS MySqlDB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

#USE mysqldb;

CREATE TABLE IF NOT EXISTS player(
  playerId BIGINT UNSIGNED AUTO_INCREMENT  COMMENT '玩家id',
  name VARCHAR(10) NOT NULL ,
  createTime DATE,
  groupId INT NOT NULL,
  PRIMARY KEY(playerId)
)ENGINE = InnoDB DEFAULT CHARSET =utf8;

ALTER TABLE player AUTO_INCREMENT=100000;

CREATE TABLE IF NOT EXISTS group1(
  groupId INT UNIQUE NOT NULL AUTO_INCREMENT,
  language VARCHAR(10) NOT NULL ,
  PRIMARY KEY (groupId)
)ENGINE =InnoDB CHARSET =utf8;



DROP TABLE group1;

#DELETE FROM player WHERE playerId=1 AND playerId=2;

INSERT INTO player(playerId, name, createTime, groupId) VALUES (1000000,"aaaaaaaaaa",NOW(),1);
INSERT INTO player( name, createTime, groupId) VALUES ("bbb",NOW(),2);
INSERT INTO player( name, groupId) VALUES ("ccc",3);
INSERT INTO player( name, groupId) VALUES ("ddd",2);


INSERT INTO group1(groupId, language) VALUES (1,'Java');
INSERT INTO group1( language) VALUES ('C#');
INSERT INTO group1( language) VALUES ('Python');




#DELETE FROM player WHERE playerId=1000003 OR playerId=1000004;
INSERT INTO player( name, groupId) VALUES ("aaa222",2);



COMMIT;  ##每次执行select前提交事务。  简单测试：没严格按照事务过程测试

SELECT * FROM player;
SELECT * from player  WHERE player.name LIKE 'ccc%';  ##模糊查询

SELECT * FROM group1;

##连接   inner join   left Join  right join
#inner join
SELECT  p.playerId ,p.name,g.language
        from player p INNER JOIN group1 g ON p.groupId=g.groupId;

## lefJoin
INSERT INTO player( name, groupId) VALUES ("LeftJoin",4);

SELECT  p.playerId ,p.name,g.language
from player p LEFT JOIN group1 g ON p.groupId=g.groupId;

##rightJoin
UPDATE player SET player.groupId = 10 WHERE player.groupId = 4;
INSERT INTO group1( language) VALUES ('Unity');

SELECT  p.playerId ,p.name,g.language
from player p RIGHT JOIN group1 g ON p.groupId=g.groupId;




