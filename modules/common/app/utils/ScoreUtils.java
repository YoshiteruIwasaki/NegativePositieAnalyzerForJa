package utils;

import models.Ranking;

public class ScoreUtils {

	/**
	 * 人気度集計（個別のツイートごとのポイントは除外したパターン）
	 *
	 * ポジティブ：1点 ネガティブ：-1点 ニュートラル：0点 として、 (countPositive -
	 * countNegative)/totalCount
	 *
	 * @param item
	 * @return
	 */
	public static double getScore(Ranking ranking) {
		if (ranking.totalCount > 0) {
			return (double) (ranking.countPositive - ranking.countNegative)
					/ ranking.totalCount;
		}
		return 0D;
	}
}
