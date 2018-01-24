import unittest
from Const import Const
from State import State

class TestState(unittest.TestCase):
    def testConstruct(self):
        unit = State()
        self.assertEqual(unit.getState(),Const.STATE_TURN_O)

    def testXWin(self):
        unit = State()
        unit.play("oa2 xa1 oa3 xb1 ob2 xc1")
        self.assertEqual(unit.getState(),Const.STATE_WIN_X)

    def testOWin(self):
        unit = State()
        unit.play("oc1 xa2 oa1 xa3 ob1")
        self.assertEqual(unit.getState(),Const.STATE_WIN_O)

    def testDraw(self):
        unit = State()
        unit.play("oa1 xa2 ob2 xc3 oc2 xa3 ob3 xb1 oc1")
        self.assertEqual(unit.getState(),Const.STATE_DRAW)
if __name__ == '__main__':
    unittest.main()
