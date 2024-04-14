package Models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Databases.ConnectionPool;
import Models.Basic.BasicImpl;
import Models.Objects.UserObject;
import Utilities.Utilities;
import Utilities.Utilities_date;

public class UserImpl extends BasicImpl implements User {

	public UserImpl(ConnectionPool cp) {
		super(cp, "User");
	}

	@Override
	public ConnectionPool getCP() {
		return super.getCP();
	}

	@Override
	public void releaseConnection() {
		super.releaseConnection();
	}

	@Override
	public boolean addUser(UserObject item) {

		if (this.isExisting(item)) {
			return false;
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tbluser(");
		sql.append("user_name, user_password, user_fullname, user_email, user_phone, user_role, user_logined,");
		sql.append("user_created_at, user_modified_at, user_notes, user_address");
		sql.append(") VALUE(?,md5(?),?,?,?,?,?,?,?,?,?)");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getUser_name());
			pre.setString(2, item.getUser_password());
			pre.setString(3, Utilities.encode(item.getUser_fullname()));
			pre.setString(4, item.getUser_email());
			pre.setString(5, item.getUser_phone());
			pre.setInt(6, item.getUser_role());
			pre.setInt(7, item.getUser_logined());
			pre.setString(8, Utilities_date.getDate());
			pre.setString(9, Utilities_date.getDate());
			pre.setString(10, Utilities.encode(item.getUser_notes()));
			pre.setString(11, Utilities.encode(item.getUser_address()));
			return this.add(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	public boolean isExisting(UserObject item) {
		boolean flag = false;
		String sql = "SELECT user_id FROM tbluser WHERE user_name='" + item.getUser_name() + "'";
		ResultSet rs = this.gets(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean editUser(UserObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tbluser SET ");
		sql.append("user_fullname=?, user_email=?, user_phone=?, ");
		sql.append("user_modified_at=?, user_notes=?, user_address=? ");
		sql.append("WHERE user_id=? ");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, Utilities.encode(item.getUser_fullname()));
			pre.setString(2, item.getUser_email());
			pre.setString(3, item.getUser_phone());
			pre.setString(4, Utilities_date.getDate());
			pre.setString(5, Utilities.encode(item.getUser_notes()));
			pre.setString(6, Utilities.encode(item.getUser_address()));
			pre.setInt(7, item.getUser_id());
			return this.edit(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delUser(UserObject item) {
		String sql = "DELETE FROM tbluser WHERE user_id=? ";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getUser_id());
			return this.del(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ResultSet getUser(int id) {
		String sql = "SELECT * FROM tbluser WHERE (user_id=?);";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getUser(String username, String userpass) {
		ArrayList<String> sql = new ArrayList<>();
		String sqlSelect = "SELECT * FROM tbluser WHERE (user_name = ?) AND (user_password = md5(?));";
		String sqlUpdate = "UPDATE tbluser SET user_logined = user_logined + 1 WHERE (user_name=?) AND (user_password = md5(?));";
		sql.add(sqlSelect);
		sql.add(sqlUpdate);
		return this.get(sql, username, userpass);
	}
	
	private String createConditions(UserObject similar) {
		StringBuilder conds = new StringBuilder();
		if(similar != null) {
			String key = similar.getUser_name();
			if(key != null && !key.equalsIgnoreCase("")) {
				key = Utilities.encode(key);
				conds.append(" (user_name LIKE '%"+key+"%') OR ");
				conds.append(" (user_fullname LIKE '%"+key+"%') OR ");
				conds.append(" (user_email LIKE '%"+key+"%') ");
			}
		}
		
		if(!conds.toString().equalsIgnoreCase("")) {
			conds.insert(0, " WHERE ");
		}
		
		return conds.toString();
	}

	@Override
	public ResultSet getUsers(UserObject similar) {
		String sql = "SELECT * FROM tbluser ";
		sql += this.createConditions(similar);
		sql += "ORDER BY user_id DESC;";
		return this.gets(sql);
	}
	
	@Override
	public ResultSet countUser() {
		String sql = "SELECT COUNT(*) AS total FROM tbluser;";
		return this.gets(sql);
	}

}
