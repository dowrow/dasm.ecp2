package es.upm.miw.dasmecp2.api;

import android.os.AsyncTask;
import android.util.Log;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterAPI {

    private static final String CONSUMER_KEY = "GTKcP4XG2feHvNNqlMmabuer8";

    private static final String CONSUMER_SECRET = "3zdWmCfPmuoyX2fDxb6PuXcNQpezNJWzPKEBzTCA7Dfbmkhywi";

    private static final String ACCESS_TOKEN = "4150573335-HRjVI3VlKNXav3mNtSxFXDddj2YQC4lGDIJAaiL";

    private static final String ACCESS_TOKEN_SECRET = "CfOWiCT8rgqIJ6iOFyTaLTYNqo3e8pUFAFJI5BVJ4yuL0";

    private Twitter twitter;

    public TwitterAPI() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }
    public void updateStatus(String status) {
        new UpdateStatusTask().execute(status);
    }

    private class UpdateStatusTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                twitter.updateStatus(new StatusUpdate(params[0]));
            } catch (TwitterException e) {
                Log.e("Error updating status", e.getMessage());
            }
            return null;
        }
    }

}
