# ProbabilityArgumentation2021


## 代码介绍与运行环境
这是论文《基于迭代分解的特征化概率论辩语义求解方法》的配套代码。运行改代码，你需要安装如下环境：(win平台）
- [Python 3（版本不限）]
- [Java 虚拟机（版本不限），安装时勾选配置环境变量]
- [PyCharm（可选） 方便修改python代码，其他编辑器亦可]
- [IntelliJ（可选） 方便运行java代码，如熟悉java调用格式可跳过]


## 安装python依赖类库
~~~
pip install networkx
~~~

## 求解实际概率论辩框架
如果并不想进行速度测试，仅仅是想通过本算法求解概率论辩框架的外延概率，则无需安装java及相关类库。可以直接运行下面的代码完成一个快速演示（需要下载main.py)
~~~
from main import*
G,P,E=graphGenerator(20,2)
print(completeSolver(G,P,E))#求解并打印完全外延概率
print(preferredSolver(G,P,E))#求解并打印优先外延概率
~~~
对于特定的论证图，请使用networkx构造出有向图G，节点名称为整数。节点概率为列表（list）P，P[i]代变节点i的概率。外延E请构造为整数的集合（set）。或者，可以使用main.py内建的样例生成器来完成测试。


如果你希望进行速度测试，请阅读下面的向导
## 修改代码中文件路径
你需要根据保存代码的目录，修改代码交叉调用的路径。在文件"basicTest.py"的第六行有
~~~
command=''
~~~
你需要根据配置的java环境中的路径，填写正确的command。以下给出一个示范
~~~
command=r'"C:\Program Files\Java\jdk-16.0.2\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2\lib\idea_rt.jar=62512:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2\bin" -Dfile.encoding=UTF-8 -classpath D:\ProbabilityJLC2016\ProbabilityJLC2016\src\probabilisticArg\out\production\probabilisticArg probabilisticArg.Main'
~~~
修改示范中的路径，包括java.exe的位置，IntelliJ的位置和存放代码的位置。如果你熟悉java，可以自行填写。
## 运行测试
~~~
python Run.py
~~~
该测试需要花费较长时间，具备自动保存功能。如需要终止，下次运行会自动从断点开始。如果想缩短测试时间，请修改如下几行
~~~
    if j<=20:
        result = speedtest(j,rate,100)
    elif j<=25:
        result = speedtest(j,rate,50)
    else:
        result = speedtest(j,rate,20)
~~~
为
~~~
    if j<=20:
        result = speedtest(j,rate,10)
    elif j<=25:
        result = speedtest(j,rate,5)
    else:
        result = speedtest(j,rate,2)
~~~
这样可以将测试用例减少为10%，极大提升测试速度。

