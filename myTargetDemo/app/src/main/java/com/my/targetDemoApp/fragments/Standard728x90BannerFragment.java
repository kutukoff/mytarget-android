package com.my.targetDemoApp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.my.target.ads.MyTargetView;
import com.my.targetDemoApp.DefaultSlots;
import com.my.targetDemoApp.R;
import com.my.targetDemoApp.utils.Tools;

public class Standard728x90BannerFragment extends Fragment implements StandardAdFragment
{
	private static final String SLOT_ID = "slot_id";

	public static Standard728x90BannerFragment newInstance(int slotId)
	{

		Standard728x90BannerFragment fragment = new Standard728x90BannerFragment();
		Bundle args = new Bundle();
		args.putInt(SLOT_ID, slotId);
		fragment.setArguments(args);
		return fragment;
	}

	private ViewGroup layout;
	private ProgressBar progressBar;
	@Nullable
	private MyTargetView adView;
	private int slotId;
	private boolean visible;
	private boolean loaded;
	private MyTargetView.MyTargetViewListener adListener = new MyTargetView.MyTargetViewListener()
	{
		@Override
		public void onLoad(MyTargetView myTargetView)
		{
			loaded = true;
			progressBar.setVisibility(View.GONE);
			myTargetView.setVisibility(View.VISIBLE);
			if (visible) myTargetView.start();
		}

		@Override
		public void onNoAd(String s, MyTargetView myTargetView)
		{
			progressBar.setVisibility(View.GONE);
			Snackbar.make(myTargetView, getString(R.string.no_ad), Snackbar.LENGTH_LONG).show();
		}

		@Override
		public void onClick(MyTargetView myTargetView)
		{

		}
	};

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_banner_728x90, container, false);

		layout = (RelativeLayout) v.findViewById(R.id.content_layout);
		progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
		ListView listView = (ListView) v.findViewById(R.id.list_view);
		listView.setAdapter(new Standard320x50BannerFragment.ListAdapter(getActivity()));
		return v;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		Bundle args = getArguments();
		slotId = args.getInt(SLOT_ID, DefaultSlots.SLOT_STANDARD_BANNER_728x90);
		initAd();
	}

	@Override
	public void reloadAd()
	{
		if (adView != null && adView.getParent() != null)
		{
			((ViewGroup) adView.getParent()).removeView(adView);
			adView.destroy();
			adView = null;
			initAd();
		}
	}

	@Override
	public void pause()
	{
		if (adView != null) adView.pause();
	}

	@Override
	public void resume()
	{
		if (adView != null) adView.resume();
	}

	@Override
	public void destroy()
	{
		if (adView != null) adView.destroy();
		loaded = false;
	}

	@Override
	public void show()
	{
		visible = true;
		if (loaded && adView != null)
		{
			adView.start();
			adView.resume();
		}
	}

	@Override
	public void hide()
	{
		visible = false;
		pause();
	}

	private void initAd()
	{
		adView = new MyTargetView(getActivity());
		adView.init(slotId, MyTargetView.AdSize.BANNER_728x90);
		Tools.fillCustomParamsUserData(adView.getCustomParams());
		adView.setListener(adListener);

		float density = getResources().getDisplayMetrics().density;
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (90 * density));
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		adView.setLayoutParams(params);

		layout.addView(adView);
		adView.load();
	}
}
