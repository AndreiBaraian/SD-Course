package repository;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import repository.dbmodel.Admin;

public class AbstractRepo<T> {

	protected final Class<T> type;
	protected static final Logger LOGGER = Logger.getLogger(AbstractRepo.class.getName());
	
	@SuppressWarnings("unchecked")
	public AbstractRepo() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		sb.append("table");
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}
	
	public T findById(int id){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private String createInsertQuery(){
		int cntFields = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(type.getSimpleName());
		sb.append("table");
		sb.append(" (");
		for(Field field : type.getDeclaredFields()){
			field.setAccessible(true);
			sb.append(field.getName());
			sb.append(",");
			cntFields++;
		}
		sb.deleteCharAt(sb.length()-1); //delete the last ","
		sb.append(") VALUES (");
		for(int i = 0; i < cntFields; i++){
			if(i != cntFields-1)
				sb.append("?,");
			else
				sb.append("?)");
		}
		return sb.toString();
	}
	
	public int insert(T t) {
		int cntField = 0;
		Connection connection = null;
		PreparedStatement insertStatement = null;
		ResultSet resultSet = null;
		String insertStatementString = createInsertQuery();
		int insertedId = -1;
		try{
			connection = ConnectionFactory.getConnection();
			insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			for(Field field : t.getClass().getDeclaredFields()){
				cntField++;
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),type);
				Method method = propertyDescriptor.getReadMethod();
				Object value = method.invoke(t);
				insertStatement.setObject(cntField, value);
			}
			System.out.println(insertStatement.toString());
			insertStatement.executeUpdate();
			resultSet = insertStatement.getGeneratedKeys();
			if(resultSet.next()){
				insertedId = resultSet.getInt(1);
				System.out.println(insertedId);
			}
		
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(connection);
		}
		return insertedId;
	}
	
	private String createSelectQueryAll(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		sb.append("table");
		return sb.toString();
	}
	
	public List<T> findAll() {
		Connection connection = null;	
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQueryAll();
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			System.out.println(statement.toString());
			resultSet = statement.executeQuery();
			
			return createObjects(resultSet);
		} catch(SQLException e){
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally{
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	public T findByField(String field, String value) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery(field);
		try{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, value);
			resultSet = statement.executeQuery();
			List<T> objects = createObjects(resultSet);
			if(objects.isEmpty())
				return null;
			else
				return objects.get(0);
		} catch (SQLException e){
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findByField " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
}
