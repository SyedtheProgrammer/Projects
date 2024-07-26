#include<iostream>
#include <cstdlib>
using namespace std;
char player1=' ';
char player2=' ';
char x[3][3]={{'1','2','3'},{'4','5','6'},{'7','8','9'}};
void print(){
    for (int i=0; i<3;i++){
        for(int j=0;j<3;j++){
            cout << "___" <<x[i][j] << "___|";
        }
        cout << endl;
    }
}
bool win(){
    for (int r=0;r<3;r++){
        //checks rows for winner
        if(x[r][0]==x[r][1] && x[r][0]==x[r][2]){
            cout << "Winner " << x[r][0] << endl;
            return true;}
        //checks columns for winner
        else if (x[0][r]==x[1][r] && x[0][r]==x[2][r]){ 
            cout << "Winner " << x[1][r] << endl;
            return true;}
        //checks diagonal -x
        else if(x[0][0]==x[1][1] && x[0][0]==x[2][2]){
            cout << "Winner " << x[2][2] << endl;
            return true;} 
        else if (x[0][2]==x[1][1] && x[0][2]==x[2][0]){
            cout << "Winner " << x[0][2] << endl;
            return true;}
    }
        return false;    
        }

void empty_position(int &row, int &column){
    while(x[row-1][column-1]=='X'||x[row-1][column-1]=='O'){
        cout << "Spot already taken. Try again (row and column): ";
        cin >> row >> column;
    }
    }

void turn(){
int row,column;
    for(int turns=1;turns<10;turns++){
        if(win()==true)return;
        if(turns%2!=0){
            cout << "Player 1 turn, Enter  row and column: ";
            cin >> row >> column;
            while(row>3 || column>3 || row < 1 || column<1){
                cout << "Incorrect input please select row and column in the range 1-3: ";
                cin >> row >> column;
            }
            empty_position(row,column);
            x[row-1][column-1]=player1;

        }
        else {
            cout << "Player 2 turn: Enter row and column: ";
            cin >> row >> column;
            empty_position(row,column);
            x[row-1][column-1]=player2;
    }
    print();
    }
    cout << "No winner " << endl;
}

int main(){
    //Get players symbols.
    cout<<"Enter the symbol for player1 (choose X or O): ";
    cin >> player1;
    while(player1!='X' && player1!='O'){
        cout << "Invalid input.Put in X or O: ";
        cin >> player1;
    }
    if (player1 =='X') player2='O';
    else {player2='X';}

    print();
    turn();//The user places symbols
  
}

