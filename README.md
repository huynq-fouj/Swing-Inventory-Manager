## Demo
### Sign in
<div align="center">
<img src="demo/login.png" width="200"/>
</div>

### Home
<div align="center">
<img src="demo/home.png" width="300"/>
</div>

### User List
<div align="center">
<img src="demo/users.png" width="300"/>
</div>

### Update User
<div align="center">
<img src="demo/updateUser.png" width="300"/>
</div>

### Personal Information
<div align="center">
<img src="demo/userAccount.png" width="300"/>
</div>

## Tech Stack
![](https://img.shields.io/badge/JAVA-00AFF0?style=for-the-badge&logo=openjdk)
<br>
![](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

## Get Start
- Restore database.
- Change MySQL connection in `Databases.ConnectionPoolImpl`:
```java
public ConnectionPoolImpl() {
		this.driver = "com.mysql.jdbc.Driver";
		this.url = "jdbc:mysql://localhost:3306/<your database's name>?allowMultiQueries=true";
		this.username = "your username";
		this.userpass = "your password";
		this.loadDriver();
		this.pool = new Stack<>();
}
```
- Run in `Views.MainView` .