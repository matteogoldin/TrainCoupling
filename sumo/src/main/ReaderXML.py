'''
Created on 8 mag 2023

@author: matte
'''
from bs4 import BeautifulSoup

if __name__ == '__main__':
    with open('../sumo/trafficInfo.xml', 'r') as f:
        data = f.read()
        bs_data = BeautifulSoup(data, "xml");
        tInfo = bs_data.find_all("tripinfo")
        for t in tInfo:
            name = t.get("id")
            duration = t.get("duration")
            duration_min = round((float(duration)/60), 1)
            print(name + ": " + str(duration_min) + " min\n")