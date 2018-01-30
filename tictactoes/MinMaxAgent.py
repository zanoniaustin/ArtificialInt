import random
from Const import Const
from Move import Move
from Game import Game

class MinMaxAgent:
    def __init__(self, side):
        if side != Const.MARK_O and side != Const.MARK_X:
            raise ValueError("side must be MARK_X or MARK_O")
        self.side = side

    def value(self,game):
        ans = None
        state = game.getState()
        if state == Const.STATE_WIN_O:
            if self.side == Const.MARK_O: ans = 1
            else: ans = -1
        elif state == Const.STATE_WIN_X:
            if self.side == Const.MARK_X: ans = 1
            else: ans = -1
        elif state == Const.STATE_DRAW:
            ans = 0

        if ans != None: return (ans,0)

        iside = 0
        if self.side == Const.MARK_O: iside = 1
        else: iside = -1

        iturn = 0
        if state == Const.STATE_TURN_O: iturn = 1
        else: iturn = -1

        myTurn = (iside == iturn)
        myOptions = 0

        for move in game.getMoves():
            move.play(game)
            (moveValue,moveOptions)=self.value(game)
            move.unplay(game)
            myOptions = myOptions + 1 + moveOptions 
            if ans == None:
                ans = moveValue
            else:
                if myTurn:
                   ans = max(ans,moveValue)
                else:
                   ans = min(ans,moveValue)

        return (ans,myOptions)

    def move(self,game):
        (maxValue,maxOptions)=self.value(game)
        playable = []
        maxPlayableOption = 0
        for move in game.getMoves():
            move.play(game)
            (moveValue,moveOptions)=self.value(game)
            move.unplay(game)
            if moveValue == maxValue:
                playable.append((move,moveOptions))
                maxPlayableOption = max(maxPlayableOption,moveOptions)

        bestPlayable = []
        for (move,options) in playable:
            if options == maxPlayableOption:
                bestPlayable.append(move)
        
        spot=random.randint(0,len(bestPlayable)-1)
        return bestPlayable[spot]
