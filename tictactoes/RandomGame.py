from Const import Const
from Move import Move
from State import State
from RandomAgent import RandomAgent

class RandomGame:
    def __init__(self):
        self._state = State()
        self._agentX = RandomAgent()
        self._agentO = RandomAgent()

    def turn(self):
        if self._state.getState() == Const.STATE_TURN_O:
            move = self._agentO.move(self._state)
            move.play(self._state)
        elif self._state.getState() == Const.STATE_TURN_X:
            move = self._agentX.move(self._state)
            move.play(self._state)

    def play(self):
        while self._state.getState() == Const.STATE_TURN_O or \
              self._state.getState() == Const.STATE_TURN_X:
            self.turn()
        print("game over")
        if self._state.getState() == Const.STATE_WIN_O:
            print("o won")
        if self._state.getState() == Const.STATE_WIN_X:
            print("x won")
        if self._state.getState() == Const.STATE_DRAW:
            print("draw")
        
if __name__ == '__main__':
    game = RandomGame()
    game.play()

