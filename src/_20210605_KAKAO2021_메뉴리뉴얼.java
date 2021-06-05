import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/*
- orders 배열의 크기는 2 이상 20 이하입니다.
- orders 배열의 각 원소는 크기가 2 이상 10 이하인 문자열입니다.
	- 각 문자열은 알파벳 대문자로만 이루어져 있습니다.
	- 각 문자열에는 같은 알파벳이 중복해서 들어있지 않습니다.
- course 배열의 크기는 1 이상 10 이하입니다.
	- course 배열의 각 원소는 2 이상 10 이하인 자연수가 오름차순으로 정렬되어 있습니다.
	- course 배열에는 같은 값이 중복해서 들어있지 않습니다.
- 정답은 각 코스요리 메뉴의 구성을 문자열 형식으로 배열에 담아 사전 순으로 오름차순 정렬해서 return 해주세요.
	- 배열의 각 원소에 저장된 문자열 또한 알파벳 오름차순으로 정렬되어야 합니다.
	- 만약 가장 많이 함께 주문된 메뉴 구성이 여러 개라면, 모두 배열에 담아 return 하면 됩니다.
	- orders와 course 매개변수는 return 하는 배열의 길이가 1 이상이 되도록 주어집니다.
 */
public class _20210605_KAKAO2021_메뉴리뉴얼 {

	// 주문된 메뉴 조합 기록을 위한 hashMap
	static HashMap<String, Integer> combiMap = new HashMap<>();

	public String[] solution(String[] orders, int[] course) {
		String[] answer = {};

		// 주문내용 분석
		for (String order : orders) {
			char[] menu = order.toCharArray(); // 단일 메뉴

			// 코스 요리의 개수 파악
			for (int menuNum : course) {

				// 주문한 메뉴보다 요구되는 코스요리의 수가 많은 경우 (오름차순이므로 종료 가능)
				if (order.length() < menuNum) {
					break;
				}

				// 조합 및 기록
				makeCourse(menu, menuNum, 0, 0, "");

			}
		}

		// 선택될 메뉴와 해당 메뉴의 개수
		int idxLen = course[course.length - 1];
		String[] selectMenu = new String[idxLen];
		int[] selectCnt = new int[idxLen];

		// 분석된 내용에서 선택
		for (String menu : combiMap.keySet()) {
			int orderCnt = combiMap.get(menu);
			int idx = menu.length() - 1;

			if (orderCnt < 2) {
				continue;
			}

			// 아직 해당 개수의 조합이 없는 경우
			if (selectMenu[idx] == null) {
				selectMenu[idx] = menu;
				selectCnt[idx] = orderCnt;
			}
			// 해당 조합이 있는 경우
			else {

				if (selectCnt[idx] < orderCnt) { // 기존 보다 높은 경우
					selectMenu[idx] = menu;
					selectCnt[idx] = orderCnt;
				} else if (selectCnt[idx] == orderCnt) {// 기존과 같은 경우
					selectMenu[idx] += "/" + menu;
				}
			}

		}

		ArrayList<String> tempArray = new ArrayList<String>();
		for (String str : selectMenu) {
			// 선택된 코스가 있는 경우
			if (str != null) {

				// 코스가 여러개인 경우
				if (str.contains("/")) {
					String[] tempStr = str.split("/");
					for (String splitStr : tempStr) {
						tempArray.add(splitStr);
					}
				} else {
					tempArray.add(str);
				}
			}
		}

		// 응답 형식
		Collections.sort(tempArray);
		answer = new String[tempArray.size()];
		int idx = 0;
		for (String str : tempArray) {
			answer[idx++] = str;
		}

		return answer;
	}

	private void makeCourse(char[] menu, int menuNum, int idx, int len, String course) {
		// 원하는 조합의 개수가 완성된 경우
		if (menuNum == len) {
			String[] tempArr = course.split("");
			Arrays.sort(tempArr);
			course = "";
			for (String s : tempArr) {
				course += s;
			}

			if (combiMap.containsKey(course)) {
				combiMap.put(course, combiMap.get(course) + 1);
			} else {
				combiMap.put(course, 1);
			}
			return;
		}

		// 조합을 완성하지 못한 경우
		if (idx >= menu.length || menuNum < len) {
			return;
		}

		makeCourse(menu, menuNum, idx + 1, len + 1, course + menu[idx]);
		makeCourse(menu, menuNum, idx + 1, len, course);

	}

}
