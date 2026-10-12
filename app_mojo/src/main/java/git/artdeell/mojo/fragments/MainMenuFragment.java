package git.artdeell.mojo.fragments;

import static git.artdeell.mojo.Tools.openPath;
import static git.artdeell.mojo.Tools.shareLog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kdt.mcgui.mcVersionSpinner;

import git.artdeell.mojo.CustomControlsActivity;
import git.artdeell.mojo.R;

import git.artdeell.mojo.Tools;
import git.artdeell.mojo.contracts.OpenDocumentWithExtension;
import git.artdeell.mojo.extra.ExtraConstants;
import git.artdeell.mojo.extra.ExtraCore;
import git.artdeell.mojo.instances.Instance;
import git.artdeell.mojo.instances.Instances;
import git.artdeell.mojo.progresskeeper.ProgressKeeper;
import git.artdeell.mojo.utils.FileUtils;

import java.io.File;

public class MainMenuFragment extends Fragment {
    public static final String TAG = "MainMenuFragment";

    private mcVersionSpinner mVersionSpinner;

    private final ActivityResultLauncher<Object> mModInstallerLauncher =
            registerForActivityResult(new OpenDocumentWithExtension("jar"), (data)->{
                if(data != null) Tools.launchModInstaller(requireContext(), data);
    });         

    public MainMenuFragment(){
        super(R.layout.fragment_launcher);
    }

    @Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    ImageButton mEditProfileButton = view.findViewById(R.id.edit_profile_button);
    Button mPlayButton = view.findViewById(R.id.play_button);
    mVersionSpinner = view.findViewById(R.id.version_spinner);

    mPlayButton.setOnClickListener(v -> {
        Tools.swapFragment(requireActivity(), MainMenuFragment.class, "PlayFragment");
    });
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
        ExtraCore.setValue(ExtraConstants.REFRESH_ACCOUNT_SPINNER, true);
    

    private void runInstallerWithConfirmation() {
        if (ProgressKeeper.getTaskCount() == 0) {
            mModInstallerLauncher.launch(null);
        } else Toast.makeText(requireContext(), R.string.tasks_ongoing, Toast.LENGTH_LONG).show();
    }
    }
