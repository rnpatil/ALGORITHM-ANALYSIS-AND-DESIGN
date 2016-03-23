

import java.util.Random;

class Quicksort
{
	
    public void quickSort(int[] a, int p, int r)
    {
        if(p<r)
        {
            int q = Partition(a, p, r);
            
         
            quickSort(a, p, q-1);
            quickSort(a, q+1, r);
        }
     }

    /*
     *  7.2 b
     *   quickSortDash  which calls PartitionDash 
     *	Part 2
     *  ModifiedquickSortDash makes a  call to randomPartitionDash which in then calls PartitionDash 
     *  
     *  
		Modify the PARTITION procedure to produce a procedure
		PARTITION'(A; p; r) which permutes the elements of A[p...r] and returns two
		indices q and t, where p <= q <= t <= r, such that
		
		all elements of A[q..t] are equal,
		each element of A[p..q-1] is less than A[q],
		each element of A[t+1...r] is greater than A[q]
     *  
     *  
     *  
     *  
    */
    
    public void quickSortDash(int[] a, int p, int r)
    {
        if(p<r)
        {
        	int n[]  = PartitionDash(a, p, r);
            
        	quickSortDash(a, p, n[0]-1);
        	quickSortDash(a, n[1]+1, r);
        }
     }
    
    
    /*
     *  7.2 c 
     *
     *	Part 2
     *  ModifiedquickSortDash makes a  call to randomPartitionDash which in then calls PartitionDash 
    */
    
    public void ModifiedquickSortDash(int[] a, int p, int r)
    {
     
       	if(p<r)
            {
                int n[]  = randomPartitionDash(a, p, r);
              
                ModifiedquickSortDash(a, p, n[0]-1);
                ModifiedquickSortDash(a, n[1]+1, r);
            }
     }
    
    
    public void RandomQuickSort(int[] a, int p, int r)
    {
        if(p<r)
        {
            int q  = randomPartition(a, p, r);
      
            RandomQuickSort(a, p, q-1);
            RandomQuickSort(a, q+1, r);
        }
     }
    
    
    /*
     *  7.2 c 
     *
     *	Part 1
     *  RandomQuickSortDash makes a  call to randomPartitionDash which in then calls PartitionDash 
    */
    
    public void RandomQuickSortDash(int[] a, int p, int r)
    {
        if(p<r)
        {
            int n[]  = randomPartitionDash(a, p, r);
          
            RandomQuickSortDash(a, p, n[0]-1);
            RandomQuickSortDash(a, n[1]+1, r);
        }
     }
    
    
     private int Partition(int[] a, int p, int r)
     {
         int x = a[r];

         int i = p-1;
         
         for(int j=p; j<r; j++)
         {
             if(a[j]<=x)
             {
                 i++;
                 Swap(a,i,j);
             }
         }

         Swap(a,i+1,r);

         return (i+1);
     }
     
     
     private int[] PartitionDash(int[] a, int p, int r)
     {
         int x = a[r];

         int q = p-1;
         int t = q;
       
         for(int j=p; j<r; j++)
         {
        	 
        	  if(a[j]==x)
             {
            	 q++;
                 Swap(a,q,j); 
             }
        	  else if(a[j]<x)
             {
                 q++;
                 Swap(a,q,j);
                 t++;
                 Swap(a,q,t);
                 
             }
            
         }
         
        Swap(a,q+1,r);  
              
       
         return new int[] {q+1, t+1};
     }

     
     private int randomPartition(int[] a, int p, int r)
     {
    	  Random rand = new Random();
    	  int i = rand.nextInt((r - p) + 1) + p;
  
    	    Swap(a,i,r);
    	     
    	    return Partition(a, p,r);
     }
     
     
     private int[] randomPartitionDash(int[] a, int p, int r)
     {
    	  Random rand = new Random();
    	  int i = rand.nextInt((r - p) + 1) + p;
  
    	    Swap(a,i,r);
    	     
    	    return PartitionDash(a, p,r);
     }
    
    


     public static void main(String[] args)
     {
         Quicksort quickSort = new Quicksort();
         int[] a = {8, 4, 8, 3, 1, 9, 8, 10, 8, 7};

         System.out.print("Original  Array A : \n");
         
         printlist(a);
         
         int lengthA = a.length;

         quickSort.quickSortDash(a, 0, lengthA-1);

         System.out.print("7.2 b) Sorted  Array A using Quick SortDash : \n");
         printlist(a);
         
         int[] b = {8, 4, 8, 3, 1, 9, 8,};
         
         System.out.print("Original  Array B : \n");
         
         printlist(b);
         int lengthB = b.length;

         quickSort.RandomQuickSortDash(b, 0, lengthB-1);
         System.out.print("7.2 c Part1) Sorted  Array B using  RandomQuickSortDash : \n");
         printlist(b);
         
         
         int[] c = {8, 4, 8, 2, 3, 8, 1, 9, 8,};
         
         System.out.print("Original  Array C : \n");
         
         printlist(c);
         int lengthC = c.length;

         quickSort.ModifiedquickSortDash(c, 0, lengthC-1);
         System.out.print("7.2 c Part2) Sorted  Array B using  ModifiedquickSortDash : \n");
         printlist(c);
     }
     
     
     public static void Swap (int A[], int a, int b)
     {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
     }
     
     public static void printlist (int a[])
     {
    	 for(int i=0; i<a.length;i++)
         {
             System.out.print(a[i] + " ");
         }
    	 System.out.print("\n");
     }
}