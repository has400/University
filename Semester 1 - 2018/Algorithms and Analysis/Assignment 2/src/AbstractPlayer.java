import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer {
	public List<String> attributes;
	public List<List<String>> attributeValues;
	public List<Person> possiblePeople;
	public Person chosenPerson;

	public AbstractPlayer(String gameFilename, String chosenName) throws IOException {
		attributes = new ArrayList<>();
		attributeValues = new ArrayList<List<String>>();
		Scanner input = new Scanner(new FileInputStream(gameFilename));
		possiblePeople = new ArrayList<>();
		// hopefully puts all types of attributes into attributes list,
		// then put all the possible values into the attributeValues list,
		// at the same index :)))
		int i = 0;
		String temp = "";
		while (!(temp = input.nextLine()).equals("")) {
			String[] tempArray = temp.split(" ");
			attributes.add(tempArray[0]);
			// System.out.print("\nAttribute: " + tempArray[0] + " Values: ");
			attributeValues.add(new ArrayList<>());
			for (int j = 1; j < tempArray.length; j++) {
				attributeValues.get(i).add(tempArray[j]);
				// System.out.print(tempArray[j] + ", ");
			}
			i++;
		}

		String tempName = "";
		do {
			temp = input.nextLine();
			if (temp.isEmpty()) {
				continue;
			}
			tempName = temp;
			ArrayList<String> attributeList = new ArrayList<>();
			do {
				temp = input.nextLine();
				if (temp.isEmpty()) {
					break;
				}
				String[] tempArray = temp.split(" ");
				String attributeType = tempArray[0];
				String attributeValue = tempArray[1];
				attributeList.add(attributeValue);
			} while (input.hasNextLine());
			possiblePeople.add(new Person(tempName, attributeList));
		} while (input.hasNextLine());
		input.close();

		for (Person p : possiblePeople) {
			if (p.name.equals(chosenName)) {
				chosenPerson = p;
			}

		}
	}
}
