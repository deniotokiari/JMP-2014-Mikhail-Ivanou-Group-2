package epam.com.memoryoptimization;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mike on 19.10.2014.
 */
public class FragmentOne extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final View view = getView();
        if (view == null) {
            return;
        }
        final TextView viewById = (TextView) view.findViewById(R.id.section_label);
        viewById.setText(getString(R.string.click_me));//I've moved text to string resources
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), getString(R.string.msg), Toast.LENGTH_SHORT).show();//I've moved text to string resources
            }
        });
        ((ImageView) view.findViewById(R.id.section_icon)).setImageBitmap(IconManager.get().getIcon(SocialType.LINKED_IN));
    }
}
