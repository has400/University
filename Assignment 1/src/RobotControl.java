import java.util.Arrays;

	//r.up(); - Makes the robots height go up
	//r.down(); - Makes the robots height go down

	//r.extend(); - Makes the arm  to extend to the source blocks
	//r.contract(); - Makes the arm  to contract to the position where it must place the block

	//r.raise(); - Makes the depth of the arm which picks up the blocks raise up
	//r.lower(); - Makes the depth of the arm which picks up the blocks lower down

	//r.pick(); - Makes the robot pick up the block
	//r.drop(); - Makes the robot drop the block it is currently holding, however can not drop mid air.

	// r.speedUp(#); - Makes the robot operate at a faster speed, this faster speed is controlled by what number you put in.
	// r.slowDown(#); - Makes the robot operate at a slower speed, this slower speed is controlled by what number you put in.

class RobotControl
{
	private Robot r;
	public static StringBuilder sb;

	int h = 2; // Initial height of arm 1
	int w = 1; // Initial width of arm 2
	int d = 0; // Initial depth of arm 3

	int currentBar = 0; 
	int currentBlock = 0;  // Starts of as 0, updates when a block is picked up
	int contractArmIteration = 0; //starts off as 0, then minus one each time code is ran. 
	int currentBlockArraySubtract = 0; // iteration to get which current block it is in the array
	int sourceHeight= 0; //Set as one, later on calculated to see how many blocks high the source is
	int contractAmount = 7; //initial amount it contracts by to reach the first bar to place a #3 block on.
	int extendAmount = 10; //Initial amount the arm extends by to reach source pile

	int targetCol1Ht = 0; //Initially empty, adds 1 each time a block is placed down
	int targetCol2Ht = 0; //Initially empty, adds 2 each time a block is placed down

	public RobotControl(Robot r){
		this.r = r;
		if (Robot.assessment == true)
		{
			r.speedUp(5);
		}
	}
	
	public void control(int barHeights[], int blockHeights[]){
		//Uses the run as the source of the code to run the robot
	run(barHeights, blockHeights);}

public void run(int barHeights[],int blockHeights[]){
		//This sums the blocks in the source pile to see how tall it is
		for (int blocks : blockHeights)
			sourceHeight += blocks;
		
		//This makes sure the code loops until all of the source pile is gone
		while ( sourceHeight > 0 ){
		currentBlock = blockHeights[blockHeights.length - 1 - currentBlockArraySubtract++]; //current block is calculated by looking at the length of array and minus 1 each time the code runs through.
		//Makes the robot increase in height so it is high enough to pickup from the source blocks.
		while (h < sourceHeight + 1 )
		{
			r.up();
			h++;
		}
		//raise the depth of the arm so it wont crash into a block
		while (h - d < maxValue(barHeights) + maxValue(blockHeights) + 1 && d > 0)
		{
			r.raise();
			d--;
		}
		//extends up to pickup blocks from source pile
		while (w < extendAmount){
			r.extend();
			w++;
		}
		//Lowers depth of arm to a height which is 1 unit above source height in order to pick the block
		while (h - d > sourceHeight + 1){
			r.lower();
			d++;
		}

		r.pick();//Picks up block from source pile
		sourceHeight -= currentBlock; // updates source height by subtracting the current block after it is picked up


if (currentBlock == 3){
		contractAmount = 7 - contractArmIteration++;
		
		ReturnDepthLessThan0();//Makes sure depth resets to pickup new block
		ContractArm();//contract arm to place where it can place a block which is height = 3, which means it will contract by initially 7, but then by 6 and so on
		HeightUpandDownBlock3(blockHeights, barHeights);//makes sure there is a clearance of 3 when moving blocks
		//Lowers depth of arm to place block 3blocks above current bar
		while (h - d - 3  >  barHeights[currentBar] + 1){
			r.lower();
			d++;
			}
		r.drop();//Makes the block drop
		ReturnD(blockHeights, barHeights);//Makes the arm return to source blocks

		currentBar++;
		}



if (currentBlock == 1){
	contractAmount = 9;

	ReturnDepthLessThan0(); //Makes sure depth resets to pickup new block
	ContractArm();//contract arm to place where it can place a block which is height = 1, which means it will contract by 9
	HeightUpandDownBlock1(blockHeights, barHeights);//makes sure there is a clearance of 1 when moving blocks
	LowerArmBlock1(); //lowers block into correct height to drop
	r.drop();//Makes the block drop
	ReturnD(blockHeights, barHeights);//Makes the arm return to source blocks

	targetCol1Ht += 1; //adds one, so that the robot will place one block above the ground or blocks above
		}

if (currentBlock == 2){
	contractAmount = 8;

	ReturnDepthLessThan0(); //Makes sure depth resets to pickup new block
	HeightUpandDownBlock2(blockHeights, barHeights);//makes sure there is a clearance of 2 when moving blocks
	ContractArm(); //contract arm to place where it can place a block which is height = 2, which means it will contract by 8
	LowerArmBlock2(); //lowers block into correct height to drop
	r.drop(); //Makes the block drop
	ReturnD(blockHeights, barHeights); //Makes the arm return to source blocks

	targetCol2Ht += 2; //adds two, so that the robot will place two blocks above the ground or blocks above 

		}

	}

}
	//Methods
	
	//Lowers depth of arm to place block  1 block above current height of targetCol1Ht
	private void LowerArmBlock1() {
		while ((h - 1) - d - currentBlock > targetCol1Ht){
			r.lower();
			d++;
		}
	}
	
	//Lowers depth of arm to place block 2 blocks above current height of targetCol2Ht
	private void LowerArmBlock2() {
		while ((h - 1) - d - currentBlock > targetCol2Ht){
			r.lower();
			d++;
		}
		
	}
	
	//Makes sure height is adjusted to make sure a block of 3 can fit over the top
	private void HeightUpandDownBlock3(int[] blockHeights, int[] barHeights) {
		while (h > maxValue(barHeights) + maxValue(blockHeights) + 1){
			r.down();
			h--;
		}

		while (h < maxValue(barHeights) + maxValue(blockHeights) + 1){
			r.up();
			h++;
		}
	}
	
	//Makes sure height is adjusted to make sure a block of 1 can fit over the top
	private void HeightUpandDownBlock1(int barHeights[],int blockHeights[]) {
		while (h > maxValue(barHeights) + maxValue(blockHeights) + 1){
			r.down();
			h--;
		}
		while (h <= maxValue(barHeights) + maxValue(blockHeights) - d + 1){
			r.up();
			h++;
		}

	}
	
	//Makes sure height is adjusted to make sure a block of 2 can fit over the top
	private void HeightUpandDownBlock2(int barHeights[],int blockHeights[]) {
		while (h > maxValue(barHeights) + maxValue(blockHeights) - d + 2) {
			r.down();
			h--;
		}

		while (h <= maxValue(barHeights) + maxValue(blockHeights) - d + 2){
			r.up();
			h++;
		}

	}
	
	//Makes sure that the D is returned to 0
	private void ReturnDepthLessThan0() {
		while ( d > 0)
	    {
	        r.raise();
	        d--;
	    }
	}
	
	//Contracts arm to place block in correct position
	private void ContractArm() {
		while (contractAmount > 0)
		{
			r.contract();
			contractAmount--;
			w--;
		}

	}
	
	//Makes the depth back to a height where it can clear the blocks
	private void ReturnD(int barHeights[], int blockHeights[]) {
		//Checks if the depth is higher than the sum of the highest block and bar, in order to pick up and drop future blocks.
		while (h < maxValue(barHeights) + maxValue(blockHeights) + 1 && d > 0)
		{
			r.raise();
			d--;
		}
		//Checks that the difference between height and depth is high enough to pick up blocks.
		while (h - d < sourceHeight + 1 && d > 0)
		{
			r.raise();
			d--;
		}
	}
	
	//Returns the highest block in source pile, so that the height of robot can always be one above it. 
	public int maxValue(int barHeights[]){
		  int max = Arrays.stream(barHeights).max().getAsInt();
		  return max;
	}
}