package toy.project.exchange;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

//@SpringBootTest
class ExchangeApplicationTests {

//	@Test
	void contextLoads() {
	}

//	@Test
	public void programmers1() {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

		HashMap<String, HashMap<String, Object>> userIOHistory
				= new HashMap<>();

		for (String userInfo : record) {
			String[] detailInfo = userInfo.split(" ");

			String io = detailInfo[0];
			String userId = detailInfo[1];
			String nickName = "";
			if (!"Leave".equals(io)) {
				nickName = detailInfo[2];
			}

			HashMap<String, Object> innerMap = new HashMap<>();
			innerMap.put(nickName, io);

			userIOHistory.put(userId, innerMap);
		}

		System.out.println("userIOHistory -> " + userIOHistory.get("uid1234"));

	}

//	@Test
	public void 프로그래머스_숫자문자열과영단어() {
		String s = "one4seveneight";

		int answer = 0;

		HashMap<String, String> numberMap = new HashMap<>();
		numberMap.put("zero", "0");
		numberMap.put("one", "1");
		numberMap.put("two", "2");
		numberMap.put("three", "3");
		numberMap.put("four", "4");
		numberMap.put("five", "5");
		numberMap.put("six", "6");
		numberMap.put("seven", "7");
		numberMap.put("eight", "8");
		numberMap.put("nine", "9");

		for (String key : numberMap.keySet()) {
			System.out.println("_key->"+key);
			if (s.contains(key)) {
				System.out.println("_numberMap.get(key).toString()->"+numberMap.get(key).toString());
				s = s.replaceAll(key, numberMap.get(key).toString());
				System.out.println("_s replaceAll->"+s);
			}
		}

		System.out.println("s -> " + Integer.parseInt(s));
	}

	private int answer = 0;
	private String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};

	@Test
	public void 프로그래머스_단체사진찍기() {
		String[] data = {"N~F=0", "R~T>2"};
//		data = new String[]{"M~C<2", "C~M>1"};

		boolean[] isVisited = new boolean[8];
		dfs("", isVisited, data);
		System.out.println(answer);
//		return answer;
	}
	private void dfs(String names, boolean[] isVisited, String[] datas) {
		if (names.length() == 7) {
			if (check(names, datas)) { // 조건만족 체크
				answer++;
			}
			return;
		}
		for (int i = 0; i < 8; i++) { // 조합
			if (!isVisited[i]) {
				isVisited[i] = true;
				String name = names + friends[i];
				dfs(name, isVisited, datas);
				isVisited[i] = false;
			}
		}
	}
	// 조건대로 섰는지 체크
	private boolean check(String names, String[] datas) {
		for (String data : datas) {
			int position1 = names.indexOf(data.charAt(0)); // 프렌즈 포지션1
//			System.out.println("_ position1 -> " + position1);
			int position2 = names.indexOf(data.charAt(2)); // 프렌즈 포지션2
//			System.out.println("_ position2 -> " + position2);
			char op = data.charAt(3); // 수식
			int index = data.charAt(4) -'0'; // 간격
			if (op == '=') {
				if (!(Math.abs(position1 - position2) == index+1)) return false; //둘 포지션 차이를 구하기 위해선 index+1 을 해야함에 주의
			} else if (op == '>') {
				if (!(Math.abs(position1 - position2) > index+1)) return false;
			} else if (op == '<') {
				if (!(Math.abs(position1 - position2) < index+1)) return false;
			}
		}
		return true;
	}
}