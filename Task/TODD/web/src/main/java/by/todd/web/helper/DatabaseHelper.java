package by.todd.web.helper;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import by.todd.api.Status;
import by.todd.entity.Task;
import by.todd.entity.User;

/**
 * Created by sergey on 22.11.2014.
 */
public class DatabaseHelper {

    private static final int DEFAULT_LIMIT = 100;

    public static User getUser(String email) {
        Query.Filter filter =
                new Query.FilterPredicate(User.EMAIL,
                        Query.FilterOperator.EQUAL,
                        email);
        Query query = new Query(User.TABLE_NAME).setFilter(filter);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity entityUser = datastore.prepare(query).asSingleEntity();
        if (entityUser == null) {
            return null;
        }
        return new User(entityUser.getProperties());
    }

    public static void addUser(HttpServletResponse resp, User user) {
        if (user == null) {
            ResponseHelper.write(resp, Status.OK);
            return;
        }

        Entity entityUser = new Entity(User.TABLE_NAME);
        entityUser.setProperty(User.NAME, user.getName());
        entityUser.setProperty(User.NAME, user.getEmail());

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Transaction transaction = datastore.beginTransaction();
        try {
            datastore.put(entityUser);
            transaction.commit();
        } catch (Exception e) {
            ResponseHelper.write(resp, e.toString(), Status.INTERNAL_SERVER_ERROR);
            return;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
                ResponseHelper.write(resp, Status.INTERNAL_SERVER_ERROR);
                return;
            }
        }
        ResponseHelper.write(resp, Status.CREATED);
    }

    public static List<Task> getTasks(String owner, int offset, int limit) {
        FetchOptions fo;
        if (limit > 0) {
            fo = FetchOptions.Builder.withLimit(limit);
            if (offset > 0) {
                fo.offset(offset);
            }
        } else if (offset > 0) {
            fo = FetchOptions.Builder.withOffset(offset);
        } else {
            fo = FetchOptions.Builder.withLimit(DEFAULT_LIMIT);
        }

        Query.Filter userFilter =
                new Query.FilterPredicate(Task.OWNER,
                        Query.FilterOperator.EQUAL,
                        owner);
        Query query = new Query(Task.TABLE_NAME).setFilter(userFilter);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        List<Entity> entitiesTasks = datastore.prepare(query).asList(fo);
        List<Task> tasks = new ArrayList<Task>(entitiesTasks.size());
        for (Entity task : entitiesTasks) {
            tasks.add(new Task(task.getProperties()));
        }
        return tasks;
    }

    public static void addTasks(HttpServletResponse resp, List<Task> tasks) {
        if (tasks.isEmpty()) {
            ResponseHelper.write(resp, Status.OK);
            return;
        }

        List<Entity> entitiesTasks = new ArrayList<Entity>(tasks.size());
        for (Task task : tasks) {
            Entity entityTask = new Entity(Task.TABLE_NAME);
            entityTask.setProperty(Task.OWNER, task.getOwner());
            entityTask.setProperty(Task.TITLE, task.getTitle());
            entityTask.setProperty(Task.CONTENT, task.getContent());
            entityTask.setProperty(Task.TIMESTAMP, task.getTimestamp());
            entityTask.setProperty(Task.TAGS, task.getTags());
            entitiesTasks.add(entityTask);
        }
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        TransactionOptions options = TransactionOptions.Builder.withXG(true);
        Transaction transaction = datastore.beginTransaction(options);
        try {
            datastore.put(entitiesTasks);
            transaction.commit();
        } catch (Exception e) {
            ResponseHelper.write(resp, e.toString(), Status.INTERNAL_SERVER_ERROR);
            return;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
                ResponseHelper.write(resp, Status.INTERNAL_SERVER_ERROR);
                return;
            }
        }
        ResponseHelper.write(resp, Status.CREATED);
    }

}
