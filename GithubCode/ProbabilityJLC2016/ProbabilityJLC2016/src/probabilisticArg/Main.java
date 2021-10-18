
package probabilisticArg;

//import java.util.ArrayList;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//import java.util.Iterator;

/**
 *
 * @author beishui
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static int[][] attacks;
    public static HashSet Args=new HashSet();
    public static HashSet E=new HashSet();
    public static double[] P;
    public static int n;//有多少个论证
public static void main2(String[] args) {
   double rat =2.0;  //the ratio between the number of attacks and the number of arguments
   
 //  DecimalFormat    df   = new DecimalFormat("######0.00"); 
   
    for(int arg=10; arg<=10; arg=arg+5){  //argument number
      System.out.println("边密度: "+rat);
      System.out.println("节点数量: "+arg);
    
      int nonconf=0;
      int conf=0;
      long time1=0;
      long time2=0;
      long time3=0;
   
     // int exeed2=0; //times of exceed
      //int dexeed2=0; //times of exceed
    
      HashSet Args; //a set of arguments of AF
      Args = new HashSet(); //initiate
      
 
      int timeCount =1;
      while(timeCount<=5){ //times of test
      int argumentNumber=arg;
      double ratio=rat;
    
      int[][] attacks; 
      attacks = new int[argumentNumber+1][argumentNumber+1];
   
      Random rand=new Random();  //g
   
 //////////////////////generate argumentation framework///////////
  int x,count; //,count_sub;
  count =0; 
 // count_sub = 0;
  
    for(int t=1; t<=argumentNumber; t++){ //define a set of arguments
    Args.add(t);
}
  // System.out.println("arguments:"+Args);
  
  //typocal example 2
  // arguments:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

   
   //test begin
  while(count<argumentNumber*ratio){
  int m;
  m = argumentNumber+1;
  int x1 = rand.nextInt(m);
  int x2 = rand.nextInt(m);
  if(Args.contains(x1)&Args.contains(x2)){
   if(attacks[x1][x2]!=1){
      attacks[x1][x2]=1;
      
       count = count+1;
   //  System.out.println("attacks["+x1+"]["+x2+"]="+attacks[x1][x2]);
      
  }
  }

 }  

   //test end
   
HashSet[] Parents = new ParentsOfArgument().getParent(attacks, argumentNumber, Args);
HashSet[] Children = new ParentsOfArgument().getChildren(attacks, argumentNumber, Args);

HashSet S;
S = new HashSet();
//S.add(10);
S.add(1);
S.add(6);
//System.out.println("liao"); 

   
   double[] p;
   p = new double[argumentNumber+1]; //initialize probability
   
  for(int i=1; i<=argumentNumber;i++){
    p[i] = Math.random();
   
  }
  
  
 //  int succ=0;
 //  int dsucc =0;
     

//  sumatt = sumatt+count;  
  
 boolean cf=false; //check whether S is conflict
 HashSet S1 = new HashSet();
 HashSet S2 = new HashSet();
 //S1.addAll(S);
 S2.addAll(S);
 int sin;
 sin = S.size();
 Iterator sit = S.iterator();
 
 
 while(sit.hasNext()){
     //System.out.println("test1!!"); 
     int k = (int)sit.next();
  //   System.out.println("k="+k);  
     S1.addAll(S2);
     Iterator sit1 = S1.iterator();
   while(sit1.hasNext()){
       int h = (int)sit1.next();
   //    System.out.println("h="+h); 
    //   System.out.println("test2!!"); 
         if(attacks[k][h]==1){
          cf = true;
      //    System.out.println("cf is "+ cf); 
          break;
        }
       //  System.out.println("conflict free");
    if(cf==true){
      // System.out.println("test3!!"); 
       break;
      }
    }
 }
 

if(cf==false){
 timeCount++;
//direct approach --begin
 boolean exc = false;
 nonconf = nonconf +1;
long begin1 = System.currentTimeMillis(); 
List ps = new Powerset().getPowerset(Args);
Iterator sb = ps.iterator();
HashSet set;

double pr, prs;
pr=0;
long begin66 = System.currentTimeMillis();
while(sb.hasNext()){
  prs =0;
  List st = (List) sb.next();
  set = new HashSet(st);
    if(!set.isEmpty()){
       //  System.out.println("S= "+S); 
       if(set.containsAll(S)){
        //  System.out.println("Subgraph "+set); 
           
        ////under preferred semantics //
    prs = new ComputeDirectly().verifySemantics(Args, set, S, Children, Parents, p);
    
           
      
        } 
    }
    pr = pr + prs;
    long end66 = System.currentTimeMillis(); 
    //System.out.println("set="+set);
   // System.out.println("test1");
	             	if(end66-begin66>180000){ //现有方法超时设定3分钟
	             		//HashSet error;
	             		//error = new HashSet();
	             		//System.out.println("existing apporach exceeds 6...");
                                exc = true;
	             		break;
	             	}
}
   ps.clear();
   //// System.out.println("The probability that"+S+" is a preferred extension is " + pr); 
 //  if(exc==false){
 //   System.out.println("The probability that"+S+" is a preferred extension is " + pr); 
//   } else{
 //  System.out.println("The probability that"+S+" is a preferred extension can not be found."); 
 //  }
    
    long end1 = System.currentTimeMillis(); 
    //time1 = time1+ (end1-begin1);
    if(exc==false){
    System.out.println("现有方法求解优先语义: " + (end1 - begin1) + " 毫秒");  
    } else{ 
    System.out.println("现有方法超时一次"); 
    }
   
  
//direct approach --end
    
//new approach for preferred

double prob, prob2;
  HashSet Ad;
 // Ad = new genAdmSubgraph().admSub(Args, Children, Parents, S);


  long begin2 = System.currentTimeMillis(); 
//  System.out.println("parents of 3 is "+ Parents[3]); 
   prob = new genPrSubgraph().pfSub(Args, Children, Parents, S, p);
  
//  System.out.println("The probability that"+S+" is a preferred extension is " + prob);
   long end2 = System.currentTimeMillis(); 
   System.out.println("新方法求解优先语义:  " + (end2 - begin2) + " 毫秒"); 
 //  time2 = time2+ (end2-begin2);
   
   long begin3 = System.currentTimeMillis(); 
//  System.out.println("parents of 3 is "+ Parents[3]); 
  prob2 = new genCoSubgraph().coSub(Args, Children, Parents, S, p);
  
//  System.out.println("The probability that"+S+" is a complete extension is " + prob2);
   long end3 = System.currentTimeMillis(); 
//   System.out.println("our approach: " + (end2 - begin2) + " milliseconds"); 
  // time3 = end3-begin3;
   System.out.println("新方法求解完全语义: " + (end3 - begin3)+ " 毫秒");
   System.out.println("============================");
   
}
else{
conf = conf+1;
}

 //   System.out.println("nonconf= "+ nonconf);  
 //   System.out.println("conf= "+ conf);
    
    
    
}
}
}
public static void main(String[] args){

    Scanner input=new Scanner(System.in);
    n= input.nextInt()+1;
    P=new double[n];
    for(int i=1;i<n;i++){
        P[i]=input.nextDouble();
    }
    ini();
    int esize=input.nextInt();
    for(int i=0;i<esize;i++)
    {insert(input.nextInt()+1);}//输入外延
    int edges=input.nextInt();//输入边
    for(int i=0;i<edges;i++)
    {
        insert(input.nextInt()+1,input.nextInt()+1);
    }
    double[] res=test();
    for(int i=0;i<2;i++)
    System.out.println(res[i]);

}
public static void Old(HashSet[] Children,HashSet[] Parents,double[] p)
{
    boolean exc=false;
    long begin1 = System.currentTimeMillis();
    List ps = new Powerset().getPowerset(Args);
    Iterator sb = ps.iterator();
    HashSet set;

    double pr, prs;
    pr=0;
    long begin66 = System.currentTimeMillis();
    while(sb.hasNext()){
        prs =0;
        List st = (List) sb.next();
        set = new HashSet(st);
        if(!set.isEmpty()){
            //  System.out.println("S= "+S);
            if(set.containsAll(E)){
                //  System.out.println("Subgraph "+set);

                ////under preferred semantics //
                prs = new ComputeDirectly().verifySemantics(Args, set, E, Children, Parents, p);



            }
        }
        pr = pr + prs;
        long end66 = System.currentTimeMillis();
        //System.out.println("set="+set);
        // System.out.println("test1");
        if(end66-begin66>180000){ //现有方法超时设定3分钟
            //HashSet error;
            //error = new HashSet();
            //System.out.println("existing apporach exceeds 6...");
            exc = true;
            break;
        }
    }
    ps.clear();
    //// System.out.println("The probability that"+S+" is a preferred extension is " + pr);
    //  if(exc==false){
    //   System.out.println("The probability that"+S+" is a preferred extension is " + pr);
//   } else{
    //  System.out.println("The probability that"+S+" is a preferred extension can not be found.");
    //  }

    long end1 = System.currentTimeMillis();
    //time1 = time1+ (end1-begin1);
    if(exc==false){
        System.out.println("现有方法求解优先语义: " + (end1 - begin1) + " 毫秒"+pr);
    } else{
        System.out.println("现有方法超时一次");
    }

}
public static double[] New(HashSet[] Children,HashSet[] Parents,double[] p)
{
    double prob, prob2;
    HashSet Ad;
    // Ad = new genAdmSubgraph().admSub(Args, Children, Parents, S);


    long begin2 = System.currentTimeMillis();
//  System.out.println("parents of 3 is "+ Parents[3]);
    prob = new genPrSubgraph().pfSub(Args, Children, Parents, E, p);

//  System.out.println("The probability that"+S+" is a preferred extension is " + prob);
    long end2 = System.currentTimeMillis();
    //System.out.println("新方法求解优先语义:  " + (end2 - begin2) + " 毫秒");
    //  time2 = time2+ (end2-begin2);

    long begin3 = System.currentTimeMillis();
//  System.out.println("parents of 3 is "+ Parents[3]);
    //prob2 = new genCoSubgraph().coSub(Args, Children, Parents, E, p);

//  System.out.println("The probability that"+S+" is a complete extension is " + prob2);
    long end3 = System.currentTimeMillis();
//   System.out.println("our approach: " + (end2 - begin2) + " milliseconds");
    // time3 = end3-begin3;
    //System.out.println("新方法求解完全语义: " + (end3 - begin3)+ " 毫秒");
    //System.out.println("============================");
    double result[]=new double[2];
    result[0]=prob;
    result[1]=end2-begin2;
    //result[2]=prob2;
    //result[3]=end3-begin3;
    return result;

}
public static double[] test()
{
    HashSet[] Parents = new ParentsOfArgument().getParent(attacks, n-1, Args);
    HashSet[] Children = new ParentsOfArgument().getChildren(attacks, n-1, Args);
    double[] result=New(Children,Parents,P);
    //Old(Children,Parents,P);
    return result;
}
public static void ini()//初始化邻接表
{

    attacks=new int[n][n];
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            attacks[i][j]=0;
        }
        Args.add(i);//初始化端点集

    }
}
public static void insert(int i, int j)
{
    attacks[i][j]=1;
}
public static void insert(int i)
{
    E.add(i);//初始化外延
}
}

