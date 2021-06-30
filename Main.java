import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("What is your seed?");
        long seed = scanner.nextInt();
        
        System.out.println("\nHow many chunks wide on the X axis are you trying to find?");
        int xChunks = scanner.nextInt();
        
        System.out.println("\nHow many chunks wide on the Z axis are you trying to find?");
        int zChunks = scanner.nextInt();
        
        System.out.println("\nWhat is the X chunk coordinate of the place you are first searching from?");
        int x1 = scanner.nextInt();
        
        System.out.println("\nWhat is the Z chunk coordinate of the place you are first searching from?");
        int z1 = scanner.nextInt();
        
        System.out.println("\nWhat is the X chunk coordinate of the place you are ending you search from?");
        int x2 = scanner.nextInt();
        
        System.out.println("\nWhat is the Z chunk coordinate of the place you are ending you search from?");
        int z2 = scanner.nextInt();
        
        int[] xInOrder = {x1, x2}, zInOrder = {z1, z2};
        
        Arrays.sort(xInOrder);
        Arrays.sort(zInOrder);
        
        x1 = xInOrder[0];
        z1 = zInOrder[0];
        x2 = xInOrder[1];
        z2 = zInOrder[1];
        
        boolean[][] arr = new boolean[zChunks][xChunks];
        
        int xPos = x1, zPos = z1;
        
        System.out.println("\nSearching...");
        
        while(true)
        {
            for(int a = 0; a < zChunks; a++)
            {
                for(int b = 0; b < xChunks; b++)
                {
                    arr[a][b] = new Random(seed + (int) ((xPos + b) * (xPos + b) * 0x4c1906) + (int) ((xPos + b) * 0x5ac0db) + (int) ((zPos + a) * (zPos + a)) * 0x4307a7L + (int) ((zPos + a) * 0x5f24f) ^ 0x3ad8025fL).nextInt(10) == 0;
                }
            }
            
            if(!Arrays.deepToString(arr).contains("false"))
            {
                System.out.println("\n" + xChunks + "x" + zChunks + " slime chunk found from chunk coordinates (" + xPos + ", " + zPos + ") to (" + (xPos + xChunks) + ", " + (zPos + zChunks) + ") on the seed " + seed + ".");
                break;
            }
            
            arr = new boolean[zChunks][xChunks];
            
            if(xPos == x2 && zPos == z2)
            {
                System.out.println("\nNo " + xChunks + "x" + zChunks + " slime chunks found from (" + x1 + ", " + z1 + ") to (" + x2 + ", " + z2 + ") on the seed " + seed + ".");
                break;
            }
            
            if(xPos == x2)
            {
                xPos = x1;
                zPos++;
            }
            
            xPos++;
        }
    }
}
