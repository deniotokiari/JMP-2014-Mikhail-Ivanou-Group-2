package by.todd.api;

/**
 * Created by sergey on 22.11.2014.
 */
public class Api {

    public static final String BASE_PATH = "http://exemplary-terra-772.appspot.com";

    public static final class AddTask {
        public static final String PATH = "/add";
    }

    public static final class GetTask {
        public static final String PARAM_OFFSET = "offset";
        public static final String PARAM_LIMIT = "limit";
        public static final String PARAM_OWNER = "owner";

        public static final String PATH = "/get?" + PARAM_OWNER + "=";
    }

}
