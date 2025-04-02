#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <windows.h>

#define WIDTH 10
#define HEIGHT 20

char board[HEIGHT][WIDTH];
int gameOver;

void initBoard() {
    for (int i = 0; i < HEIGHT; i++) {
        for (int j = 0; j < WIDTH; j++) {
            board[i][j] = ' ';
        }
    }
}

void drawBoard() {
    system("cls");
    for (int i = 0; i < HEIGHT; i++) {
        printf("|");
        for (int j = 0; j < WIDTH; j++) {
            printf("%c", board[i][j]);
        }
        printf("|\n");
    }
    printf("------------\n");
}

void dropPiece() {
    int x = WIDTH / 2;
    int y = 0;
    while (y < HEIGHT - 1 && board[y + 1][x] == ' ') {
        board[y][x] = ' ';
        y++;
        board[y][x] = 'X';
        drawBoard();
        Sleep(200);
    }
}

void input() {
    if (_kbhit()) {
        switch (_getch()) {
            case 'q': gameOver = 1; break;
        }
    }
}

int main() {
    gameOver = 0;
    initBoard();
    while (!gameOver) {
        dropPiece();
        input();
    }
    printf("Game Over!\n");
    return 0;
}