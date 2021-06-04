package algo;

/*
아이디의 길이는 3자 이상 15자 이하여야 합니다.
아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.

*/

public class _20210605_KAKAO2021_신규아이디추천 {
	public String solution(String new_id) {
		String answer = "";

		// 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
		answer = new_id.toLowerCase();

		// 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
		StringBuilder tempSB = new StringBuilder();
		String specialStr = "~!@#$%^&*()=+[{]}:?,<>/";
		for (int idx = 0; idx < answer.length(); idx++) {
			char target = answer.charAt(idx);
			if (!specialStr.contains(Character.toString(target))) {
				tempSB.append(target);
			}
		}

		// 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
		for (int idx = 0; idx < tempSB.length() - 1; idx++) {
			char preChar = tempSB.charAt(idx);
			char postChar = tempSB.charAt(idx + 1);

			if (preChar == '.' && postChar == '.') {
				tempSB.deleteCharAt(idx);
				idx--;
			}
		}

		// 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
		if (tempSB.length() > 0 && tempSB.charAt(0) == '.') {
			tempSB.deleteCharAt(0);
		}

		if (tempSB.length() > 0 && tempSB.charAt(tempSB.length() - 1) == '.') {
			tempSB.deleteCharAt(tempSB.length() - 1);
		}

		// 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
		if (tempSB.length() == 0) {
			tempSB.append("a");
		}

		// 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
		// 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
		if (tempSB.length() > 15) {
			tempSB.delete(15, tempSB.length());
			if (tempSB.charAt(14) == '.') {
				tempSB.deleteCharAt(14);
			}
		}

		// 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
		if (tempSB.length() < 3) {
			while (tempSB.length() != 3) {
				tempSB.append(tempSB.charAt(tempSB.length() - 1));
			}
		}

		answer = tempSB.toString();

		return answer;
	}
}
