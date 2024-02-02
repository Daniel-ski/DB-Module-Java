package orm;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManager<E> implements DBContext<E>{
    private static final String INSERT_QUERY = "INSERT INTO %s (%s) VALUES (%s);" ;
    private static final String SELECT_QUERY = "SELECT * FROM %s %s LIMIT 1;" ;
    private static final String CREATE_QUERY = "CREATE TABLE %s (id INT PRIMARY KEY AUTO_INCREMENT , %s );";

    private Connection connection ;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity.getClass());
        String fieldsWithoutId = getFields(entity);
        String valueList = getInsertValues(entity);


        return this.connection.prepareStatement(String.format(INSERT_QUERY,tableName,fieldsWithoutId,valueList)).execute();
    }

    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find(table,null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(table);

        ResultSet resultSet = connection.prepareStatement(
                String.format(SELECT_QUERY, tableName, where == null ? "" : "WHERE " + where)).executeQuery();

        List<E> result = new ArrayList<>();
        E entity = this.createEntity(table,resultSet);

        while (entity != null){
            result.add(entity);
            entity = this.createEntity(table,resultSet);
        }
        return result;
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(table,null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(table);

        ResultSet resultSet = connection.prepareStatement(
                String.format(SELECT_QUERY, tableName, where == null ? "" : "WHERE " + where)).executeQuery();


        return this.createEntity(table,resultSet);
    }

    

    private Map<String,String> getAllFieldsAndDataTypes(Class<E> entity) {
        Field[] fields = Arrays.stream(entity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Id.class))
                .toArray(Field[]::new);

        Map<String,String> fieldsAndTypes = new HashMap<>();

        Arrays.stream(fields).forEach(field -> fieldsAndTypes.put(getSQLColumnName(field),getSQLType(field.getType())));

        return fieldsAndTypes;
    }

    private String getSQLColumnName(Field field){
        return field.getAnnotationsByType(Column.class)[0].name();
    }

    private String getSQLType(Class<?> type) {
        if (type == long.class || type == Long.class || type == int.class || type == Integer.class) {
            return "INT";
        } else if (type == LocalDate.class) {
            return "DATE";
        }
            return "VARCHAR(60)";
    }

        private String getTableName (Class < ? > clazz){
            Entity annotation = clazz.getAnnotation(Entity.class);

            if (annotation == null) {
                throw new UnsupportedOperationException("Entity must have Entity annotation");
            }
            return annotation.name();
        }

        private String getFields (E entity){
            return Arrays.stream(entity.getClass().getDeclaredFields())
                    .filter(field -> field.getAnnotation(Column.class) != null)
                    .map(field -> field.getAnnotation(Column.class).name())
                    .collect(Collectors.joining(","));
        }

        private String getInsertValues (E entity) throws IllegalAccessException {
            Field[] declaredFields = entity.getClass().getDeclaredFields();
            ArrayList<String> result = new ArrayList<>();

            for (Field declaredField : declaredFields) {
                if (declaredField.getAnnotation(Column.class) == null) {
                    continue;
                }
                declaredField.setAccessible(true);
                Object value = declaredField.get(entity);
                result.add(value.toString());
            }
            return String.join(",", result);
        }

        private E createEntity (Class < E > table, ResultSet resultSet) throws
        SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            if (!resultSet.next()) {
                return null;
            }
            E entity = table.getDeclaredConstructor().newInstance();

            Field[] fields = table.getDeclaredFields();

            for (Field field : fields) {
                if (!field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Id.class)) {
                    continue;
                }

                Column columnAnnotation = field.getAnnotation(Column.class);

                String fieldName = columnAnnotation == null ? field.getName() : columnAnnotation.name();

                String value = resultSet.getString(fieldName);

                entity = fillData(entity, field, value);
            }

            return entity;
        }

        private E fillData (E entity, Field field, String value) throws IllegalAccessException {
            field.setAccessible(true);

            if (field.getType() == long.class || field.getType() == Long.class) {
                field.setLong(entity, Long.parseLong(value));
            } else if (field.getType() == LocalDate.class) {
                field.set(entity, value);
            } else if (field.getType() == String.class) {
                field.set(entity, value);
            } else if (field.getType() == int.class || field.getType() == Integer.class) {
                field.setInt(entity, Integer.parseInt(value));
            } else {
                throw new UnsupportedOperationException("Unsupported type " + field.getType());
            }

            return entity;
        }

}
