##
#import pandas as pd
import pickle
def load():
    with open("C:/data.pkl",'rb') as f:
        return pickle.load(f)
dt=load()
##
import math
print2=lambda x:print(x,end='',sep='')
def print3(x):
    print2(',')
    print2('$')
    if x==0:
        print2('<10^{-5}$')
        return
    base=math.log(x,10)
    base=math.floor(base)
    x=x/(10**base)
    x=round(x,2)
    print2(x)
    print2("\\times 10^{")
    print2(base)
    print2('}$')



##
# avgLiao,avgNew,timeoutRec['Liao'],timeoutRec['New'],max(liaoI),mean(liaoI),max(newI),mean(newI),liaoTime,newTime


print2('rate\\nodes')
for j,item in enumerate(dt[0]):
    if type(item)==type((1,2)):
        print2(',');print2(j)
print()
for i,line in enumerate(dt):
    print2(0.5+0.5*i)
    for j,item in enumerate(line):
        if type(item)==type((1,2)):
            print3(item[0])
    print()
    for j,item in enumerate(line):
        if type(item)==type((1,2)):
            print3(item[1])
    print()
##
r=dt[3][10:31]
telist=[]
for j in range(6):
    r=dt[j][10:31]
    for i,item in enumerate(r):
        old=math.log(item[0],10)
        new=math.log(item[1],10)
        print2('(')
        print2(i+10)
        print2(',')
        print2(new)
        telist.append(old)
        print(')')
print(sum(telist)/len(telist))
#画时间的
##
r=dt[3][10:31]
for i,item in enumerate(r):
    #old=math.log(item[5],10)
   # new=math.log(item[7],10)
    print2('(')
    print2(i+10)
    print2(',')
    if len(item)>9:
        print(1-len(item[-2])/20,end='')
    #print2(old)
    print(')')