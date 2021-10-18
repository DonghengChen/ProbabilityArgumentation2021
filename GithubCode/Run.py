##
from basicTest import*
#import pandas
##
import pickle
def save(obj):
    with open("C:/data.pkl",'wb') as f:
        pickle.dump(obj,f)
def load():
    with open("C:/data.pkl",'rb') as f:
        return pickle.load(f)
def ini():
    form=[]
    for i in range(6):
        form.append([0]*31)
    save(form)



##
#边密度：0.5~3 (0.5)一档
#节点数：10-30 （1） 一档
form=load()


for j in range(28,29):
    i=0
    rate = i * 0.5 + 0.5
    if j<=20:
        result = speedtest(j,rate,100)
    elif j<=25:
        result = speedtest(j,rate,50)
    else:
        result = speedtest(j,rate,20)
    #分三级
    form[i][j]=result
    print(i,j)
    save(form)
