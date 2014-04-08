from random import shuffle

class Node(object):
    value = None
    left = None
    right = None

    def __init__(self, val):
        if type(val) is list:
            for e in val:
                self.insert(e)
        elif type(val) is int:
            self.value = val
        else:
            raise Exception

    def insert(self, val):
        if not self.value:
            self.value = val
        elif val <= self.value:
            if self.left:
                self.left.insert(val)
            else:
                self.left = Node(val)
        else:
            if self.right:
                self.right.insert(val)
            else:
                self.right = Node(val)

    def get_sorted(self, sofar=[]):
        if self.left: sofar = self.left.get_sorted(sofar)
        sofar += [self.value,]
        if self.right: sofar = self.right.get_sorted(sofar)
        return sofar

    def __repr__(self):
        return str(self.value)
        

def seq(length):
    res = []
    for x in range(0, length):
        res.append(x)
    shuffle(res)
    return res
