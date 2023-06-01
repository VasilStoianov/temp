INSERT IGNORE INTO application_user (id, name, password, user_type, username, last_connection_date)
values (1,
       'Petar Popov',
        'peterHPopov123',
        1, 'petarPopov', NOW());


INSERT IGNORE INTO application_user (id, name, password, user_type, username, last_connection_date)
values (2,
       'Ivan Ivanov',
        'ivanIvanov123',
        2, 'ivanIvanov', NOW());
