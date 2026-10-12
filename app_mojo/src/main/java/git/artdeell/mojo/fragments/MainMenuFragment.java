package net.kdt.pojavlaunch.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.kdt.pojavlaunch.Tools;
import net.kdt.pojavlaunch.instances.Instance;
import net.kdt.pojavlaunch.instances.Instances;
import net.kdt.pojavlaunch.utils.FileUtils;
import com.kdt.mcgui.mcVersionSpinner;

import java.io.File;

public class MainMenuFragment extends Fragment {

    public static final String TAG = "MainMenuFragment";
    private mcVersionSpinner mVersionSpinner;

    public MainMenuFragment() {
        super(git.artdeell.mojo.R.layout.fragment_launcher);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton mEditProfileButton = view.findViewById(git.artdeell.mojo.R.id.edit_profile_button);
        Button mPlayButton = view.findViewById(git.artdeell.mojo.R.id.play_button);
        mVersionSpinner = view.findViewById(git.artdeell.mojo.R.id.version_spinner);

        if (mPlayButton != null) {
            mPlayButton.setOnClickListener(v -> {
                Tools.swapFragment(requireActivity(), MainMenuFragment.class, "PlayFragment");
            });
        }
    }

    private void openGameDirectory(Context context) {
        Instance instance = Instances.loadSelectedInstance();
        if(instance == null) {
            Toast.makeText(context, "Secili bir instance yok", Toast.LENGTH_SHORT).show();
            return;
        }
        File gameDirectory = instance.getGameDirectory();
        if(FileUtils.ensureDirectorySilently(gameDirectory)) {
            Tools.openPath(context, gameDirectory.getAbsolutePath());
        } else {
            Toast.makeText(context, "Dizin acilamadi", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
