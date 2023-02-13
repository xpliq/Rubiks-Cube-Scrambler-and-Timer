# Rubiks-Cube-Scrambler-and-Timer
Program for generating random Rubik's Cube Scrambles as well as a timer functionality to track your solving speeds.

SCRAMBLE LOGIC

 --==+Repetition Rules+==--
 
 Notation can't repeat twice
  
 When a notation is generated. We assign it as the "face"
 
       - This means that this "face" is going to have a special property
       - As a "face" piece is moved, it's adjacent notations can be moved without creating a "pointless" move.
           -This means, if 'R' was a move, U, D, F, and B are all valid moves. L is not, as it's opposite of R's "face"
       - Once the "face" has been declared, the opposite notation can not be called, as it will be the "back" of that generated notation 
  
   "face" Correspondance
   
    R <-> L
    U <-> D
    F <-> B
  
  Examples:
  
       R U R' - valid b/c
           -R is the face
           -U is adjacent to R
           -R' can be repeated as a result
  
       R L R' - invalid b/c
           -R is the face
           -L is the opposite/"back" of 'R'
           -R' therefore can not be repeated as a result
  
  --==+Determining the Face+==--
  Below I have a table of all "remainder" operations from 1-6. Credit to mrxuso for the logic
  1 2 3 4 5 6
  0 0 0 0 0 0 - %1
  1 0 1 0 1 0 - %2
  1 2 0 1 2 0 - %3 *We will use this
  1 2 3 0 1 2 - %4
  1 2 3 4 0 1 - %5
  1 2 3 4 5 0 - %6
  We use %3 because there are enough unique combonations to each "face" and "back".
  This means we have to organize our notations in a specific order
  
  --Notation Order--
       1 - Rights
       2 - Ups
       3 - Fronts
       4 - Lefts
       5 - Downs
       6 - Backs
  Using this notation order, we can use the %3 method to compare the values for no repitition 
  
  In Cubing Terms
  
       If we were to rotate a side on a cube, and follow that up with a move that is adjacent to the cube, it is safe to
       rotate that same side again because that side is now in a different state than what is was originally. The reason
       we can't continue the scramble if the opposite notation was followed is because, you would be performing an
       unnecessary notation. For example, (R L R2) is the same as (R' L). Therefore, a legal/logical scramble would have
       to consider the potential of these pointless turns.
  
  --==+Notations+==--
  
   //N2 will be added twice since it's probability is more likely than the rest
   
   rights : R, R', R2, R2
   lefts : L, L', L2, L2
   ups : U, U', U2, U2
   downs : D, D', D2, D2
   fronts : F, F', F2, F2
   backs : B, B', B2, B2
  
  
  --==+Algorithm+==--
  
  1. Call for a new scramble with declared length
  2. Declare scramble array with default length 25
  3. Declare notations within a 2D array.
  4. We run getNotation method on a loop at each scramble index to fill scramble
  5. getNotation will generate two values, one value is the random notation index, and the other is the notation type
  6. The notation type will determine whether the notation is plain, a double turn, or a prime notation
  7. The notation index, which will choose whether its right, left, etc, will be passed into the getFace method
  8. The get face method runs a check on whether or not the notation is valid for the index position it is at
  9. If the generated notation index is the same as the last, we randomize it again
   9a. If the generated notation index is the same as the one before, we check if the last one is it's counterpart
   9b. While the generated notation index meets the criteria of 9 and 9a, we continue to randomize until conditions are false.
  10. Next we define that index to variables that will keep track of what index was last and before the last.
  11. After the notation index is returned with a legal value, we store return it to getNotation, where it fills the scramble at the respective index
  12. Lastly we run the scramble through the getScramble method, and print it with the toString
