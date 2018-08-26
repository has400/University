import java.io.*;
import java.util.*;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Harrison Smith - s3658817.
 * @author Artur Gromek - s3657198.
 */
public class AdjMatrix < T extends Object > implements FriendshipGraph < T > {
    int[][] adjMatrix;
    int defaultSize = 0;
    ArrayList < T > Vertices = new ArrayList < > ();
    int size;

    /**
     * Contructs empty graph.
     */
    public AdjMatrix() {
        adjMatrix = new int[defaultSize][defaultSize];
        size = defaultSize;
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }
	
	/**
     * Adds a vertex to the graph.
     */
    public void addVertex(T vertLabel) {
        //Makes the 2d Array bigger, allowing for another vertex.
        if (!Vertices.contains(vertLabel)) {
            Vertices.add(vertLabel);
            updateMatrix();
        }
    }

	/**
     * Updates matrix when its needs to increase.
     */
    public void updateMatrix() {
        int[][] adjUpdatedMatrix = new int[size + 1][size + 1];
		
		//Initiliases a graph which is empty.
        for (int i = 0; i < adjUpdatedMatrix.length; i++) {
            for (int j = 0; j < adjUpdatedMatrix.length; j++) {
                adjUpdatedMatrix[i][j] = 0;
            }
        }

        //Replaces new matrix with old matrix information
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                adjUpdatedMatrix[i][j] = adjMatrix[i][j];
            }
        }
		
        size++;
        //Updates previous array with new larger array.
        adjMatrix = adjUpdatedMatrix;
    }
	
	/**
     * Adds an edge to the graph.
     */
    public void addEdge(T srcLabel, T tarLabel) {
		if (!Vertices.contains(srcLabel) || !Vertices.contains(tarLabel)){
		throw new IllegalArgumentException("One or more vertexes don't exist");
		}
		
		//Makes vertices both ways [i][j] and [j][i] an edge.
        adjMatrix[Vertices.indexOf(srcLabel)][Vertices.indexOf(tarLabel)] = 1;
        adjMatrix[Vertices.indexOf(tarLabel)][Vertices.indexOf(srcLabel)] = 1;
    }

	/**
     * Returns the neighbours of verticies. 
     */
    public ArrayList < T > neighbours(T vertLabel) {
		if (!Vertices.contains(vertLabel)){
		throw new IllegalArgumentException("This vertex doesn't exist");
		}
		
        ArrayList < T > neighbours = new ArrayList < T > ();
        int index = Vertices.indexOf(vertLabel);
		
		//Checks for all neighbours for the given vertex in the 2D array.
        for (int i = 0; i < adjMatrix[index].length; i++) {
            if (adjMatrix[index][i] == 1) {
                neighbours.add(Vertices.get(i));
            }
        }

        return neighbours;
    }

	/**
     * Removes a vertex. 
     */
    public void removeVertex(T vertLabel) {
        //Make the 2d Array smaller, by deleting a vertex.
        if (Vertices.contains(vertLabel)) {
            updateRemoveMatrix(Vertices.indexOf(vertLabel));
            Vertices.remove(vertLabel);
        }
    } // end of removeVertex()

	/**
     * Removes an edge. 
     */
    public void removeEdge(T srcLabel, T tarLabel) {
		if (!Vertices.contains(srcLabel) || !Vertices.contains(tarLabel)){
		throw new IllegalArgumentException("One or more vertexes don't exist");
		}
		
		//Removes vertices both ways [i][j] and [j][i] from being an edge.
        adjMatrix[Vertices.indexOf(srcLabel)][Vertices.indexOf(tarLabel)] = 0;
        adjMatrix[Vertices.indexOf(tarLabel)][Vertices.indexOf(srcLabel)] = 0;
    } // end of removeEdges()

	/**
     * Prints the verticies. 
     */
    public void printVertices(PrintWriter os) {
		
        // Loops through all of the verticies and prints them.
        for (Object T: Vertices) {
            os.print(T + " ");
        }
        os.println();
    } // end of printVertices()

	/**
     * Prints the edges. 
     */
    public void printEdges(PrintWriter os) {
		
		//Loops through the 2d array and sees in which positions contains a 1, because it signifies an edge.
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] == 1) {
                    os.println(Vertices.get(i) + " " + Vertices.get(j));
                }
            }
        }
    } // end of printEdges()

	/**
     * Updates the 2d when you're removing a vertex. 
     */
    public void updateRemoveMatrix(int removeVal) {
        int[][] adjUpdatedMatrix = new int[size - 1][size - 1];
        //Puts all nodes to 0 in smaller 2d array.
        for (int i = 0; i < adjUpdatedMatrix.length; i++) {
            for (int j = 0; j < adjUpdatedMatrix.length; j++) {
                adjUpdatedMatrix[i][j] = 0;
            }
        }

        //Adds all the edges back to the graph which arent in the position of the vertex that is being removed.
        for (int i = 0; i < adjUpdatedMatrix.length; i++) {
            for (int j = 0; j < adjUpdatedMatrix.length; j++) {
                int indexI = i;
                int indexJ = j;
                if (i >= removeVal && i < adjMatrix.length) {
                    indexI = i + 1;
                }
                if (j >= removeVal && j < adjMatrix.length) {
                    indexJ = j + 1;
                }

                adjUpdatedMatrix[i][j] = adjMatrix[indexI][indexJ];
            }
        }
        //Updates previous array with new larger array.
        adjMatrix = adjUpdatedMatrix;
        size--;
    }
	
	/**
     * Finds the shortestpath for two vertexs. 
     */
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
		
		if (!Vertices.contains(vertLabel1) || !Vertices.contains(vertLabel2)){
		throw new IllegalArgumentException("One or more vertexes don't exist");
		}
		
        int intMax = Integer.MAX_VALUE;

        int distanceBetweenVert[] = new int[size];
        Boolean visited[] = new Boolean[size];

        for (int i = 0; i < size; i++) {
            distanceBetweenVert[i] = intMax;
            visited[i] = false;
        }
		
		//Makes source vertex distance to itself 0.
        distanceBetweenVert[Vertices.indexOf(vertLabel1)] = 0;
		
		//Goes through the verticies and sees shortest path.
        for (int i = 0; i < size; i++) {

            int min = intMax;
            int index = -1;

            for (int v = 0; v < size; v++){
				if (visited[v] == true){
					continue;
				}
				
                if (distanceBetweenVert[v] <= min) {
                    min = distanceBetweenVert[v];
                    index = v;
                }
			}
			
            visited[index] = true;

            for (int v = 0; v < size; v++){
				//Skips current iteration of loop if node has been visited already.
				if (visited[v] == true && distanceBetweenVert[index] == intMax){
					continue;
				}
				
                if (adjMatrix[index][v] != 0 && distanceBetweenVert[index] + adjMatrix[index][v] < distanceBetweenVert[v]){
                    distanceBetweenVert[v] = distanceBetweenVert[index] + adjMatrix[index][v];
				}
			}
        }
		
		//Checks that the destination vertex is reachable.
		// distanceBetweenVert[Vertices.indexOf(vertLabel2)] > 0  is to check if it isnt its self.
        if (distanceBetweenVert[Vertices.indexOf(vertLabel2)] != intMax) {
            return distanceBetweenVert[Vertices.indexOf(vertLabel2)];
        }

        return disconnectedDist;
    } // end of shortestPathDistance()

} // end of class AdjMatrix