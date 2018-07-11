package ru.maxxlt.bakeme.ui.step;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Baking;
import ru.maxxlt.bakeme.data.Steps;
import ru.maxxlt.bakeme.models.BakingListViewModel;

public class StepFragment extends Fragment {
    PlayerView playerView;
    SimpleExoPlayer exoPlayer;
    TextView description;
    Button button_next, button_previous;
    int stepPosition, mainPosition;
    boolean isTwoPane;

    OnButtonClickListener onButtonClickListener;

    public interface OnButtonClickListener {
        void buttonClicked(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onButtonClickListener = (OnButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement ParseData");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        stepPosition = getArguments().getInt("stepposition");
        mainPosition = getArguments().getInt("bakepostion");
        isTwoPane = getArguments().getBoolean("isTwoPane");

        playerView = rootView.findViewById(R.id.player_pv);
        description = rootView.findViewById(R.id.description_tv);
        button_next = rootView.findViewById(R.id.button_next);
        button_previous = rootView.findViewById(R.id.button_previous);

        BakingListViewModel bakingListViewModel = ViewModelProviders.of(getActivity()).get(BakingListViewModel.class);
        bakingListViewModel.getSharingIsCaringBakery().observe(this, new Observer<List<Baking>>() {
            @Override
            public void onChanged(@Nullable List<Baking> bakings) {
                Baking baking = bakings.get(mainPosition);
                Steps steps = baking.getSteps().get(stepPosition);
                if (rootView.findViewById(R.id.portrait_steps_layout) != null) {
                    if (stepPosition == 0) {
                        button_previous.setVisibility(View.GONE);
                    } else if (stepPosition == baking.getSteps().size() - 1) {
                        button_next.setVisibility(View.GONE);
                    }
                    if (steps.getVideoURL().equals("")) {
                        playerView.setVisibility(View.GONE);
                    }

                    description.setText(steps.getDescription());
                    button_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onButtonClickListener.buttonClicked(stepPosition + 1);
                        }
                    });
                    button_previous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onButtonClickListener.buttonClicked(stepPosition - 1);
                        }
                    });
                } else if (rootView.findViewById(R.id.landscape_steps_layout) != null) {
                        hideSystemUI(rootView);
                    if (steps.getVideoURL().equals("")) {
                        playerView.setVisibility(View.GONE);
                    }
                }
                else {
                    if (stepPosition == 0) {
                        button_previous.setVisibility(View.GONE);
                    } else if (stepPosition == baking.getSteps().size() - 1) {
                        button_next.setVisibility(View.GONE);
                    }
                    if (steps.getVideoURL().equals("")) {
                        playerView.setVisibility(View.GONE);
                    }

                    description.setText(steps.getDescription());
                    button_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onButtonClickListener.buttonClicked(stepPosition + 1);
                        }
                    });
                    button_previous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onButtonClickListener.buttonClicked(stepPosition - 1);
                        }
                    });
                }
                setPlayer(steps.getVideoURL());

            }
        });
        return rootView;
    }

    private void setPlayer(String url) {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),
                new DefaultTrackSelector());
        playerView.setPlayer(exoPlayer);
        DefaultDataSourceFactory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(),
                        Util.getUserAgent(getContext(),
                                "BakeMe"));
        ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(url));

        exoPlayer.prepare(extractorMediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    //https://developer.android.com/training/system-ui/immersive#EnableFullscreen
    private void hideSystemUI(View rootView) {
        rootView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI(View rootView) {
        rootView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


    @Override
    public void onStop() {
        super.onStop();
        if (playerView != null) {
            playerView.setPlayer(null);
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }
}
