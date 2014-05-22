from random import sample
import copy
from sys import stdout
write = stdout.write

class TSP_Map(object):

    def __init__(self, width, height, no_of_nodes):
        self.width = width
        self.height = height
        self.tsp_map = []
        
        possible = width * height
        node_list = sample(range(possible), no_of_nodes)
        for e in node_list:
            x = e % width
            y = e // width
            self.tsp_map.append((x, y))


    def write_as_csv(self):
        write(str(self.tsp_map[0][0]) + ',' + str(self.tsp_map[0][1]))
        for e in self.tsp_map[1:]:
            write(',' + str(e[0]) + ',' + str(e[1]))
