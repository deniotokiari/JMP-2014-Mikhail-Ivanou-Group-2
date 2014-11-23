package by.todd.api;

/**
 * Created by sergey on 22.11.2014.
 */
public class Api {

    public static final String BASE_PATH = "localhost:8080";

    public static final String PARAM_TOKEN = "token";

    public static final class AddTask {
        public static final String PATH = "/add";
    }

    public static final class GetTask {
        public static final String PATH = "/get";
        public static final String PARAM_OFFSET = "offset";
        public static final String PARAM_LIMIT = "limit";
    }

    public static final class SyncTask {
        public static final String PATH = "/sync";
        public static final String PARAM_DEVICE_TYPE = "deviceType";
    }
}
