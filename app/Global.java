import java.util.concurrent.TimeUnit;

import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import rikyu.Rikyu;
import scala.concurrent.duration.Duration;
import tasks.FeedReader;
import tasks.SetRanking;
import tasks.TwitterAnalyze;

public class Global extends GlobalSettings {

	public static final Long AKB_CATEGORY_ID = 5L;

	public static final Long TOKYO_CATEGORY_ID = 8L;

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

		Runnable setRanking = new Runnable() {
			@Override
			public void run() {
				SetRanking.main(AKB_CATEGORY_ID);
				SetRanking.main(TOKYO_CATEGORY_ID);
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
		// 1時間ごとに前日のランキング集計
		Akka.system()
				.scheduler()
				.schedule(Duration.create(60, TimeUnit.SECONDS),
						Duration.create(1, TimeUnit.HOURS), setRanking,
						Akka.system().dispatcher());

	}

}
