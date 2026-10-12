package net.artdeen.mojo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.artdeen.mojo.R;
import net.kdt.pojavlaunch.Tools;
import net.kdt.pojavlaunch.value.Instance;
import net.kdt.pojavlaunch.value.Instances;
import net.kdt.pojavlaunch.utils.FileUtils;
import net.kdt.pojavlaunch.extra.mcVersionSpinner;

import java.io.File;

public class MainMenuFragment extends Fragment {

    public static final String TAG = "MainMenuFragment";
    private mcVersionSpinner mVersionSpinner;

    public MainMenuFragment() {
        super(R.layout.fragment_launcher);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton mEditProfileButton = view.findViewById(R.id.edit_profile_button);
        Button mPlayButton = view.findViewById(R.id.play_button);
        mVersionSpinner = view.findViewById(R.id.version_spinner);

        if (mPlayButton != null) {
            mPlayButton.setOnClickListener(v -> {
                Tools.swapFragment(requireActivity(), MainMenuFragment.class, "PlayFragment");
            });
        }
    }

    private void openGameDirectory(Context context) {
        Instance instance = Instances.loadSelectedInstance();
        if(instance == null) {
            Toast.makeText(context, R.string.global_no_instance, Toast.LENGTH_SHORT).show();
            return;
        }
        File gameDirectory = instance.getGameDirectory();
        if(FileUtils.ensureDirectorySilently(gameDirectory)) {
            Tools.openPath(context, gameDirectory.getAbsolutePath());
        } else {
            Toast.makeText(context, R.string.global_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void runInstallerWithConfirmation() {
        // Empty implementation to avoid build errors
    }
        }
