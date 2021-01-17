INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('kri',
'$2a$10$oikfEKnw0ddgOzXe/qMRCeaKiLyqAL.MMbWtdUt3jqQfCeAX7K8ge',
'ROLE_USER', 1);
 
INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('admin',
'$2a$10$alB6pdYy6UFSqNOW75s1uu6wv3iPchpbjRo4gUb5pypf2st8KlbZe',
'ROLE_ADMIN', 1);

INSERT INTO `products`
(`uname`,
`id`,
`name`,
`brand`,
`madein`,
`price`)
VALUES
('admin',
1,
'pen',
'reynolds' ,
'india' ,
2 );