import java.util.Random;

 public class Scrambler
 {
     public static String scramble()
     {
        Scramble scramble = new Scramble();
        scramble.getScramble();
        return scramble.toString();
     } 
 }
 
 class Scramble
 {
    //Creating a random object
    static Random r = new Random();

    //Notations
    String[] rights = {"R","R'","R2","R2"}; //1
    String[] ups = {"U","U'","U2","U2"}; //2
    String[] fronts = {"F","F'","F2","F2"}; //3
    String[] lefts = {"L","L'","L2","L2"}; //4
    String[] downs = {"D","D'","D2","D2"}; //5
    String[] backs = {"B","B'","B2","B2"}; //6
    String[][] notations = {rights, ups, fronts, lefts, downs, backs};

    //Delcaring last faces
    int lastFace = 0;
    int beforeLastFace = 0;

    //Declaring Scramble Size
    String[] scramble = new String[25];

    public void getScramble()
    {
        //Loops methods for random notation generation for each index of the scramble array
        for(int i=0; i < scramble.length; i++)
        {
            scramble[i] = getNotation();
        }
    } 

    public String getNotation()
    {
        //Adding 1 to get index to match our "remainder" logic
        int notIndex = r.nextInt(notations.length) + 1;
        int rNot = r.nextInt(rights.length);

        notIndex = getFace(notIndex);

        return notations[notIndex - 1][rNot];
    }
    public int getFace(int x)
    {
        while((x == beforeLastFace && x%3 == lastFace%3) || x == lastFace)
        {
            x = r.nextInt(notations.length) + 1;
        }

        beforeLastFace = lastFace;
        lastFace = x;

        return x;
    }
    
    public String toString()
    {
        String scram = "";
         for(int k = 0; k < scramble.length; k++)
         {
             scram += scramble[k] + " ";
         }
         return scram;
    }
 }