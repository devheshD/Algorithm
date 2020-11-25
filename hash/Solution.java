import java.util.HashMap;

class Solution {
	
public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for (String name : participant) {
        	if (map.get(name) == null) {
        		map.put(name, 1);
        	} 
        	// 중복된 이름 제거
        	else {
        		map.put(name, map.get(name) + 1);
        	}
        }
        
        // 완주한 사람의 value값 감소
        for (String name : completion) {
        	map.put(name, map.get(name) - 1);
        }
        
        // map의 value가 1일 경우 완주하지 못한 선수
        for (String name : map.keySet()) {
        	if (map.get(name) == 1) {
        		answer = name;
        		break;
        	}
        		
        }
         
        return answer;
    }	
}
