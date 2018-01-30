import random
from Const import Const
from Move import Move
from State import State

class RandomAgent:
    def __init__(self):
        pass

    def move(self,state):
        mark = None
        if state.getState() == Const.STATE_TURN_O:
            mark = Const.MARK_O
        if state.getState() == Const.STATE_TURN_X:
            mark = Const.MARK_X
        if mark == None:
            raise ValueError("state must be playable")
        board = state.getBoard()
        playable = []
        for row in range(Const.ROWS):
            for col in range(Const.COLS):
                if board[row][col] == Const.MARK_NONE:
                    playable.append([row,col])
        spot=random.randint(0,len(playable)-1)
        return Move(playable[spot][0],playable[spot][1],mark)


    def moveSmart(self, state):
        mark = None
        if state.getState() == Const.STATE_TURN_O:
            mark = Const.MARK_O
        if state.getState() == Const.STATE_TURN_X:
            mark = Const.MARK_X
        if mark == None:
            raise ValueError("state must be playable")
        board = state.getBoard()
        playable = []
        victory = False
        for row in range(Const.ROWS):
            for col in range(Const.COLS):
                if board[row][col] == Const.MARK_NONE:
                    playable.append([row,col])
                    state.move(row, col, mark)
                    if state.getState() == STATE_WIN_X:
                        victory == True
                    state.unmove(row, col)
        #Spot is just a number how does it keep track of the row and col
        
        spot=random.randint(0,len(playable)-1)
        return Move(playable[spot][0],playable[spot][1],mark)
