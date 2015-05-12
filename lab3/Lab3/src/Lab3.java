import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class Lab3 {
	public static void main(String[] args) throws IOException {
		new Lab3().run(args[0]);
	}

	HashMap<String, Set<String>> cityMap;
	LinkedList<Edge> edges = new LinkedList<Edge>();
	public void run(String fileName) throws IOException {
		Scanner sc = new Scanner(new FileInputStream(fileName));
		cityMap = new HashMap<String, Set<String>>();
		while (sc.hasNext()) {
			String currentString = sc.nextLine();
			if (!currentString.contains("[")) {
				Set<String> set = new HashSet<String>();
				currentString = currentString.trim();
				set.add(currentString);
				cityMap.put(currentString, set);
			}
			else {
			String[] city = currentString.split("--"); 	// d0 contains city1
			String[] city_weight = city[1].split("\\["); // e0 contains city2'
			city_weight[1] = city_weight[1].substring(0, city_weight[1].length()-1);	// e1 contains weight without ]
			edges.add(new Edge(city[0], city_weight[0].trim(), Integer.parseInt(city_weight[1])));
				
			}
		}
		Collections.sort(edges);
		ArrayList<Edge> MST = new ArrayList<Edge>();
		while (!edges.isEmpty()){
			Edge check = edges.remove(0);
			
			Set<String> visited1 = cityMap.get(check.v1);
			Set<String> visited2 = cityMap.get(check.v2);
			
			if (!visited1.equals(visited2))
				MST.add(check);
			visited1.addAll(visited2);
			for (String i: visited1) {
				cityMap.put(i, visited1);
			}
		}
		int sum = 0;
		for (Edge edge : MST) {
			sum = sum + edge.w;
			System.out.println(edge.v1 + " " + edge.v2 + " " + edge.w);
		}
		System.out.println(sum);
	}
}
