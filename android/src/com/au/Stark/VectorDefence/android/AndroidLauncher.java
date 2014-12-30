package com.au.Stark.VectorDefence.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.au.Stark.VectorDefence.MainGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//cfg.useGL20 = true;
		//config.hideStatusBar = true;
		//config.useImmersiveMode = true;
		
		initialize(new MainGame(), config);
	}
}
