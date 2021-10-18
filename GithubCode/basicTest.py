#一次测试
from main import*
import time
import eventlet
#需要修改路径
command=''
location='C:/graph.txt'


def pretest(G,P,E):
    start=time.time()
    a=preferredSolver(G,P,E)
    end=time.time()
    #print(a)
    #print(a,end-start)
    return end-start
def comtest(G,P,E):
    start=time.time()
    a=completeSolver(G,P,E)
    end=time.time()

    #print(a,end-start)
    return end-start

def Isize(G,E):
    Gset = G.copy()
    Gnot, I = divisionOfI(Gset, E)
    liaoI = len(I.nodes)
    iterRemove(I, Gnot)
    subsgraphs = weakDecompose(I)
    newIs=[]
    for item in subsgraphs:
        newIs.append(len(item))
    if len(newIs)>0:
        return liaoI,max(newIs)
    else:
        return liaoI,0


import JavaAPI
def speedtest(size=30,density=3,times=1,liaoOn=True):

    #test()
    liaoTime=[]
    newTime=[]
    liaoI=[]
    newI=[]

    for i in range(times):
        print(i,end=' ')
        rotate = True
        while rotate:
            try:
                G,P,E=graphGenerator(size,density)
            except:
                rotate=True
            else:
                rotate=False
        #图生成
        G2=G.copy()
        temp=Isize(G,E)
        liaoI.append(temp[0]);newI.append(temp[1])

        timeoutRec={'Liao':0,'New':0,'mem':0}
        timeout=True
        eventlet.monkey_patch()  # 超时控制器
        if liaoOn:
            with eventlet.Timeout(60,False):
                liao = JavaAPI.java(command,location)
                liao.load(G, P, E)
                result=liao.test()
                if(result):
                    liaoTime.append(result[1]/1000)
                else:
                    timeoutRec['mem'] += 1
                #print(result[0])
                timeout=False
            if timeout:
                timeoutRec['Liao']+=1
            timeout=True
        with eventlet.Timeout(60, False):
            t1=pretest(G,P,E)
            newTime.append(t1)
            timeout=False
        if timeout:
            timeoutRec['New']+=1

    #print("pre avg time:",sum(tlist)/len(tlist))
    #print("com avg time:",sum(tlist2)/len(tlist2))
    avgLiao=0;avgNew=0

    mean=lambda x:sum(x)/len(x)

    if len(liaoTime)>0:
        avgLiao=mean(liaoTime)
    if len(newTime)>0:
        avgNew=mean(newTime)



    if timeoutRec['mem']==0:
        return avgLiao,avgNew,timeoutRec['Liao'],timeoutRec['New'],max(liaoI),mean(liaoI),max(newI),mean(newI),liaoTime,newTime
    else:
        return avgLiao, avgNew, timeoutRec['Liao'], timeoutRec['New'], max(liaoI), mean(liaoI), max(newI), mean(newI),timeoutRec['mem'],liaoTime,newTime





