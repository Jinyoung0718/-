import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.lang.Math;

public class java_20250916 {

	class Solution {
		private int timeToMinute(String time) {
			String[] parts = time.split(":");
			int hour = Integer.parseInt(parts[0]);
			int minute = Integer.parseInt(parts[1]);
			return hour * 60 + minute;
		}

		public int[] solution(int[] fees, String[] records) {
			Map<String, List<int[]>> memo = new HashMap<>();

			for (String record : records) {
				String[] parts = record.split(" ");
				String timeStr = parts[0];
				String carNum = parts[1];
				String status = parts[2];

				int timeMin = timeToMinute(timeStr);

				memo.putIfAbsent(carNum, new ArrayList<>());
				memo.get(carNum).add(new int[]{timeMin, status.equals("IN") ? 1 : 0});
			}

			List<String> carNums = new ArrayList<>(memo.keySet());
			Collections.sort(carNums);

			List<Integer> result = new ArrayList<>();

			for (String carNum : carNums) {
				List<int[]> recordList = memo.get(carNum);
				int stack = -1;
				int accureTime = 0;

				for (int[] rec : recordList) {
					int timeMin = rec[0];
					int status = rec[1];

					if (status == 1) {
						stack = timeMin;
					} else {
						accureTime += (timeMin - stack);
						stack = -1;
					}
				}

				if (stack != -1) {
					accureTime += timeToMinute("23:59") - stack;
				}

				int fee = 0;
				if (accureTime <= fees[0]) {
					fee = fees[1];
				} else {
					fee = fees[1] + (int)Math.ceil((accureTime - fees[0]) / (double)fees[2]) * fees[3];
				}

				result.add(fee);
			}

			return result.stream().mapToInt(i -> i).toArray();
		}
	}

}
