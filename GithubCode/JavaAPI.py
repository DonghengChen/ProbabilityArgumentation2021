import os
class java:
    def __init__(self,cm,dp):
        self.command=cm
        self.dataPath=dp
    def load(self,G,P,E):
        self.data=[len(P)]
        for i in range(len(P)):
            self.data.append(P[i])
        self.data.append(len(E))#外延
        self.data.extend(E)
        self.data.append(len(G.edges))
        #循环加入边
        for item in G.edges:
            self.data.append(item[0])
            self.data.append(item[1])
        self.graph=''
        for item in self.data:
            self.graph += str(item) + "\n"
        with open(self.dataPath, 'w') as f:
            f.write(self.graph)
    def test(self):
        cmd=self.command+' < '+self.dataPath
        window=os.popen(cmd)
        try:
            result = window.readlines()
            for i in range(2):
                result[i]=float(result[i])
        except:
            return False
        else:
            return result

