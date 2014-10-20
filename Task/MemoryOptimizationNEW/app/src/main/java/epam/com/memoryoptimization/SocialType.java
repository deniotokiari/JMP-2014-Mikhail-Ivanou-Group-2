package epam.com.memoryoptimization;

/**
 * Created by Mike on 22.11.13.
 */
public enum SocialType {

    FACEBOOK(R.drawable.ic_fb),
    LINKED_IN(R.drawable.ic_linked),
    TWITTER(R.drawable.ic_twitter),
    GOOGLE_PLUS(R.drawable.ic_g_plus),
    VKONTAKTE(R.drawable.ic_vk),
    ODNOKLASSNIKI(R.drawable.ic_odnoklassniki);

    int value;

    SocialType(int ic_fb) {
        value = ic_fb;
    }

    public int getResId() {
        return value;
    }

}

