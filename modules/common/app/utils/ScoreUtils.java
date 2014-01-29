package utils;

import models.Ranking;

public class ScoreUtils {

	/**
	 * 人気度集計（個別のツイートごとのポイントは除外したパターン）
	 *
	 * ポジティブ：1点 ネガティブ：-1点 ニュートラル：0点 として、 (countPositive -
	 * countNegative)/totalCount
	 *
	 * ランキング算出方法を変更（2014/01/29）
	 * 投稿全体に占める割合ではなく、投稿数の多さをベースに算出ポイントとする
	 * ポジティブ：1点 ネガティブ：-1点 ニュートラル：0点 として、 (countPositive -
	 * countNegative)
	 *
	 * @param item
	 * @return
	 */
	public static double getScore(Ranking ranking) {
		if (ranking.totalCount > 0) {
		//	return (double) (ranking.countPositive - ranking.countNegative)
			//		/ ranking.totalCount;
			return (double) (ranking.countPositive - ranking.countNegative);
		}
		return 0D;
	}
}
