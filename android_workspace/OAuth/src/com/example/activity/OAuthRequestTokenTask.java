package com.example.activity;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

public class OAuthRequestTokenTask extends AsyncTask<Void, Void, Void> {

	private Context context;
	private OAuthConsumer consumer;
	private OAuthProvider provider;
	
	
	public OAuthRequestTokenTask(Context context, OAuthConsumer consumer,
			OAuthProvider provider) {
		super();
		this.context = context;
		this.consumer = consumer;
		this.provider = provider;
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		try {
			System.out.println("请求Request Token之前" + consumer.getToken());
			final String url = provider.retrieveRequestToken(consumer, Constants.OAUTH_CALLBACK_URL);
			System.out.println("请求Request Token之后" + consumer.getToken());
			System.out.println("uri-------->" + url);
			Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url)).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			context.startActivity(intent);
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
