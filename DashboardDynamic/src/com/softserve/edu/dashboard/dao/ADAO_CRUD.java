package com.softserve.edu.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.dashboard.db.ConnectionManager;
import com.softserve.edu.dashboard.entity.AEntity;
import com.softserve.edu.dashboard.services.SQLProperty;

public abstract class ADAO_CRUD<TEntity extends AEntity> implements IDAO_CRUD<TEntity> {

	private static Connection connection = ConnectionManager.getInstance().getConnection();

	private String queryPrefix;

	public ADAO_CRUD() {
		this.queryPrefix = getQueryPrefix();
		createTable();
//		dropTable();
	}

	public abstract String getQueryPrefix();

	public abstract TEntity createEntity(ResultSet resultSet) throws SQLException;
	
	public abstract List<Object> getEntityParams(String operation, TEntity entity);
	
	public static final String CREATE = "create";
	public static final String UPDATE = "update";

	
	@Override
	public void create(TEntity entity) throws SQLException {
		String query = SQLProperty.get(queryPrefix + ".insert");
		// System.out.println(query);
		try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			List<Object> entityParams = getEntityParams(ADAO_CRUD.CREATE, entity);
			// entityParams.forEach(System.out::println);
			for (int i = 1; i < entityParams.size(); i++) {
				preparedStatement.setObject(i, entityParams.get(i));
			}
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
//		} finally {
//			if (connection != null) {
//				connection.close();
//			}
		}
	}

	@Override
	public List<TEntity> findAll() throws SQLException {
		List<TEntity> entities = new ArrayList<>();
		String query = SQLProperty.get(queryPrefix + ".findAll");
		try (Statement st = ConnectionManager.getInstance().getConnection().createStatement()) {
			ResultSet resultSet = st.executeQuery(query);
			while (resultSet.next()) {
				entities.add(createEntity(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
//		} finally {
//			if (connection != null) {
//				connection.close();
//			}
		}
		return entities;
	}

	@Override
	public List<TEntity> findByField(String field, Object o) throws SQLException {
		List<TEntity> entities = new ArrayList<>();
		String query = SQLProperty.get(queryPrefix + ".findByField").replace("$field$", field);
		// System.out.println(query);
		try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			preparedStatement.setObject(1, o);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				entities.add(createEntity(resultSet));
			}
			// entities.forEach(System.out::println);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
//		} finally {
//			if (connection != null) {
//				connection.close();
//			}
		}
		return entities;
	}
	
	@Override
	public TEntity findById(Long id) throws SQLException {
		return findByField("id", id).get(0);
	}

	@Override
	public void update(TEntity entity) throws SQLException {
		String query = SQLProperty.get(queryPrefix + ".updateById");
		//UPDATE users SET name = ? (1) , password = ?(2) , email = ? (3)  WHERE id = ? (4)
		 System.out.println("update: " + query);
		try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			List<Object> entityParams = getEntityParams(ADAO_CRUD.UPDATE, entity);
			entityParams.forEach(System.out::println); // +
			for (int i = 1; i <= entityParams.size(); i++) {
				preparedStatement.setObject(i, entityParams.get(i-1));
				// System.out.println(entityParams.get(i));
			}
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
//		} finally {
//			if (connection != null) {
//				connection.close();
//			}
		}
	}
	
	@Override
	public void delete(TEntity entity) throws SQLException {
		deleteById(entity.getId());
	}
	
	@Override
	public void deleteById(Long id) throws SQLException {
		String query = SQLProperty.get(queryPrefix + ".deleteById");
		try (PreparedStatement preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			preparedStatement.setObject(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
//		} finally {
//			if (connection != null) {
//				connection.close();
//			}
		}
	}
	
	

	private void createTable(){
		String query = SQLProperty.get(queryPrefix + ".createTable");
//		System.out.println(query);
		try (Statement statement = ConnectionManager.getInstance().getConnection().createStatement()) {
			if ( statement.execute(query)) {
				System.out.println("Table " + queryPrefix + "s created");				
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Table " + queryPrefix + "s was not created");
		}
	}
	
//	private void dropTable() {
//		String query = "DROP TABLE " + queryPrefix + "s";
//		System.out.println(query);
//		try (Statement statement = ConnectionManager.getInstance().getConnection().createStatement()) {
//			statement.execute(query);
//			System.out.println("Table " + queryPrefix + "s deleted");
//		} catch (SQLException e) {
////			e.printStackTrace();
//			System.out.println("Failed to drop tabel " + queryPrefix + "s");
//		}
//	}

}
