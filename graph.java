
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

public class graph {
  
   

   private static final String INF = "&";
   private static final int INFINITY = 999999999;
   
   

   private int numVerts;
   private int[][] adjMatrix;
   private BufferedReader graphStream;
   public int current_edge_weight;
   private int[] next;


  

   public graph() {       
       new graph (10);
   }

   
   

   public graph (int numVs) {
       numVerts = numVs;       
       adjMatrix = new int[numVerts][numVerts];
       
       this.resetGraph();
       
       
       next = new int[numVerts];
       for (int i = 0; i < numVerts; i++)			
          next[i] = -1;
   }
   
   
  

   public graph (String filename) throws IOException {
       
       graphStream = fileIn (filename);
       
       inputGraph();
       
       

       next = new int[numVerts];       
       resetnext();
   }
    
    
  

   public void resetGraph() { 
      for (int row = 0; row < numVerts; row++) {
         for (int col = 0; col < numVerts; col++)
            adjMatrix[row][col] = INFINITY;
        
         adjMatrix[row][row] = 0;
      } 
   } 
    
    
   
   private BufferedReader fileIn (String file) {
      BufferedReader br = null;

      try
      {
         br = new BufferedReader (new FileReader (file));
      }
 
      catch (FileNotFoundException fnf)
      {
         System.out.println ("File " + file + " not found.\n");
      }
      
      catch (IOException io)
      {
         System.out.println ("File " + file + " has I/O problems.\n");
      }
      
      return br;
   }
   
   
   

   private void inputGraph() {
      int row, col;
      String line, current;
      StringTokenizer st; 
      
      try 
      {        
         
         line = graphStream.readLine(); 
         st = new StringTokenizer(line);
         current = st.nextToken();
         numVerts = Integer.parseInt (current);
         
         adjMatrix = new int[numVerts][numVerts];                     
         row = 0;
         
         while ((line = graphStream.readLine()) != null) {
            st = new StringTokenizer (line);
            col = 0;
            
            while (col < numVerts && st.hasMoreTokens()) {
               current = st.nextToken();
               if (current.compareTo (INF) == 0)
                  adjMatrix[row][col++] = INFINITY;
               else   
                  adjMatrix[row][col++] = Integer.parseInt (current); 
            }
                
            row++;   
         }
      }
      
      catch (IOException io)   
      {
         System.out.println ("File has I/O problems.\n");
      }
      
      catch (NumberFormatException nfe)
      {
         System .out.println (" File must contain only integers.\n");
      }
      
      finally 
      {      
         try
         {
            if (graphStream != null)
               graphStream.close();
         }
                 
         catch (IOException io2)
         {
            System.out.println ("Unable to close file.\n");
         }      
      }
   }
   
   

   public void insertVertex(int a, int x, int y)	// insert a vertex
   {
      if(x == y)					// if adjMatrix[i][i]
      {
         if(a != 0)                      // if value if not zero, display error and exit
         {
            System.out.println("Cannot initialize graph, adjMatrix[i][i] must be zero! Exiting...");
            System.exit(0);
         }// end if
      }// end outer if

      adjMatrix[x][y] = a;		// insert vertex into matrix

   }// end method insertVertex()



   

   public void display() {
      for (int row = 0; row < numVerts; row++) {
         for (int col = 0; col < numVerts; col++)
         
            // prints symbol instead of 999999999
            if (adjMatrix[row][col] > INFINITY - 0.01 * INFINITY)
               System.out.print ("& ");
            else
               System.out.print ("" + adjMatrix[row][col] + " ");
            
         System.out.println();
      }
      
      System.out.println("\n");   
   }
   
   
   

   public int vertices() {
      return numVerts;   
   } 
   
   
   

   public int edgeLength (int row, int col) {
      return adjMatrix[row][col];
   } 
   
   
   

   public int[] getRow (int row) {
      int[] oneRow = new int[numVerts];
      
      for (int col = 0; col < numVerts; col++)
         oneRow[col] = adjMatrix[row][col];
         
      return oneRow;   
   } 
     


   public void setWeight (int row, int col, int edgeWeight) {
         adjMatrix[row][col] = edgeWeight;
   } 
 

   public int nextneighbor(int v) {
      next[v] = next[v] + 1; 				

      if(next[v] < numVerts){
      	while(adjMatrix[v][next[v]] == 0 && next[v] < numVerts) {
            next[v] = next[v] + 1;                        

            if(next[v] == numVerts)
         	   break;
         }
     }

      if(next[v] >= numVerts) {
         next[v] = -1;                                    
         current_edge_weight = -1;
      }
      else 
         current_edge_weight = adjMatrix[v][next[v]];

      return next[v];      				
   } 

 
   public void resetnext()
   {
      for (int i=0; i < numVerts; i++)	// reset the array next to all -1's
         next[i] = -1;

   }
}
