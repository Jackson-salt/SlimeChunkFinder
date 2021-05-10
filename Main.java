import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Main
{
    static boolean[][] arr;
    
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("What is your seed?");
        long seed = scanner.nextLong();
        
        System.out.println("\nHow many chunks wide on the X axis are you trying to find?");
        int x = scanner.nextInt();
        
        System.out.println("\nHow many chunks wide on the Z axis are you trying to find?");
        int z = scanner.nextInt();
        
        System.out.println("\nWhat is the X chunk coordinate of the place you are first searching from?");
        int x1 = scanner.nextInt();
        
        System.out.println("\nWhat is the Z chunk coordinate of the place you are first searching from?");
        int z1 = scanner.nextInt();
        
        System.out.println("\nWhat is the X chunk coordinate of the place you are ending you search from?");
        int x2 = scanner.nextInt();
        
        System.out.println("\nWhat is the Z chunk coordinate of the place you are ending you search from?");
        int z2 = scanner.nextInt();
        
        int[] xInOrder = {x1, x2};
        int[] zInOrder = {z1, z2};
        
        Arrays.sort(xInOrder);
        Arrays.sort(zInOrder);
        
        x1 = xInOrder[0];
        z1 = zInOrder[0];
        x2 = xInOrder[1];
        z2 = zInOrder[1];
        
        arr = new boolean[z][x];
        
        int xPosition = 0;
        int zPosition = 0;
        int xPos = x1;
        int zPos = z1;
        
        System.out.println("\nSearching...\n");
        
        while(true)
        {
            for(int a = 0; a < arr.length; a++)
            {
                for(int b = 0; b < arr[0].length; b++)
                {
                    xPosition = xPos + b;
                    zPosition = zPos + a;
                    
                    Random ran = new Random(seed + (int) (xPosition * xPosition * 0x4c1906) + (int) (xPosition * 0x5ac0db) + (int) (zPosition * zPosition) * 0x4307a7L + (int) (zPosition * 0x5f24f) ^ 0x3ad8025fL);
                     
                    if(ran.nextInt(10) == 0)
                    {
                        arr[a][b] = true;
                    }
                }
            }
            
            if(allTrue())
            {
                System.out.println(x + "x" + z + " slime chunk found from chunk coordinates (" + xPos + ", " + zPos + ") to (" + (xPos + arr[0].length) + ", " + (zPos + arr.length) + ").");
                break;
            }
            
            arr = new boolean[z][x];
            
            if(xPos == x2 && zPos == z2)
            {
                System.out.println("No " + x + "x" + z + " slime chunks found from (" + x1 + ", " + z1 + ") to (" + x2 + ", " + z2 + ").");
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
    
    static boolean allTrue()
    {
        long i = 0;
        
        for(int a = 0; a < arr.length; a++)
        {
            for(int b = 0; b < arr[0].length; b++)
            {
                if(arr[a][b])
                {
                    i++;
                }
            }
        }
        
        return i == arr.length * arr[0].length;
    }
}