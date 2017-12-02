import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Menu
{
	final String[] STAGE_A_TEST_1_CONFIG =	{ "777777", "3333"};
	final String[] STAGE_A_TEST_1_CONFIG_MOVES =	{ "93", "102" };
	final String[] STAGE_B_TEST_1_CONFIG =	{ "734561", "3333"};
	final String[] STAGE_B_TEST_1_CONFIG_MOVES =	{ "89", "98" };
	final String[] STAGE_B_TEST_2_CONFIG =	{ "137561", "3333" };
	final String[] STAGE_B_TEST_2_CONFIG_MOVES =	{ "105", "116" };
	final String[] STAGE_B_TEST_3_CONFIG =	{ "171717", "3333" };
	final String[] STAGE_B_TEST_3_CONFIG_MOVES =	{ "109", "120" };

	final String[] STAGE_C_TEST_1_CONFIG =	{ "734561", "231231" };
	final String[] STAGE_C_TEST_1_CONFIG_MOVES =	{ "223", "245" };
	final String[] STAGE_C_TEST_2_CONFIG =	{ "222222", "321113" };
	final String[] STAGE_C_TEST_2_CONFIG_MOVES =	{ "170", "187" };
	final String[] STAGE_C_TEST_3_CONFIG =	{ "444444", "311123" };
	final String[] STAGE_C_TEST_3_CONFIG_MOVES =	{ "194", "213" };
	final String[] STAGE_C_TEST_4_CONFIG =	{ "777777", "222111111" };
	final String[] STAGE_C_TEST_4_CONFIG_MOVES =	{ "301", "331" };
	final String[] STAGE_C_TEST_5_CONFIG =	{ "676767", "1332" };
	final String[] STAGE_C_TEST_5_CONFIG_MOVES =	{ "132", "145" };

	final Scanner sc = new Scanner(System.in);

	private double score;
	private double numMoves;

	StringBuilder sb = new StringBuilder();


	public void runMenu()
	{
		Robot.sb = this.sb;
		Robot.runningScore = String.valueOf(this.score);
		int response;

		do
		{
			printMenu();

			System.out.print("Enter selection: ");
			while (!sc.hasNextInt())
			{

				printMenu();
				sc.next(); // this is important!
			}
			response = Integer.parseInt(sc.nextLine());

			System.out.println();

			switch (Integer.valueOf(response))
			{

			case 1:

				runStageATest(null);
				System.out.println("Press any key to return to the menu");
				sc.nextLine();
				break;

			case 2:

				runStageBTest1(null);
				System.out.println("Press any key to return to the menu");
				sc.nextLine();
				break;

			case 3:
				runStageBTest2(null);
				System.out.println("Press any key to return to the menu");
				sc.nextLine();
				break;

			case 4:
				runStageBTest3(null);
				System.out.println("Press any key to return to the menu");
				sc.nextLine();
				break;

			case 5:
				runStageCTest1(null);
				System.out.println("Press any key to return to the menu");
				sc.nextLine();
				break;

			case 6:
				runStageCTest2(null);
				System.out.println("Press any key to return to the menu");
				sc.nextLine();
				break;

			case 7:
				runStageCTest3(null);
				System.out.println("Press any key to return to the menu");
				sc.nextLine();
				break;

			case 8:
				runStageCTest4(null);
				System.out.println("Press any key to return to the menu");
				sc.nextLine();
				break;

			case 9:
				runCustomTest();
				System.out.println("Press any key to return to the menu");
				sc.nextLine();
				break;

			case 10:
				runAllTests();
				System.exit(0);

			case 11:
				break;

			default:
				System.out.println("Error - invalid selection!");
			}
			System.out.println();

		}
		while (response != 0);
	}

	public void runStageATest(StringBuilder sb)
	{
		double maxMarks = 1.0;
		System.out.println(
				"Running Stage A Test - bars = "
				+ STAGE_A_TEST_1_CONFIG[0] + ", blocks = 3333 (default bar / block config)");
		System.out.println("Minimum move count: " + STAGE_A_TEST_1_CONFIG_MOVES[0]);

		Robot.main(STAGE_A_TEST_1_CONFIG);
		System.out.println("You completed the task in: " +  (int)(Robot.getNumberOfMoves()-1) + " moves.");

		double success = readFromFile("success.txt");
		if(sb != null && success != 1.0)
		{
			updateScoreForTestA(sb, "A", 0.50, STAGE_A_TEST_1_CONFIG_MOVES, Robot.numBlocksMoved);
			Robot.numBlocksMoved = 0;
		}
	}

	public void runStageBTest1(StringBuilder sb)
	{
		System.out.println(
				"Running Stage B Test - bars = "
				+ STAGE_B_TEST_1_CONFIG[0] + ", blocks = 3333 (default block config)");

		System.out.println("Minimum move count: " + STAGE_B_TEST_1_CONFIG_MOVES[0]);
		Robot.main(STAGE_B_TEST_1_CONFIG);
		System.out.println("You completed the task in: " +  (int)(Robot.getNumberOfMoves()-1) + " moves.");

		double success = readFromFile("success.txt");
		if(sb != null && success != 1.0 && Robot.numBlocksMoved == 4)
		{
			sb.append("\n*************************************************\n");
			sb.append("PART B - RESULTS\n");
			sb.append("*************************************************\n");
			sb.append("\n1.2 marks - You completed scenario 1\n");
			updateScore(sb, "B", 1.2, STAGE_B_TEST_1_CONFIG_MOVES);

			if(Robot.getNumberOfMoves() -1 <= Integer.parseInt(STAGE_B_TEST_1_CONFIG_MOVES[1]))
			{
				sb.append("\n0.6 marks - You completed the minimum number of moves for scenario 1" +"\n");
				updateScore(sb, "B", 0.6, STAGE_B_TEST_1_CONFIG_MOVES);
			}
//			sb.append("0.30 marks - You completed the task in "
//					+ (int)numMoves +".  You achieved the minimum moves of "
//					+ moves[0] + " for part " + part +"\n");
		}
		Robot.numBlocksMoved = 0;
	}

	public void runStageBTest2(StringBuilder sb)
	{
		System.out.println(
				"Running Stage B Test - bars = "
				+ STAGE_B_TEST_2_CONFIG[0] + ", blocks = 3333 (default block config)");
		System.out.println("Minimum move count: " + STAGE_B_TEST_2_CONFIG_MOVES[0]);
		Robot.main(STAGE_B_TEST_2_CONFIG);
		System.out.println("You completed the task in: " +  (int)(Robot.getNumberOfMoves()-1) + " moves.");

		double success = readFromFile("success.txt");
		if(sb != null && success != 1.0  && Robot.numBlocksMoved == 4)
		{
			sb.append("0.2 marks - You completed scenario 2" +"\n");
			updateScore(sb, "B", 0.2, STAGE_B_TEST_2_CONFIG_MOVES);
			if(Robot.getNumberOfMoves() -1 <= Integer.parseInt(STAGE_B_TEST_2_CONFIG_MOVES[1]))
			{
				sb.append("0.1 marks - You achieved the minimum number of moves for scenario 2" +"\n");
				updateScore(sb, "B", 0.1, STAGE_B_TEST_2_CONFIG_MOVES);
			}
		}
		Robot.numBlocksMoved = 0;
	}

	public void runStageBTest3(StringBuilder sb)
	{
		System.out.println(
				"Running Stage B Test - bars = "
				+ STAGE_B_TEST_3_CONFIG[0] + ", blocks = 3333 (default block config)");
		System.out.println("Minimum move count: " + STAGE_B_TEST_3_CONFIG_MOVES[0]);
		Robot.main(STAGE_B_TEST_3_CONFIG);
		System.out.println("You completed the task in: " +  (int)(Robot.getNumberOfMoves()-1) + " moves.");
		double success = readFromFile("success.txt");
		if(sb != null && success != 1.0  && Robot.numBlocksMoved == 4)
		{
			sb.append("0.6 marks - You completed scenario 3" +"\n");
			updateScore(sb, "B", 0.6, STAGE_B_TEST_3_CONFIG_MOVES);
			if(Robot.getNumberOfMoves() -1 <= Integer.parseInt(STAGE_B_TEST_3_CONFIG_MOVES[1]))
			{
				sb.append("0.3 marks - You achieved the minimum number of moves for scenario 3" +"\n");
				updateScore(sb, "B", 0.3, STAGE_B_TEST_3_CONFIG_MOVES);
			}
			sb.append("Running Total: " + score + " out of 10\n");
		}
		Robot.numBlocksMoved = 0;
	}

	public void runStageCTest1(StringBuilder sb)
	{
		System.out.println(
				"Running Stage C Test 1 - bars = "
				+ STAGE_C_TEST_1_CONFIG[0] + ", blocks = " + STAGE_C_TEST_1_CONFIG[0]);
		System.out.println("Minimum move count: " + STAGE_C_TEST_1_CONFIG_MOVES[0]);
		Robot.main(STAGE_C_TEST_1_CONFIG);
		System.out.println("You completed the task in: " +  (int)(Robot.getNumberOfMoves()-1) + " moves.");

		double success = readFromFile("success.txt");
		if(sb != null && success != 1.0  && Robot.numBlocksMoved == 6)
		{
			sb.append("\n*************************************************\n");
			sb.append("PART C - RESULTS\n");
			sb.append("*************************************************\n");
			sb.append("\n1.25 marks - You completed scenario 1 for part C" +"\n");
			updateScore(sb, "B", 1.25, STAGE_C_TEST_1_CONFIG_MOVES);
			if(Robot.getNumberOfMoves() -1 <= Integer.parseInt(STAGE_C_TEST_1_CONFIG_MOVES[1]))
			{
				sb.append("0.5 marks - You achieved the minimum number of moves for scenario 1" +"\n");
				updateScore(sb, "B", 0.5, STAGE_C_TEST_1_CONFIG_MOVES);
			}
		}
		Robot.numBlocksMoved = 0;
	}

	public void runStageCTest2(StringBuilder sb)
	{
		System.out.println(
				"Running Stage C Test 2 - bars = "
				+ STAGE_C_TEST_2_CONFIG[0] + ", blocks = " + STAGE_C_TEST_2_CONFIG[1]);
		System.out.println("Minimum move count: " + STAGE_C_TEST_2_CONFIG_MOVES[0]);
		Robot.main(STAGE_C_TEST_2_CONFIG);
		System.out.println("You completed the task in: " +  (int)(Robot.getNumberOfMoves()-1) + " moves.");
		double success = readFromFile("success.txt");
		if(sb != null && success != 1.0  && Robot.numBlocksMoved == 6)
		{
			sb.append("0.25 marks - You completed scenario 2 for part C" +"\n");
			updateScore(sb, "B", 0.25, STAGE_C_TEST_2_CONFIG_MOVES);
			if(Robot.getNumberOfMoves() -1 <= Integer.parseInt(STAGE_C_TEST_2_CONFIG_MOVES[1]))
			{
				sb.append("0.1 marks - You achieved the minimum number of moves for scenario 2" +"\n");
				updateScore(sb, "B", 0.1, STAGE_C_TEST_2_CONFIG_MOVES);
			}
		}
		Robot.numBlocksMoved = 0;
	}

	public void runStageCTest3(StringBuilder sb)
	{
		System.out.println(
				"Running Stage C Test 3 - bars = "
				+ STAGE_C_TEST_3_CONFIG[0] + ", blocks = " + STAGE_C_TEST_3_CONFIG[1]);
		System.out.println("Minimum move count: " + STAGE_C_TEST_3_CONFIG_MOVES[0]);
		Robot.main(STAGE_C_TEST_3_CONFIG);
		System.out.println("You completed the task in: " +  (int)(Robot.getNumberOfMoves()-1) + " moves.");
		double success = readFromFile("success.txt");
		if(sb != null && success != 1.0  && Robot.numBlocksMoved == 6)
		{
			sb.append("0.25 marks - You completed scenario 3 for part C" +"\n");
			updateScore(sb, "B", 0.25, STAGE_C_TEST_3_CONFIG_MOVES);
			if(Robot.getNumberOfMoves() -1 <= Integer.parseInt(STAGE_C_TEST_3_CONFIG_MOVES[1]))
			{
				sb.append("0.1 marks - You achieved the minimum number of moves for scenario 3" +"\n");
				updateScore(sb, "B", 0.1, STAGE_C_TEST_3_CONFIG_MOVES);
			}
		}
		Robot.numBlocksMoved = 0;
	}

	public void runStageCTest4(StringBuilder sb)
	{
		System.out.println(
				"Running Stage C Test 4 - bars = "
				+ STAGE_C_TEST_4_CONFIG[0] + ", blocks = " + STAGE_C_TEST_4_CONFIG[1]);
		System.out.println("Minimum move count: " + STAGE_C_TEST_4_CONFIG_MOVES[0]);
		Robot.main(STAGE_C_TEST_4_CONFIG);
		System.out.println("You completed the task in: " +  (int)(Robot.getNumberOfMoves()-1) + " moves.");
		double success = readFromFile("success.txt");
		if(sb != null && success != 1.0  && Robot.numBlocksMoved == 9)
		{
			sb.append("0.75 marks - You completed scenario 4 for part C" +"\n");
			updateScore(sb, "B", 0.75, STAGE_C_TEST_4_CONFIG_MOVES);
			if(Robot.getNumberOfMoves() -1 <= Integer.parseInt(STAGE_C_TEST_4_CONFIG_MOVES[1]))
			{
				sb.append("0.3 marks - You achieved the minimum number of moves for scenario 4" +"\n");
				updateScore(sb, "B", 0.3, STAGE_C_TEST_4_CONFIG_MOVES);
			}
		}
		Robot.numBlocksMoved = 0;
	}

	public void runStageCTest5(StringBuilder sb)
	{
		System.out.println(
				"Running Stage C Test 5 - bars = 734561, blocks = 1332");
		System.out.println("Minimum move count: " + STAGE_C_TEST_5_CONFIG_MOVES[0]);
		Robot.main(STAGE_C_TEST_5_CONFIG);
		System.out.println("You completed the task in: " +  (int)(Robot.getNumberOfMoves()-1) + " moves.");
		double success = readFromFile("success.txt");
		if(sb != null && success != 1.0)
		{
			sb.append("0.50 marks - You completed scenario 5 for part C" +"\n");
			updateScore(sb, "C", 0.50, STAGE_C_TEST_5_CONFIG_MOVES);
		}
		Robot.numBlocksMoved =0;
	}

	public void runCustomTest()
	{
		System.out.print(
				"Running custom test - please enter bar and block heights below: ");
		Scanner scCustom = new Scanner(System.in);
		String configInput = scCustom.nextLine();
		System.out.println();

		String[] configArgs = configInput.split(" ");
		System.out.println("Running robot with config bars = "
				+ (configArgs[0].length() == 0 ? "{7,7,7,7,7,7} (default)"
						: configArgs[0])
				+ ", blocks: " + (configArgs.length == 1 ? "{3,3,3,3} (default)"
						: configArgs[1]));
		System.out.println();

		Robot.main(configArgs);
	}

	public void runAllTests()
	{
		Robot.assessment = true;
		writeToFile("0.0");
//		StringBuilder sb = new StringBuilder();

		System.out.println(
				"Running all tests - hit enter after each test is complete to contine:");
		System.out.println();
		runStageATest(sb);


		System.out.println();
		runStageBTest1(sb);

		System.out.println();
		runStageBTest2(sb);
		System.out.println();

		runStageBTest3(sb);
		System.out.println();

		runStageCTest1(sb);
		System.out.println();

		runStageCTest2(sb);

		System.out.println();

		runStageCTest3(sb);

		System.out.println();

		runStageCTest4(sb);

		System.out.println();

		System.out.println(sb.toString());
		System.out.println("*********************************************");
		System.out.println("PLEASE NOTE THESE MARKS ARE INDICATIVE ONLY");
		System.out.println("YOUR SUBMISSION WILL BE CHECKED MANUALLY");
		System.out.println("*********************************************");
		System.out.println("Your functional score is: " + readFromFile("score.txt") + " out of 10");
		System.out.println("Your submission will also be assessed for code quality and checked manually.");
	}

	public void printMenu()
	{
		System.out.println(
				"************************** ROBOT TEST HARNESS MENU **************************");
		System.out.println();
		System.out.println(
				"IMPORTANT: YOU MUST PASS EACH TEST BEFORE MOVING ON TO THE NEXT TEST");
		System.out.println(
				"For example to be awarded marks in Part C, you must first pass all scenarios \nfor Part B.");
		System.out.println(
				"IMPORTANT: ANY TEST AFTER A FAILED TEST WILL NOT BE ASSESSED.");
		System.out.println(
				"******************************************************************************");
		System.out.println();
		System.out.println(
				"1. Stage A Test 1 - bars = "
				+ STAGE_A_TEST_1_CONFIG[0] + ", blocks = " + STAGE_A_TEST_1_CONFIG[1] + "\n");
		System.out.println(
				"2. Stage B Test 1 - bars = "
				+ STAGE_B_TEST_1_CONFIG[0] + ", blocks = " + STAGE_B_TEST_1_CONFIG[1] + "\n");
		System.out.println(
				"3. Stage B Test 2 - bars = "
				+ STAGE_B_TEST_2_CONFIG[0] + ", blocks = " + STAGE_B_TEST_2_CONFIG[1] + "\n");
		System.out.println(
				"4. Stage B Test 3 - bars = "
				+ STAGE_B_TEST_3_CONFIG[0] + ", blocks = " + STAGE_B_TEST_3_CONFIG[1] + "\n");
		System.out.println(
				"5. Stage C Test 1 - bars = "
				+ STAGE_C_TEST_1_CONFIG[0] + ", blocks = " + STAGE_C_TEST_1_CONFIG[1] + "\n");
		System.out
				.println("6. Stage C Test 2 - bars = "
				+ STAGE_C_TEST_2_CONFIG[0] + ", blocks = " + STAGE_C_TEST_2_CONFIG[1] + "\n");
		System.out
				.println("7. Stage C Test 3 - bars = "
				+ STAGE_C_TEST_3_CONFIG[0] + ", blocks = " + STAGE_C_TEST_3_CONFIG[1] + "\n");
		System.out
				.println("8. Stage C Test 4 - bars = "
				+ STAGE_C_TEST_4_CONFIG[0] + ", blocks = " + STAGE_C_TEST_4_CONFIG[1] + "\n");
//		System.out
//		.println("8. Stage C Test 5 - bars = 676767, blocks = 1332\n");
		System.out.println(
				"9. Stage C Test Custom (user supplies bar / block config)\n");
		System.out.println("10. Run All Tests (1 - 8)\n");

		System.out.println("0. Exit Test Harness\n");
		System.out.println(
				"IMPORTANT: YOU MUST PASS EACH TEST BEFORE MOVING ON TO THE NEXT TEST");
		System.out.println(
				"IMPORTANT: ANY TEST AFTER A FAILED TEST WILL NOT BE ASSESSED.");
		System.out.println();
	}


	private Double readFromFile(String filename)
	{
		 File f = new File(filename);

		 if(!f.exists()) {
			    try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //creating it
			}

			else if(f.exists() && !f.isDirectory()) {
			    //append it
			}
		    FileReader r = null;
			try {
				r = new FileReader(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    BufferedReader b = new BufferedReader(r);
		    String out = "";
		    String k="";
		    try {
				while( (out=b.readLine()) != null) {
				    k+=out;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				b.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if(k.equals(""))
		    {
		    	return 0.0;
		    }
		    return Double.parseDouble(k);
	}

	private void writeToFile(String score)
	{
		File file = new File("score.txt");
	    FileWriter fw = null;
		try {
			fw = new FileWriter(file, false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    BufferedWriter bw = new BufferedWriter(fw);
	    try {
			bw.write(score);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateScore(StringBuilder sb, String part, double testPassed, String[] moves)
	{
		numMoves = Robot.getNumberOfMoves()-1;
		score = readFromFile("score.txt");
		score += testPassed;
		Robot.runningScore = String.valueOf(this.score);
//		if(numMoves <= Double.parseDouble(moves[0]))
//		{
////			score +=  0.30;
//
//		}
//		else if(numMoves <= Double.parseDouble(moves[1]))
//		{
//			score +=  0.25;
//			sb.append("0.25 marks - You completed the task in "
//			+ (int)numMoves + " .You ALMOST achieved the minimum moves of "
//					+ moves[0] + " for part " + part+"\n");
//		}
		writeToFile(String.valueOf(Math.round(score)));
	}

	private void updateScoreForTestA(StringBuilder sb, String part, double testPassed, String[] moves, double maxMarks)
	{

		sb.append("*************************************************\n");
		sb.append("PART A - RESULTS\n");
		sb.append("*************************************************\n");
		numMoves = Robot.getNumberOfMoves()-1;
		score = readFromFile("score.txt");
		double scoreBlocksMoved = 0.0;
		switch (Robot.numBlocksMoved)
		{
			case 1:
				scoreBlocksMoved = 0.5;
				score += scoreBlocksMoved;

			case 2:
				scoreBlocksMoved = 0.6;
				score += scoreBlocksMoved;
			case 3:
				scoreBlocksMoved = 0.7;
				score += scoreBlocksMoved;
			case 4:
				scoreBlocksMoved = 1.0;
				score += scoreBlocksMoved;
		}
		sb.append(scoreBlocksMoved + " marks - You successfully moved: " + Robot.numBlocksMoved + " blocks.\n");
//		score += maxMarks;
		if(numMoves <= Double.parseDouble(moves[0]))
		{
			score +=  0.50;
			sb.append("0.50 marks - You completed the task in "
					+ (int)numMoves +".  \nYou achieved the minimum moves of "
					+ moves[0] + " for part " + part +"\n");
		}
		else if(numMoves <= Double.parseDouble(moves[1]))
		{
			score +=  0.35;
			sb.append("0.35 marks - You completed the task in "
			+ (int)numMoves + " .\nYou ALMOST achieved the minimum moves of "
					+ moves[0] + " for part " + part+"\n");
		}
		sb.append("\nRunning Total: " + score + " out of 10\n");
		Robot.runningScore = String.valueOf(this.score);
		writeToFile(String.valueOf(score));
	}
}
