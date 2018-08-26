
import java.io.*;
import java.util.*;


/**
 * Incidence matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Artur Gromek - s3657198, 2018.
 * @author Harrison Smith - s3658817, 2018
 *
 */
public class IndMatrix <T extends Object> implements FriendshipGraph<T>
{
	
	int[][] incMatrix;
	int numEdges;
	int numVertices;
	ArrayList <T> vertices = new ArrayList <T> ();

	/**
	 * Contructs empty graph.
	 */
    public IndMatrix() {
    	incMatrix = new int[0][0];
    	numEdges = 0;
    	numVertices = 0;
    	
    } // end of IndMatrix()
     
    public void addVertex(T vertLabel) {
    	if (vertices.contains(vertLabel))
    	{
    		throw new IllegalArgumentException("A vertex with this label already exists!");
    	}
	//create new matrix with space for extra vertex
    	int[][] newIncMatrix = new int[numEdges][numVertices + 1];
	//copy values and initialise the value for all edges to 0
    	for (int i = 0; i < numEdges; i++)
    	{
    		for (int j = 0; j < numVertices; j++)
    		{
    			newIncMatrix[i][j] = incMatrix[i][j];
    		}
    	}
    	for (int i = 0; i < numEdges;i++)
    	{
    		newIncMatrix[i][numVertices] = 0;
    	}
    	vertices.add(vertLabel);
    	incMatrix = newIncMatrix;
    	numVertices++;
    	return;
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
    	if (!vertices.contains(srcLabel) || !vertices.contains(tarLabel))
    	{
    		throw new IllegalArgumentException("One or more vertices don't exist");
    	}
	////create new matrix with space for extra vertex
    	int[][] newIncMatrix = new int[numEdges + 1][numVertices];
	//copy values
    	for (int i = 0; i < numEdges; i++)
    	{
    		for (int j = 0; j < numVertices; j++)
    		{
    			newIncMatrix[i][j] = incMatrix[i][j];
    		}
    	}
	//set all values for the edge to 0, then set the values for the vertices to 1
    	for (int i = 0; i < numVertices; i++)
    	{
    		newIncMatrix[numEdges][i] = 0;
    	}
    	int srcIndex = vertices.indexOf(srcLabel);
    	int tarIndex = vertices.indexOf(tarLabel);
    	newIncMatrix[numEdges][srcIndex] = 1;
    	newIncMatrix[numEdges][tarIndex] = 1;
    	numEdges++;
	incMatrix = newIncMatrix;
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        
    	if (!vertices.contains(vertLabel))
    	{
    		throw new IllegalArgumentException("This vertex does not exist!");
    	}
    	ArrayList<T> neighbours = new ArrayList<T>();
        int vertIndex = vertices.indexOf(vertLabel);
	//search for any edges that contain 1 for the vertex
        for (int i = 0; i < numEdges;i++)
        {
            if (incMatrix[i][vertIndex] == 1)
	    {
		//find the vertices that share the edge, then add to the collection
	        for (int j = 0; j < numVertices;j++)
	        {
		    if (j == vertIndex)
		    {
			continue;
		    }
		    if (incMatrix[i][j] == 1)
		    {
			neighbours.add(vertices.get(j));
    		    }
	    	}
            }
        }
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
    	if (!vertices.contains(vertLabel))
    	{
    	    throw new IllegalArgumentException("This vertex does not exist!");
    	}
    	int vertIndex  = vertices.indexOf(vertLabel);
	//collection to record which edges need to be removed with the vertex
    	ArrayList < Integer > edgesToRemove = new ArrayList<Integer >();
    	//checks which edges need to be removed
    	for (int i = 0; i < numEdges;i++)
    	{
    	    if (incMatrix[i][vertIndex] == 1)
    	    {
    	    	edgesToRemove.add(i);
    	    }
    	}
    	
    	int numEdgesNew = numEdges - edgesToRemove.size();
    	int[][] newIncMatrix = new int[numEdgesNew][numVertices - 1];
    	//copies old matrix to new, skipping edges and vertex that are being removed
	//newI and newJ are used to keep track of which elements are being copied to
    	int newI = 0;
    	for (int i = 0; i <numEdges;i++)
    	{
    	    int newJ = 0;
    	    if (edgesToRemove.contains(i))
    		continue;
    	    for (int j = 0; j < numVertices;j++)
    	    {
    		if (j == vertIndex)
    		    continue;
    		else
    		{
    		    newIncMatrix[newI][newJ] = incMatrix[i][j];
    		    newJ++;
    		}
    	    }
    	    newI++;
    	}
    	incMatrix = newIncMatrix;
    	numEdges = numEdgesNew;
    	numVertices--;
    	vertices.remove(vertLabel);
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
    	if (!vertices.contains(srcLabel) || !vertices.contains(tarLabel))
    	{
    	    throw new IllegalArgumentException("One or more of the vertices doesn't exist");
    	}
    	int srcIndex = vertices.indexOf(srcLabel);
    	int tarIndex = vertices.indexOf(tarLabel);
    	int edgeToRemove = -1;
    	for (int i = 0; i < numEdges;i++)
    	{
    	    if (incMatrix[i][srcIndex] == 1 && incMatrix[i][tarIndex] == 1)
    	    {
    		edgeToRemove = i;
    		break;
    	    }
    	}
    	if (edgeToRemove == -1)
    	{
    	    throw new IllegalArgumentException("No Edge found with the vertex labels given!");
    	}
	//copy values to new matrix with updated dimensions
    	int[][] newIncMatrix = new int[numEdges - 1][numVertices];
    	int newI = 0;
    	for (int i = 0;i < numEdges; i++)
    	{
    	    if (i == edgeToRemove)
    	    {
    		continue;
    	    }
    	    for (int j = 0; j < numVertices;j++)
    	    {
    		newIncMatrix[newI][j] = incMatrix[i][j];
    	    }
	    newI++;
    	}
	numEdges--;
    	incMatrix = newIncMatrix;
    	return;
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
        for (int i = 0; i < numVertices;i++)
        {
            os.printf("%s ", vertices.get(i));
        }
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
	//print edges in 1 direction
        for (int i = 0; i < numEdges; i++)
        {
            boolean found = false;
            for (int j = 0; j < numVertices;j++)
            {
        	if (incMatrix[i][j] == 1)
        	{
        	    os.printf("%s ", vertices.get(j));		
        	    found = true;
        	}
            }
            if (found)
            {
        	os.printf("\n");
            }
        }
	//print them in the other direction
	for (int i = numEdges - 1; i >= 0; i--)
	{
	    boolean found = false;
	    for (int j = numVertices - 1; j >= 0; j--)
	    {
		if (incMatrix[i][j] == 1)
		{
		    os.printf("%s ", vertices.get(j));
		    found = true;
		}
	    }
	    if (found)
	    {
		os.printf("\n");
	    }
	}
    } // end of printEdges()
    
    //implements breadth-first search to find shortest distance
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
	if (!vertices.contains(vertLabel1) || !vertices.contains(vertLabel2))
	{
        throw new IllegalArgumentException("One or more of the vertices doesn't exist");
	}
	ArrayList <T> visited = new ArrayList <T> ();
	ArrayList <T> currSet = neighbours(vertLabel1);
	visited.add(vertLabel1);
	int distance = 0;
	while (!visited.contains(vertLabel2) && !currSet.isEmpty())
	{
	    //set of vertices to search next iteration
	    ArrayList <T> nextSet = new ArrayList <T> ();
	    for (int i = 0; i < currSet.size();i++)
	    {
		if (visited.contains(currSet.get(i)))
		{
		    continue;
		}
		else
		{
		    ArrayList <T> temp = neighbours(currSet.get(i));
		    //add all neighbours of current vertex's that are not already visited
		    //	or going to be visited already
		    for (int j = 0; j < temp.size();j++)
		    {
			if (!nextSet.contains(temp.get(j)) && !visited.contains(temp.get(j)))
			{
			    nextSet.add(temp.get(j));
			}
		    }
		}
		visited.add(currSet.get(i));
		//equivalent to dequeue
		currSet.remove(currSet.get(i));
		//since the first element is always removed, i does not need to be incremented
		//i-- to set it back to 0, tried removing i++ but it broke it xd
		i--;
	    }
	    distance++;
	    currSet = nextSet;
	}
	if (visited.contains(vertLabel2))
	{
	    return distance;
	}
	// if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class IndMatrix
