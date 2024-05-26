#include <iostream>
#include <vector> 
using namespace std;

// Find the candidate
// Move the candidate to the first availible tower to the right
// If not done go back to step 1 and repeat



int main(){
    vector<int> t[3];
    int n, candidate, to, from, near, far, move=0;
    //Where n represents the number of disks that the user will input
    //It's the smallest disk to move that has not been moved on the last move
    //To represents the tower to which we are moving the disk
    //From represents the first tower.
    //Near represents which tower we have to move all the disks too.
    //Far represents the tower that acts as our temperary disk holder
    //Move simply counts the number of moves before we successfully have completed our goal

    cout << "Enter the number of disks " << endl;
    cin >> n;
    cout << endl;


    //Initializing the three towers we basically move the n disks to their respective places

for(int i=n+1; i>=1; i--) t[0].push_back(i); //Keep in mind the order! The larger number should be at the bottom
// n+1 is the sentinel value
t[1].push_back(n+1);
t[2].push_back(n+1);


//Now we design the logic for whether the number of disks are even or odd. 
//After drawing the towers we learn that if we have even number of rings
//Then the tower that is furtherest from tower 1 is where all the disks are compiled
//Whereas if its odd then the closest one.

if(n%2==0){
    near = 2;
    far = 1; 
}
else {
    near =1; 
    far =2; 
}
// Now we declare the values for all the variables.
from = 0, to = near, candidate =1; 
/*Notice: 
    The reason why we have to = near is because above we established in the if 
    condition that if it has even rings then our near is the furthest one.*/

while (t[1].size() <n+1) {
    //Keep running it until we reach the sentinel value in our first tower signifying we ran out of disks
    cout << "Move number" << ++move << " : Transfer ring " << candidate << " from tower " << char(from+65) << " to tower " << char (to+65) << endl;
    

    //Now the important part: Logic for moving the rings
    t[to].push_back(t[from].back()); //Move a disk "from" tower to "to" tower
    t[from].pop_back(); //get rid of that disk in the "from" tower.

    //Now the condition to finding the candidate. 

    //This checks if the 3rd towers top value is less than tower 2nd top value
    //then we make "from" to be the tower with less value
    if(t[(to+1)%3].back() < t[(to+2) %3].back()) from = (to+1)%3;
    else from = (to+2)%3;
    
    //We take that towers value and that is our new candidate
    candidate = t[from].back();

    if(t[(from + near) % 3].back() > candidate) to= (from + near)%3;
    else to = (from + far) % 3;
    }

    return 0;

}
