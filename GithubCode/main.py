import networkx as nx   #导入networkx包
import random			#导入random包
import numpy as np      #导入numpy包
def graphGenerator(nodes,ratio,extension=3):#创图，概率，选extension
    G=nx.DiGraph()
    G.add_nodes_from(list(range(nodes)))#新建[0,1,..n-1]的随机图
    edgeSet=set()#随机生成边集
    while(len(edgeSet)<=nodes*ratio):#循环随机生成边（无自攻击）
        x1 = random.randint(0, nodes-1)
        x2 = random.randint(0, nodes-1)
        if(x1!=x2):
            edgeSet.add((x1,x2))
    for item in edgeSet:
        G.add_edge(item[0],item[1])

    P={}#指派随机概率
    for i in range(nodes):
        P[i]=random.random()

    #选Extension
    X=G.copy()
    E=set()
    for i in range(extension):#循环选extension

        e=random.choice(list(X.nodes))
        E.add(e)
        del1=list(X.pred[e])#去除父母
        del2=list(X.succ[e])#去除孩子
        X.remove_node(e)#除去自己
        X.remove_nodes_from(del1+del2)
    return G,P,E #返回图G,概率P和无冲突外延E

def Eplus(G,E):
    e_plus=set()
    for item in E:
        e_plus.update(G.succ[item])#把全部a+放入E+中
    return e_plus
def Eminus(G,E):
    e_minus=set()
    for item in E:
        e_minus.update(G.pred[item])
    return e_minus

def divisionOfI(G,E):#将G分解成I,E-\E+,E+,其中E+不需要计算概率，抛弃
    ep=Eplus(G,E)
    em=Eminus(G,E)
    Gnot=em-ep
    G.remove_nodes_from(E|ep|em)
    return Gnot,G

def iterRemove(G:nx.DiGraph,Gnot:set):#迭代去除所有入度为0的点，并加入Gnot中
    flag=True #标记，一轮循环有入度为0的点
    while flag:
        flag=False #假设已经分解完毕
        for item in G.nodes:
            if len(G.succ[item])==0:
                Gnot.add(item)
                G.remove_node(item)
                flag=True
                break

def weakDecompose(G):#弱联通分支分解
    result=nx.weakly_connected_components(G)
    return list(result)

def zeroDegreeCk(G,g):#检查子集g是否有入度为0的点
    for item in g:
        if len(G.succ[item])==0:
            return False
    return True

def powerSet(nodes):#给定节点，生成幂集
    N = len(nodes)
    nodes=list(nodes)
    res = []
    for i in range(2 ** N):
        temp = set()
        for j in range(N):
            if (i>>j)%2:
                temp.add(nodes[-j])
        res.append(temp)
    return res
def probability(P,pos,neg):#计算正反概率
    result=1
    for item in pos:
        result*=P[item]
    for item in neg:
        result*=1-P[item]
    return result

def admCheck(G):#用来检测是否有可相容外延的临时函数
    waitList=powerSet(G.nodes)
    for item in waitList:
        if Eminus(G,item).issubset(Eplus(G,item)):
            return True
    return False


def SCCsrc(G):#本代码针对可相容外延特化，假设没有大小为1的SCC分支
    waitlist=list(nx.strongly_connected_components(G))
    waitlist2=[]
    result=[]
    for item in waitlist:
        if(len(item))!=1:
            waitlist2.append(item)
    if(len(waitlist2)==1):
        return waitlist2
    else:
        for item in waitlist2:
            if Eminus(G,item).issubset(item):
                result.append(item)
    return result

def preCheck(G):#用来检测是否有非空优先外延
    waitList=SCCsrc(G)
    for item in waitList:
        if admCheck(G):
            return True
    return False


def completeSolver(G,P,E):
    Gnot,I=divisionOfI(G,E)
    iterRemove(I,Gnot)
    #计算剩余I的概率
    subgraphs=weakDecompose(I)
    subsPro=1#弱分解，乘法
    for graph in subgraphs:
        graphPro=0#内部加法
        Inow=I.subgraph(graph)#每一个弱联通分支是Inow
        neg=set(Inow.nodes)
        #print(len(neg))
        for item in powerSet(graph):
            temp=Inow.subgraph(item)
            if(zeroDegreeCk(temp,temp.nodes)):
                graphPro+=probability(P,item,neg-item)
        subsPro*=graphPro#弱分解乘法
    #子图计算完毕，合并
    finalResult=probability(P,E,Gnot)*subsPro
    return finalResult

def preferredSolver(G,P,E):
    Gnot, I = divisionOfI(G, E)
    iterRemove(I, Gnot)
    # 计算剩余I的概率
    subgraphs = weakDecompose(I)
    subsPro = 1  # 弱分解，乘法
    for graph in subgraphs:
        graphPro = 0  # 内部加法
        Inow = I.subgraph(graph)  # 每一个弱联通分支是Inow
        neg = set(Inow.nodes)
        # print(len(neg))
        for item in powerSet(graph):
            temp = Inow.subgraph(item)
            if (zeroDegreeCk(temp, temp.nodes) and not preCheck(temp)):
                graphPro += probability(P, item, neg - item)
        subsPro *= graphPro  # 弱分解乘法
    # 子图计算完毕，合并
    finalResult = probability(P, E, Gnot) * subsPro
    return finalResult


















