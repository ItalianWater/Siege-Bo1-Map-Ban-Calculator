import java.io.*;
import java.util.*;

public class MapOrderAverager {
    public static void main(String[] args) {
        List<String> mapNames = Arrays.asList("Oregon", "Kafe", "Chalet", "Border", "Nighthaven", "Consulate", "Skyscraper", "Clubhouse", "Bank");
        Map<String, List<Integer>> mapPositions = new HashMap<>();
        
        for (String mapName : mapNames) {
            mapPositions.put(mapName, new ArrayList<>());
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the text file containing the map orders (or type 'end' to finish):");
        String filePath = scanner.nextLine();

        if (filePath.trim().equalsIgnoreCase("end")) {
            System.out.println("No file provided. Exiting.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] inputMaps = line.split(",");
                if (inputMaps.length != 4) {
                    System.out.println("Invalid number of map names in line: " + line);
                    continue;
                }

                for (int i = 0; i < inputMaps.length; i++) {
                    String mapName = inputMaps[i].trim();
                    if (!mapNames.contains(mapName)) {
                        System.out.println("Invalid map name in line: " + line);
                        continue;
                    }
                    mapPositions.get(mapName).add(i + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("Average positions of map names:");
        for (String mapName : mapNames) {
            List<Integer> positions = mapPositions.get(mapName);
            if (positions.isEmpty()) {
                System.out.println(mapName + ": No data");
            } else {
                double average = positions.stream().mapToInt(Integer::intValue).average().orElse(0.0);
                System.out.printf("%s: %.2f%n", mapName, average);
            }
        }
        
        scanner.close();
    }
}
