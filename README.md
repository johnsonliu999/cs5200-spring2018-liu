# CS 5200 spring project

## HOW TO TEST
1. use `db.sql` to create the schema and tables
2. configure the `url`, `username`, `password` in `/utils/ConnectDB.java`
2. run `main` function in `hw_jdbc.java` which is the of same level with the spring app file


* the orders in `hw_jdbc.java` are fixed to follow foreign key constraints, otherwise may require executing `set foreign_key_check=0`
* the test files in `test` directory require canceling foreign key check, or manually preload the needed data to satisfy foreign key constraint. However, they are not prerequisites to run `hw_jdbc.java`

## Integrating Spring Boot with MySQL
[Insert a static hello message](http://cs5200-spring2018-liu.us-east-2.elasticbeanstalk.com/api/hello/insert)  
[Insert a parameterized hello message](http://cs5200-spring2018-liu.us-east-2.elasticbeanstalk.com/api/hello/insert/Some%20parameterized%20message)  
[Retrieve all hello messages](http://cs5200-spring2018-liu.us-east-2.elasticbeanstalk.com/api/hello/select/all)

