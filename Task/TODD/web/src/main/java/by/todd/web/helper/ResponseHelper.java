package by.todd.web.helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import by.todd.api.Status;

/**
 * Created by sergey on 22.11.2014.
 */
public class ResponseHelper {
    private static final String MSG_ERROR = "{'msg':'error'}";
    private static final String MSG_UNAUTHORIZED = "{'msg':'unauthorized'}";
    private static final String MSG_OK = "{'msg':'ok'}";
    private static final String MSG_BAD_REQUEST = "{'msg':'bad request'}";
    private static final String MSG_CREATED = "{'msg':'created'}";
    private static final String MSG_INTERNAL_SERVER_ERROR = "{'msg':'internal server error'}";
    private static final String MSG_METHOD_NOT_ALLOWED = "{'msg':'method not allowed'}";

    public static void write(HttpServletResponse resp, String content, int statusCode) {
        try {
            resp.getWriter().print(new JSONObject(content).toString());
            resp.setStatus(statusCode);
        } catch (IOException e) {
            statusCode = Status.INTERNAL_SERVER_ERROR;
        } catch (JSONException e) {
            statusCode = Status.INTERNAL_SERVER_ERROR;
        } finally {
            resp.setStatus(statusCode);
        }
    }

    public static void write(HttpServletResponse resp, int statusCode) {
        String content;
        switch (statusCode) {
            case Status.UNAUTHORIZED:
                content = MSG_UNAUTHORIZED;
                break;
            case Status.OK:
                content = MSG_OK;
                break;
            case Status.BAD_REQUEST:
                content = MSG_BAD_REQUEST;
                break;
            case Status.CREATED:
                content = MSG_CREATED;
                break;
            case Status.INTERNAL_SERVER_ERROR:
                content = MSG_INTERNAL_SERVER_ERROR;
                break;
            case Status.METHOD_NOT_ALLOWED:
                content = MSG_METHOD_NOT_ALLOWED;
                break;
            default:
                content = MSG_ERROR;
                break;
        }
        write(resp, content, statusCode);
    }

    public static void writeOk(HttpServletResponse resp, JSONObject content) {
        write(resp, content.toString(), Status.OK);
    }
}
