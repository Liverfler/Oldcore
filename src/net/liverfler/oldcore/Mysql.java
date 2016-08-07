package net.liverfler.oldcore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class Mysql {
	
	static Connection c = null;	

	public static String mysqlLoadString(String pname, String path) {
		Statement statement;
		try {
			statement = Main.c.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = statement
					.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '"
							+ pname + "';");
			if (res.next()) {
				String s = res.getString(path);
				statement.close();
				return s;
			} else {
				statement.close();
				return null;			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Integer mysqlLoadInt(String pname, String path) {
		Statement statement;
		try {
			statement = Main.c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet res = statement.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '"+ pname + "';");
			if (res.next()) {
				Integer i = res.getInt(path);
				statement.close();
				return i;
			} else {
				statement.close();
				return null;			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Integer a = -1;
			return a;
		}

	}

	public static boolean mysqlSaveInt(String pname, String path, int value) {

		try {
			Statement statement = Main.c
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			ResultSet res = statement
					.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '"
							+ pname + "';");
			
			
			res.next();
			res.updateInt(path, value);
			res.updateRow();
				statement.close();
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public static boolean mysqlSaveString(String pname, String path, String value) {
		Statement statement;
		try {
			statement = Main.c.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = statement
					.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '"
							+ pname + "';");
			if (res.next()) {
				res.updateString(path, value);
				res.updateRow();
				statement.close();
				return true;
			} else {
				res.updateString(path, value);
				res.updateRow();
				statement.close();
			return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
		
	public static void mysqlLoader(String pname) {
		try {
			Statement statement = Main.c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet res = statement.executeQuery("SELECT * FROM playerdata WHERE PlayerName = '"	+ pname + "';");
			
			if (res.next()) {
				final int tp = res.getInt("tp");
				final int szint = res.getInt("szint");
				final int weektp = res.getInt("weektp");
				final int warn = res.getInt("warn");
				final int ora = res.getInt("ora");
				final int perc = res.getInt("perc");

				Main.tp.put(pname, tp);
				Main.szint.put(pname, szint);
				Main.weektp.put(pname, weektp);
				Main.warn.put(pname, warn);
				Main.ora.put(pname, ora);
				Main.perc.put(pname, perc);

				statement.close();
			}else{
				statement.executeUpdate("INSERT INTO playerdata (`PlayerName`) VALUES ('"+ pname + "');");
				statement.close();
				Main.tp.put(pname, 0);
				Main.szint.put(pname, 1);
				Main.weektp.put(pname, 0);
				Main.warn.put(pname, 0);
				Main.ora.put(pname, 0);
				Main.perc.put(pname, 0);
				
				
				
				
				
				
				
				
			}
		} catch (SQLException e) {
			Main.log.log(Level.SEVERE,
					"Mysql hiba a játékosok adatainak újratöltésekor (reload)!");
			e.printStackTrace();
		}	
	}
}
