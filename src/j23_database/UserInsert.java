package j23_database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import j20_JSON.builder.User;

public class UserInsert {

	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();

		User user = User.builder().username("bbb").password("1234").name("bbb").email("bbb@gmail.com").build();

		String sql = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try { // 예외처리
			connection = pool.getConnection();

			sql = "insert into user_mst\r\n" + "value (0, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getEmail());

			int successCount = preparedStatement.executeUpdate(); // insert, update, delete 명령 실행할 떄 사용한다.

			System.out.println("쿼리 실행 성공: " + successCount + "건");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
