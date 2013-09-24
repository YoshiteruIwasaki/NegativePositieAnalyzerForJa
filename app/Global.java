import java.util.concurrent.TimeUnit;

import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import rikyu.Rikyu;
import scala.concurrent.duration.Duration;
import tasks.FeedReader;
import tasks.TwitterAnalyze;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {

		Rikyu.init();

		Runnable getAnalyze = new Runnable() {
			@Override
			public void run() {
				TwitterAnalyze.main();
			}
		};

		Runnable getFeed = new Runnable() {
			@Override
			public void run() {
				FeedReader.main();
			}
		};
		// 1時間ごとにRSS取得
		Akka.system()
				.scheduler()
				.schedule(Duration.create(0, TimeUnit.SECONDS),
						Duration.create(1, TimeUnit.HOURS), getFeed,
						Akka.system().dispatcher());
		// 5分ごとにTwitter検索
		Akka.system()
				.scheduler()
				.schedule(Duration.create(1, TimeUnit.SECONDS),
						Duration.create(300, TimeUnit.SECONDS), getAnalyze,
						Akka.system().dispatcher());

	}

}
