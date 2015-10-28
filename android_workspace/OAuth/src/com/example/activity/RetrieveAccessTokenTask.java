package com.example.activity;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class RetrieveAccessTokenTask extends
		AsyncTask<Uri, Void, Void> {

	final String TAG = "OAuth";
	
	private Context context;
	private OAuthConsumer consumer;
	private OAuthProvider provider;
	private SharedPreferences prefs;
	
	
	public RetrieveAccessTokenTask(Context context, OAuthConsumer consumer,
			OAuthProvider provider, SharedPreferences prefs) {
		super();
		this.context = context;
		this.consumer = consumer;
		this.provider = provider;
		this.prefs = prefs;
	}


	@Override
	protected Void doInBackground(Uri... params) {
		// TODO Auto-generated method stub
		final Uri uri = params[0];
		final String oauth_verifier = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);
		
		try {
			provider.retrieveAccessToken(consumer, oauth_verifier);
			
			final Editor edit = prefs.edit();
			edit.putString(OAuth.OAUTH_TOKEN, consumer.getToken());
			edit.putString(OAuth.OAUTH_TOKEN_SECRET, consumer.getTokenSecret());
			edit.commit();
			
			String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
			String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
			
			consumer.setTokenWithSecret(token, secret);
			//context.startActivity(new Intent(context,MainActivity.class));
			
			Log.i(TAG,"OAuth - Access Token Retrieved");
		} catch (Exception e){
			Log.e(TAG,"OAuth - Access Token Retrieval Error",e);
		}
		
		return null;
	}

}
