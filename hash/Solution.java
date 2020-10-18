import java.util.HashMap;

class Solution {
	
public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for (String name : participant) {
        	if (map.get(name) == null) {
        		map.put(name, 1);
        	} 
        	// �ߺ��� �̸� ����
        	else {
        		map.put(name, map.get(name) + 1);
        	}
        }
        
        // ������ ����� value�� ����
        for (String name : completion) {
        	map.put(name, map.get(name) - 1);
        }
        
        // map�� value�� 1�� ��� �������� ���� ����
        for (String name : map.keySet()) {
        	if (map.get(name) == 1) {
        		answer = name;
        		break;
        	}
        		
        }
        
        return answer;
    }	
}
